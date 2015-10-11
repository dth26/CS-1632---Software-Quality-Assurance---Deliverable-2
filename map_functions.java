
public interface map_functions {
	public void createPath(Destination from, Destination to, Street street);
	public Destination addDestination(String destName);
	public Street addStreet(String streetName);
	public Driver addDriver(String driver, Destination location);
	public Destination getRandomNeighbor(Destination dest);
	public Destination moveDriver(Driver driver);
	public Destination getRandomDestination();
}
