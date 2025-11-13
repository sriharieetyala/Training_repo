import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class SalesReport {
    public static void main(String[] args) throws Exception {
        
        String filePath = "data/sales.csv";

        
        List<String> lines = Files.readAllLines(Paths.get(filePath)).stream()
                .skip(1) // skip header row
                .collect(Collectors.toList());

        // Process with Streams
        Map<String, Double> totalSales = lines.stream()
                .map(line -> line.split(",")) // split each line into parts
                .collect(Collectors.groupingBy(
                        parts -> parts[0], // key: product name
                        Collectors.summingDouble(
                                parts -> Integer.parseInt(parts[1]) * Double.parseDouble(parts[2]) // quantity * price
                        )
                ));

        // Print report
        totalSales.forEach((product, total) ->
                System.out.println(product + " → $" + total));
    }
}
