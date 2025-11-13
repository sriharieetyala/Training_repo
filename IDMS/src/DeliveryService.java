
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService {
    // HashMap for O(1) lookup of Agents and Packages
    private final Map<String, Agent> agentMap = new HashMap<>();
    private final Map<String, Package> packageMap = new HashMap<>();
    
    // PriorityQueue for optimization: retrieves high-priority packages first
    private final Queue<Package> pendingPackagesQueue = new PriorityQueue<>();
    
    private static final int MAX_PACKAGES_PER_AGENT = 5;

    public void registerAgent(Agent agent) throws DuplicateEntryException {
        if (agentMap.containsKey(agent.getId())) {
            throw new DuplicateEntryException("Agent ID " + agent.getId() + " already exists.");
        }
        agentMap.put(agent.getId(), agent);
        System.out.println("Registered Agent: " + agent.getName() + " in " + agent.getCity());
    }

    public void addPackage(Package pkg) throws DuplicateEntryException, InvalidPackageException {
        if (packageMap.containsKey(pkg.getId())) {
            throw new DuplicateEntryException("Package ID " + pkg.getId() + " already exists.");
        }
        if (pkg.getDestinationCity() == null || pkg.getDestinationCity().trim().isEmpty()) {
            throw new InvalidPackageException("Package must have a valid destination city.");
        }
        
        packageMap.put(pkg.getId(), pkg);
        pendingPackagesQueue.offer(pkg); // Adds package to be prioritized
        System.out.println("Package " + pkg.getId() + " added to pending queue.");
    }
    
    public Collection<Agent> getAllAgents() {
        return agentMap.values();
    }
    
    public int getPendingPackageCount() {
        return pendingPackagesQueue.size();
    }
    
    /**
     * Logic: Finds the least-loaded available agent in the city and assigns a batch 
     * of packages prioritized by the PriorityQueue.
     */
    public Route assignRouteBatch(String destinationCity, int batchSize) 
            throws AgentNotAvailableException, OverloadException, RouteUnavailableException {

        // 1. Filter and Sort Agents (Stream API): Sort agents by least assigned packages
        List<Agent> availableAgents = agentMap.values().stream()
            .filter(a -> a.getCity().equalsIgnoreCase(destinationCity))
            .filter(a -> a.getStatus().equals(Agent.STATUS_AVAILABLE)) 
            .sorted(Agent.getLoadComparator()) 
            .collect(Collectors.toList());

        if (availableAgents.isEmpty()) {
            throw new AgentNotAvailableException("No AVAILABLE agent found in " + destinationCity + ".");
        }

        Agent selectedAgent = availableAgents.get(0);
        
        // 2. Overload Check
        if (selectedAgent.getAssignedPackageCount() + batchSize > MAX_PACKAGES_PER_AGENT) {
             throw new OverloadException("Agent " + selectedAgent.getId() + " would be overloaded. Max packages: " + MAX_PACKAGES_PER_AGENT);
        }

        // 3. Package Selection (PriorityQueue Logic)
        Route newRoute = new Route(UUID.randomUUID().toString(), selectedAgent.getId());
        List<Package> nonMatchingPackages = new ArrayList<>();
        int packagesAssigned = 0;
        
        // Pull HIGH PRIORITY packages from the queue
        while (!pendingPackagesQueue.isEmpty() && packagesAssigned < batchSize) {
             Package pkg = pendingPackagesQueue.poll();
             
             if (pkg.getDestinationCity().equalsIgnoreCase(destinationCity)) {
                newRoute.addPackageStop(pkg);
                packagesAssigned++;
             } else {
                 // Re-queue non-matching packages after this route is assigned
                 nonMatchingPackages.add(pkg);
             }
        }
        
        pendingPackagesQueue.addAll(nonMatchingPackages); // Add back non-city packages

        if (newRoute.getPackageStops().isEmpty()) {
            throw new RouteUnavailableException("No pending packages found for city " + destinationCity + ".");
        }
        
        // 4. Update Statuses
        selectedAgent.setStatus(Agent.STATUS_ON_ROUTE);
        selectedAgent.setAssignedPackageCount(selectedAgent.getAssignedPackageCount() + packagesAssigned);

        System.out.printf("\n--- ASSIGNMENT SUCCESS ---\nRoute ID: %s assigned to Agent %s (%s).\nPackages: %s\nNew Agent Load: %d\n",
                          newRoute.getId(), selectedAgent.getName(), selectedAgent.getId(), newRoute.getPackageStops(), selectedAgent.getAssignedPackageCount());
        
        return newRoute;
    }
}