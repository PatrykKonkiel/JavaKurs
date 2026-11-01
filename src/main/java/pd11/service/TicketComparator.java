package pd11.service;

import pd11.enums.Category;
import pd11.enums.Priority;
import pd11.enums.Status;
import pd11.model.Ticket;

import java.util.Comparator;

public class TicketComparator {
    public static Comparator<Ticket> byPriority(Priority priority) {
        return Comparator.comparing(ticket -> ticket.getPriority() != priority);
    }

    public static Comparator<Ticket> byCategory(Category category) {
        return Comparator.comparing(ticket -> ticket.getCategory() != category);
    }

    public static Comparator<Ticket> byStatus(Status status) {
        return Comparator.comparing(ticket -> ticket.getStatus() != status);
    }
}
