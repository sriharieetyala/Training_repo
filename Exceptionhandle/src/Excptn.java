public class Excptn {

    public static void main(String[] args) {
        
        // --- 1. Basic try-catch (e.g., ArithmeticException) ---
        System.out.println("--- 1. Basic try-catch (Divide by Zero) ---");
        try {
            int result = 10 / 0; // This will throw an ArithmeticException
            System.out.println("Result: " + result); // This line is skipped
        } catch (ArithmeticException e) {
            System.out.println("Exception Caught: Cannot divide by zero. -> " + e.getMessage());
        }
        
        // --- 2. try with Multiple catch blocks (ArrayIndexOutOfBoundsException) ---
        System.out.println("\n--- 2. try with Multiple catch blocks (Array Index) ---");
        try {
            int[] numbers = {1, 2, 3};
            System.out.println("Trying to access index 5: " + numbers[5]); // Throws ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception Caught: Array index is out of bounds. -> " + e.getMessage());
        } catch (Exception e) {
            // A general catch block to handle any other unexpected exceptions. 
            
            System.err.println("A general exception occurred. -> " + e.getMessage());
        }
        
        // --- 3. Example of IOException/FileNotFoundException (Requires java.io.* imports) ---
        System.out.println("\n--- 3. FileNotFoundException / IOException Example ---");
        // NOTE: In a real scenario, you'd need actual file operations and imports.
        // This block *simulates* the process by calling a method that would throw the exception.
        try {
            simulateFileRead("non_existent_file.txt"); 
            System.out.println("File operation successful."); // This line is skipped if exception occurs
        } catch (java.io.FileNotFoundException e) {
            // Handles the specific case of the file not being found.
            System.err.println("Exception Caught: Specified file was not found. -> " + e.getMessage());
        } catch (java.io.IOException e) {
            // Handles any other general IO errors (e.g., read/write permissions).
            System.err.println("Exception Caught: A general IO error occurred. -> " + e.getMessage());
        }
    }

    /**
     * Helper method to simulate a file operation that throws a checked exception.
     * In a real application, java.io.FileReader or similar class would throw these.
     */
    public static void simulateFileRead(String filename) throws java.io.FileNotFoundException, java.io.IOException {
        if (filename.equals("non_existent_file.txt")) {
            // Throw a specific checked exception
            throw new java.io.FileNotFoundException("The file '" + filename + "' could not be located.");
        }
        // else if (/* some IO error condition */) {
        //    throw new java.io.IOException("Error reading data from file.");
        // }
    }
}