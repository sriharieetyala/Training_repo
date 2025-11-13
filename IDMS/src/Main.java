
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DeliveryService service = new DeliveryService();

    public static void main(String[] args) {
        System.out.println("--- SmartShip Intelligent Delivery Management System ---");
        
        setupInitialAgents();
        
        boolean running = true;
        while (running) {
            System.out.println("\n=======================================================");
            System.out.println("Pending Packages: " + service.getPendingPackageCount());
            System.out.println("Select Operation:");
            System.out.println("1. Add New Package");
            System.out.println("2. Assign Route Batch (Optimization)");
            System.out.println("3. View Agent Status");
            System.out.println("4. Exit");
            System.out.print("Enter choice (1-4): ");
            
            String choiceLine = scanner.nextLine().trim();
            if (choiceLine.isEmpty()) continue;
            
            try {
                int choice = Integer.parseInt(choiceLine);
                switch (choice) {
                    case 1: addPackage(); break;
                    case 2: assignRoute(); break;
                    case 3: viewAgentStatus(); break;
                    case 4: running = false; break;
                    default: System.out.println("Invalid option. Please choose 1-4.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Input Error: Please enter a number for your choice.");
            }
        }
        System.out.println("\nSystem shutting down. Goodbye.");
        scanner.close();
    }

    private static void setupInitialAgents() {
        System.out.println("\n--- Setting up Initial Agents (HYDERABAD and MUMBAI) ---");
        try {
            service.registerAgent(new Agent("Alice", "555-001", "A001", "HYDERABAD"));
            service.registerAgent(new Agent("Bob", "555-002", "B002", "MUMBAI"));
            service.registerAgent(new Agent("Charlie", "555-003", "A003", "HYDERABAD")); 
        } catch (DuplicateEntryException e) {
            System.err.println("Setup Error: " + e.getMessage());
        }
    }
    
    private static void addPackage() {
        System.out.println("\n--- Add New Package ---");
        try {
            System.out.print("Enter Tracking ID: ");
            String id = scanner.nextLine().trim();
            if (id.isEmpty()) throw new InvalidPackageException("ID cannot be empty.");

            System.out.print("Enter Destination City (e.g., HYDERABAD): ");
            String city = scanner.nextLine().trim().toUpperCase();

            System.out.print("Is High Priority? (yes/no): ");
            boolean priority = scanner.nextLine().trim().equalsIgnoreCase("yes");
            
            Package pkg = new Package(id, "Address TBD", city, priority);
            service.addPackage(pkg);
            
        } catch (InvalidPackageException | DuplicateEntryException e) {
            System.err.println("Package Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unknown error occurred: " + e.getMessage());
        }
    }
    
    private static void assignRoute() {
        System.out.println("\n--- Route Assignment (Optimization) ---");
        try {
            if (service.getPendingPackageCount() == 0) {
                System.out.println("No packages are currently pending assignment.");
                return;
            }
            
            System.out.print("Enter Target Destination City for Route: ");
            String city = scanner.nextLine().trim().toUpperCase();
            
            System.out.print("Enter Batch Size (packages per agent, 1-5): ");
            int batchSize = Integer.parseInt(scanner.nextLine().trim());
            
            if (batchSize <= 0) throw new NumberFormatException();
            
            service.assignRouteBatch(city, batchSize); 

        } catch (AgentNotAvailableException | OverloadException | RouteUnavailableException e) {
            System.err.println("ROUTE FAILED: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Input Error: Batch size must be a positive number.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during assignment: " + e.getMessage());
        }
    }
    
    private static void viewAgentStatus() {
        System.out.println("\n--- Current Agent Status ---");
        if (service.getAllAgents().isEmpty()) {
            System.out.println("No agents registered.");
            return;
        }
        service.getAllAgents().stream()
            .sorted(Agent.getLoadComparator())
            .forEach(System.out::println);
    }
}