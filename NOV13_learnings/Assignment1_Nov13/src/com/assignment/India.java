package com.assignment;

import java.io.*;

public class India {
    public static void main(String[] args) {
        int count = 0;

        
        String filePath = "C:\\Users\\sriha\\eclipse-new(nov13)\\Assignment1_Nov13\\src\\wordsofinida.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

         
            while ((line = br.readLine()) != null) {

                
                String[] words = line.split("\\W+");

                
                for (String w : words) {
                    if (w.equalsIgnoreCase("india")) {
                        count++;
                    }
                }
            }

            System.out.println("Total 'india' count: " + count);

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

