import java.util.List;
import java.util.Scanner;

public class HotelApp {
    private static HotelService service = new HotelService();
    private static Scanner scanner = new Scanner(System.in);
    private static double totalBusiness = 0.0;

    public static void main(String[] args) {
        String continueBooking = "yes";

        System.out.println("🏨 Welcome to the Hotel Booking System!");

        while (continueBooking.equalsIgnoreCase("yes")) {
            try {
                Hotel selectedHotel = selectCityAndHotel();
                if (selectedHotel != null) {
                    processBooking(selectedHotel);
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Starting over.");
                scanner.nextLine();
            }

            System.out.print("\nDo you want to book another room? (yes/no): ");
            continueBooking = scanner.next();
        }

        System.out.println("\n\n=== Business Summary ===");
        System.out.printf("💰 Total Business Generated: $%.2f%n", totalBusiness);
        System.out.println("========================");
        scanner.close();
    }

    private static Hotel selectCityAndHotel() {
        System.out.println("\n--- Step 1: Choose City ---");
        List<String> cities = service.getAllCities();
        for (int i = 0; i < cities.size(); i++) {
            System.out.println((i + 1) + ". " + cities.get(i));
        }

        System.out.print("Select a city number: ");
        int cityChoice = scanner.nextInt();
        if (cityChoice < 1 || cityChoice > cities.size()) {
            System.out.println("Invalid city choice.");
            return null;
        }
        String chosenCity = cities.get(cityChoice - 1);
        
        System.out.println("\n--- Step 2: Choose Hotel in " + chosenCity + " ---");
        List<Hotel> availableHotels = service.getHotelsByCity(chosenCity);
        for (int i = 0; i < availableHotels.size(); i++) {
            System.out.println((i + 1) + ". " + availableHotels.get(i).getName());
        }

        System.out.print("Select a hotel number: ");
        int hotelChoice = scanner.nextInt();
        if (hotelChoice < 1 || hotelChoice > availableHotels.size()) {
            System.out.println("Invalid hotel choice.");
            return null;
        }
        return availableHotels.get(hotelChoice - 1);
    }

    private static void processBooking(Hotel hotel) {
        System.out.println("\n--- Step 3: Available Rooms at " + hotel.getName() + " ---");
        List<Room> rooms = hotel.getInventory();
        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            System.out.printf((i + 1) + ". %s (%d available) - Price: $%.2f per night%n",
                    room.getType(), room.getCount(), room.getPrice());
        }

        System.out.print("Select a room number to book: ");
        int roomChoice = scanner.nextInt();
        if (roomChoice < 1 || roomChoice > rooms.size()) {
            System.out.println("Invalid room choice.");
            return;
        }
        Room chosenRoom = rooms.get(roomChoice - 1);

        if (chosenRoom.getCount() <= 0) {
            System.out.println("Sorry, no " + chosenRoom.getType() + " rooms are currently available.");
            return;
        }

        System.out.print("Enter number of nights to stay: ");
        int nights = scanner.nextInt();
        if (nights <= 0) {
            System.out.println("Number of nights must be positive.");
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("Enter your Name for the booking: ");
        String customerName = scanner.nextLine();
        
        // --- Booking and Inventory Update ---
        if (service.bookRoom(chosenRoom, 1)) {
            Booking booking = new Booking(customerName, hotel, chosenRoom, nights);
            totalBusiness += booking.getTotalAmount();
            
            System.out.println("\n✅ Booking successful for " + customerName + "!");
            
            booking.generateInvoice();
            service.displayInventory(hotel);
        } else {
            System.out.println("❌ Booking failed. Room count mismatch or error.");
        }
    }
}