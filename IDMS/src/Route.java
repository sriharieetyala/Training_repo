
import java.util.ArrayList;
import java.util.List;

public class Route implements IEntity {
	
    private final String routeId;
    private final String assignedAgentId;
    private final List<Package> packageStops;

    public Route(String routeId, String assignedAgentId) {
    	
        this.routeId = routeId;
        this.assignedAgentId = assignedAgentId;
        this.packageStops = new ArrayList<>();
    }

    @Override
    public String getId() { 
    	return this.routeId; 
    	}
    
    public String getAssignedAgentId() {
    	return assignedAgentId;
    	}
    public List<Package> getPackageStops() { 
    	return packageStops;
    	}
    public void addPackageStop(Package pkg) {
    	this.packageStops.add(pkg); 
    	}
}