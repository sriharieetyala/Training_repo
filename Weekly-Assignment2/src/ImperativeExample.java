import java.util.*;

public class ImperativeExample {
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

        // Imperative approach: mutate a list, multiple steps
        List<Customer> vipList = new ArrayList<>();
        for (Customer c : customers) {
            if (c.spent > 1000) {
                vipList.add(c);
            }
        }

        // sort vipList by spent descending
        Collections.sort(vipList, new Comparator<Customer>() {
            @Override
            public int compare(Customer a, Customer b) {
                return Double.compare(b.spent, a.spent);
            }
        });

        // extract names
        List<String> vipNames = new ArrayList<>();
        for (Customer c : vipList) {
            vipNames.add(c.name);
        }

        // print result
        System.out.println(vipNames);
    }
}
