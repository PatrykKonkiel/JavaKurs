package pd11.service;

import pd11.enums.Category;
import pd11.enums.Priority;
import pd11.enums.Status;
import pd11.model.Ticket;

import java.util.function.Predicate;

public class TicketPredicates {

    public static Predicate<Ticket> byCategory(Category category) {
        return ticket -> ticket.getCategory() == category;
    }

    public static Predicate<Ticket> byStatus(Status status) {
        return ticket -> ticket.getStatus() == status;
    }

    public static Predicate<Ticket> byPriority(Priority priority) {
        return ticket -> ticket.getPriority() == priority;
    }
}
