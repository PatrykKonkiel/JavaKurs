package pd11.report;

import lombok.AllArgsConstructor;
import pd11.data.InMemoryRepository;
import pd11.enums.Category;
import pd11.enums.Priority;
import pd11.enums.Status;
import pd11.model.Employee;
import pd11.model.Ticket;
import pd11.service.TicketPredicates;

import java.util.List;

@AllArgsConstructor
public class Report {
    private InMemoryRepository inMemoryRepository;

    private void countByStatus() {
        for (Status status : Status.values()) {
            long count = inMemoryRepository.getTickets().stream().filter(ticket -> ticket.getStatus() == status).count();
            System.out.println(status + " " + count);
        }
    }

    private void countByCategory() {
        for (Category category : Category.values()) {
            long count = inMemoryRepository.getTickets().stream().filter(ticket -> ticket.getCategory() == category).count();
            System.out.println(category + " " + count);
        }
    }

    private void countByPriority() {
        for (Priority priority : Priority.values()) {
            long count = inMemoryRepository.getTickets().stream().filter(ticket -> ticket.getPriority() == priority).count();
            System.out.println(priority + " " + count);
        }
    }

   private void activeTicketsByEmployee() {
        for (Employee employee : inMemoryRepository.getEmployees()) {
            long count = inMemoryRepository.getTickets().stream()
                    .filter(ticket -> ticket.getEmployee() != null)
                    .filter(ticket -> ticket.getEmployee().equals(employee))
                    .filter(ticket -> ticket.getStatus() != Status.COMPLETED)
                    .count();
            System.out.println(employee + ": " + count);
        }
    }

    private void unassignedTicketsWithHighPrio() {
        inMemoryRepository.getTickets().stream()
                .filter(TicketPredicates.byStatus(Status.UNASSIGNED))
                .filter(TicketPredicates.byPriority(Priority.HIGH))
                .forEach(System.out::println);
    }

    private boolean allTicketsClosed() {
        List<Ticket> tickets = inMemoryRepository.getTickets();
        return tickets.stream().allMatch(ticket -> ticket.getStatus() == Status.COMPLETED);
    }

    public void printReport() {
        System.out.println("Report: ");
        System.out.println("Tickets count by status");
        countByStatus();
        System.out.println("Ticket count by category");
        countByCategory();
        System.out.println("Ticket count by priority");
        countByPriority();
        System.out.println("Active tickets by employee");
        activeTicketsByEmployee();
        System.out.println("Unassigned tickets with high priority");
        unassignedTicketsWithHighPrio();
        System.out.println("Are all ticket closed?: " + (allTicketsClosed() ? "Yes" : "No"));
    }
}
