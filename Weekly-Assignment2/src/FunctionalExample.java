import java.util.*;
import java.util.stream.*;

public class FunctionalExample {
    static class Customer {
        String name;
        double spent;
        Customer(String name, double spent) { this.name = name; this.spent = spent; }
    }

    public static void main(String[] args) {
        List<Customer> customers = List.of(
            new Customer("Alice", 1200),
            new Customer("Bob", 800),
            new Customer("Charlie", 1500),
            new Customer("Diana", 1100)
        );

        // Functional pipeline: filter -> sort -> map -> collect
        List<String> vipNames = customers.stream()
            .filter(c -> c.spent > 1000)                      // keep only customers with spent > 1000
            .sorted(Comparator.comparingDouble((Customer c) -> c.spent).reversed()) // sort by spent desc
            .map(c -> c.name)                                 // convert Customer -> name
            .collect(Collectors.toList());                    // collect into a List<String>

        System.out.println(vipNames);
    }
}
