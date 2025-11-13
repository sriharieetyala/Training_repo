
public abstract class Person implements IEntity {
	
    protected String name;
    protected String contactNumber;

    public Person(String name, String contactNumber) {
    	
        this.name = name;
        this.contactNumber = contactNumber;
    }
    
    public String getName() {
    	return name; 
    	}
    public String getContactNumber() { 
    	return contactNumber; 
    	}
}