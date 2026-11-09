package pd12;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Config config = mapper.readValue(Path.of("src/main/java/pd12/config.json").toFile(), Config.class);
        Path inputPath = Path.of(config.inputPath);
        Path outputPath = Path.of(config.outputPath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.dateFormat);
        List<Sale> sales = loadSales(inputPath);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(outputPath.resolve("report.json").toFile(), sales);
        Files.writeString(outputPath.resolve("summary.csv"), SalesService.totalAndAverage(sales).toString(), StandardCharsets.UTF_8);
    }

    public static List<Sale> loadSales(Path path) throws IOException {
        try (var lazyReader = Files.lines(path)) {
            return lazyReader.skip(1)
                    .map(s -> s.split(",", -1))
                    .map(c -> new Sale(
                            LocalDate.parse(c[0]),
                            c[1],
                            c[2],
                            c[3],
                            Integer.parseInt(c[4]),
                            new BigDecimal(c[5])
                    )).toList();
        }
    }
}
