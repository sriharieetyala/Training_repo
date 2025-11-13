package com.app.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        List<String> logs = Arrays.asList("Login", "fundtrasfer","add bene", "Logout");
        
        List<String> names = new ArrayList<String>();
        
        names.add(null);
        
        Stream<String> str = logs.stream();
        
      boolean status =  str.allMatch(name->name.startsWith("L"));
      
      System.out.println("value of status" +status);
      
      //  str.anyMatch(name->name.startsWith("L"));
        
      //  long count = str.filter(str1->str1.startsWith("L")).count();
        
       // System.out.println("value of count" +count);
        
        logs.stream().filter(str1->str1.startsWith("L")).count();
        
        List<String> filternames = logs.stream().filter(str1->str1.startsWith("L")).collect(Collectors.toList());
        
        filternames.forEach(System.out::println);

	}

}
