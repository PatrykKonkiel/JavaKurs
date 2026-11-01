package pd11.data;

import lombok.Getter;
import pd11.enums.Category;
import pd11.model.Employee;
import pd11.enums.Priority;
import pd11.model.Ticket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Getter
public class InMemoryRepository {
    private final List<Ticket> tickets = new ArrayList<>();
    private final List<Employee> employees = new ArrayList<>();
    private int counterTicket = 1;
    private int counterEmployee = 1;

    public Ticket saveTicket(String title, String description, Priority priority, Category category) {
        Ticket ticket = new Ticket(counterTicket++, title, description, priority, category);
        tickets.add(ticket);
        return ticket;
    }

    public Employee saveEmployee(String name, String lastName) {
        Employee employee = new Employee(counterEmployee++, name, lastName);
        employees.add(employee);
        return employee;
    }

    public Optional<Ticket> findById(int id) {
        return tickets.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst();
    }

    public Optional<Employee> findEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    public void assignEmployeeToTicket(int ticketId, int employeeId) {
        Ticket ticket = findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        Employee employee = findEmployeeById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        ticket.assignEmployee(employee);
    }

    public void closeTicket(int ticketId, String message) {
        Ticket ticket = findById(ticketId).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.close(message);
    }

    public void findBy(Predicate<Ticket> predicate) {
        tickets.stream()
                .filter(predicate)
                .forEach(System.out::println);
    }

    public void sortBy(Comparator<Ticket> comparator) {
        tickets.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    public void printAll() {
        System.out.println("All tickets:");
        tickets.forEach(System.out::println);
        System.out.println("All employess:");
        employees.forEach(System.out::println);
    }
}
