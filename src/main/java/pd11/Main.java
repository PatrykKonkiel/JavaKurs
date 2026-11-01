package pd11;

import pd11.data.InMemoryRepository;
import pd11.enums.Category;
import pd11.enums.Priority;
import pd11.report.Report;
import pd11.service.TicketComparator;
import pd11.service.TicketPredicates;

public class Main {
    public static void main(String[] args) {
        InMemoryRepository memoryRepository = new InMemoryRepository();
        Report report = new Report(memoryRepository);
        memoryRepository.saveTicket("Bleble ", "blublu", Priority.LOW, Category.ERROR);
        memoryRepository.saveTicket("asddddd ", "asdasdasd", Priority.HIGH, Category.ERROR);
        memoryRepository.saveTicket("Bleble ", "blublu", Priority.LOW, Category.ERROR);
        memoryRepository.saveEmployee("Jan", "Nowak");
        memoryRepository.saveEmployee("Jacek", "Jaworek");
        memoryRepository.saveEmployee("Adolf", "Jakiś");
        memoryRepository.saveEmployee("Józek", "Nowak");
        memoryRepository.assignEmployeeToTicket(1, 1);
        memoryRepository.closeTicket(1,"Nie dzialalo ale dziala juz");
        memoryRepository.sortBy(TicketComparator.byCategory(Category.ERROR));
        memoryRepository.findBy(TicketPredicates.byPriority(Priority.LOW));
        report.printReport();
        memoryRepository.printAll();

    }
}
