import java.util.List;

public class Hotel {
    private String name;
    private String city;
    private List<Room> inventory;

    public Hotel(String name, String city, List<Room> inventory) {
        this.name = name;
        this.city = city;
        this.inventory = inventory;
    }

    public String getName() { return name; }
    public String getCity() { return city; }
    public List<Room> getInventory() { return inventory; }
}