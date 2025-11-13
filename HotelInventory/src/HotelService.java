import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HotelService {
    private List<Hotel> allHotels;

    public HotelService() {
        initializeHotels();
    }

    private void initializeHotels() {
        allHotels = new ArrayList<>();

        // Mumbai Hotels
        List<Room> jwInventory = new ArrayList<>();
        jwInventory.add(new Room("Standard", 5, 5000.00));
        jwInventory.add(new Room("Deluxe", 3, 8500.00));
        allHotels.add(new Hotel("JW Marriott", "Mumbai", jwInventory));

        List<Room> tajInventory = new ArrayList<>();
        tajInventory.add(new Room("Luxury", 4, 12000.00));
        tajInventory.add(new Room("Standard", 7, 6000.00));
        allHotels.add(new Hotel("Taj Palace", "Mumbai", tajInventory));

        // Delhi Hotels
        List<Room> leelaInventory = new ArrayList<>();
        leelaInventory.add(new Room("Premium", 6, 9000.00));
        leelaInventory.add(new Room("Suite", 2, 15000.00));
        allHotels.add(new Hotel("The Leela", "Delhi", leelaInventory));

        List<Room> novotelInventory = new ArrayList<>();
        novotelInventory.add(new Room("Standard", 10, 4500.00));
        novotelInventory.add(new Room("Deluxe", 5, 7000.00));
        allHotels.add(new Hotel("Novotel", "Delhi", novotelInventory));
    }

    public List<String> getAllCities() {
        return allHotels.stream()
                .map(Hotel::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Hotel> getHotelsByCity(String city) {
        return allHotels.stream()
                .filter(h -> h.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public boolean bookRoom(Room room, int quantity) {
        if (room.getCount() >= quantity) {
            room.setCount(room.getCount() - quantity);
            return true;
        }
        return false;
    }

    public void displayInventory(Hotel hotel) {
        System.out.println("\n--- Updated Inventory for " + hotel.getName() + " ---");
        for (Room room : hotel.getInventory()) {
            System.out.printf("- %s: %d remaining (Rate: $%.2f)%n",
                    room.getType(), room.getCount(), room.getPrice());
        }
    }
}