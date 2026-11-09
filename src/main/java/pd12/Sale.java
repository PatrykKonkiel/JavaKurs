package pd12;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
public class Sale {
    private LocalDate date;
    private String productId;
    private String productName;
    private String category;
    private int quantity;
    private BigDecimal unitPrice;

    public BigDecimal getRevenue(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
