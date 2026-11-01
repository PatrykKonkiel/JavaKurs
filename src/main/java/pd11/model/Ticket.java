package pd11.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import pd11.enums.Category;
import pd11.enums.Priority;
import pd11.enums.Status;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
public class Ticket {
    @EqualsAndHashCode.Include
    private final int id;

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private Category category;
    private Employee employee;
    private String resolveMessage;

    public Ticket(int id, String title, String description, Priority priority, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = Status.UNASSIGNED;
        this.priority = priority;
        this.category = category;
        ticketValidation();
    }

    private void ticketValidation() {
        if (id < 0) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Tittle cannot be null or blank");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or blank");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
    }

    public void assignEmployee(Employee employee) {
        this.employee = employee;
        status = Status.IN_PROGRESS;
    }

    public void close(String resolveMessage) {
        if (status != Status.IN_PROGRESS) {
            throw new IllegalArgumentException("Ticket must be in progress");
        }
        this.resolveMessage = resolveMessage;
        this.status = Status.COMPLETED;
    }
}
