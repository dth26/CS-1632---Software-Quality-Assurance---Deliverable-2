

public class Driver {
	Destination location;
	String driverName;
	
	public Driver(String driverName, Destination location){
		this.location = location;
		this.driverName = driverName;
	}
	
	public void setLocation(Destination location){
		this.location = location;
	}
}
