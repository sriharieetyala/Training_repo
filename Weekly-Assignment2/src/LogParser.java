import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class LogParser {
    public static void main(String[] args) throws Exception {
        // Path to your log file
        String filePath = "data/app.log";

        // Read all lines from the log file
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        // Process the lines using streams
        Map<String, Long> errorCounts = lines.stream()
                .filter(line -> line.contains("ERROR")) // only lines with "ERROR"
                .map(line -> line.split(":")[1].trim()) // extract the part after "ERROR:"
                .collect(Collectors.groupingBy(error -> error, Collectors.counting()));

        // Print the error summary
        System.out.println("Error Summary Report:");
        errorCounts.forEach((type, count) ->
                System.out.println(type + " → " + count + " times"));
    }
}
