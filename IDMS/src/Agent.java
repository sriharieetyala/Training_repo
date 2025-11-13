
import java.util.Comparator;

public class Agent extends Person {
    // String Constants replacing Enum
    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_ON_ROUTE = "ON_ROUTE";
    public static final String STATUS_OFFLINE = "OFFLINE";
    
    private final String agentId;
    private String status; 
    private String city;
    private int assignedPackageCount;

    public Agent(String name, String contact, String agentId, String city) {
        super(name, contact);
        this.agentId = agentId;
        this.status = STATUS_AVAILABLE;
        this.city = city;
        this.assignedPackageCount = 0;
    }

    @Override
    public String getId() { return this.agentId; } 

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCity() { return city; }
    public int getAssignedPackageCount() { return assignedPackageCount; }
    public void setAssignedPackageCount(int count) { this.assignedPackageCount = count; }

    // Comparator: Used by Stream API to sort by least assigned packages
    public static Comparator<Agent> getLoadComparator() {
        return Comparator.comparingInt(Agent::getAssignedPackageCount);
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s) | City: %s | Status: %s | Load: %d", name, agentId, city, status, assignedPackageCount);
    }
}