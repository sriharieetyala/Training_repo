package test1;

import java.util.*;
import java.util.stream.Collectors;

public class Maven1test1 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(10, 25, 30, 45, 50, 65);

        List<Integer> result = numbers.stream()                      // start the stream
                                      .filter(n -> n % 2 == 0)       // keep only even numbers
                                      .map(n -> n * 2)               // double each number
                                      .sorted()                      // sort the results
                                      .collect(Collectors.toList()); // collect into a list

        System.out.println("Processed list: " + result);
    }
}
