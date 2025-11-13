
public class Package implements IEntity, Comparable<Package> {
	
    private final String trackingId;
    private final String deliveryAddress;
    private final String destinationCity;
    private final boolean isHighPriority;

    public Package(String id, String address, String city, boolean priority) {
        this.trackingId = id;
        this.deliveryAddress = address;
        this.destinationCity = city;
        this.isHighPriority = priority;
    }
    
    @Override
    public String getId() { 
    	return this.trackingId;
    	}

    public String getDestinationCity() { 
    	return destinationCity;
    	}

    // Comparable implementation for PriorityQueue: High-Priority Packages First
    @Override
    public int compareTo(Package other) {
        // High priority (true) returns -1 to put it first in the PriorityQueue
        if (this.isHighPriority && !other.isHighPriority) return -1;
        if (!this.isHighPriority && other.isHighPriority) return 1;
        return this.trackingId.compareTo(other.trackingId);
    }
    
    @Override
    public String toString() {
        return String.format("%s (City: %s, Priority: %b)", trackingId, destinationCity, isHighPriority);
    }
}