
import java.util.ArrayList;
import java.util.Random;

public class Map implements map_functions{

	private ArrayList<Destination> destinations;
	private Random randomGenerator;
	
	public Map(int seed){
		destinations = new ArrayList<Destination>();
		randomGenerator = new Random(seed);
	}
	
	public int getRandom(int max){
		return randomGenerator.nextInt(max);
	}


	@Override
	public void createPath(Destination from, Destination to, Street street){
		from.AddNeighbor(to, street);	
	}

	@Override
	public Destination addDestination(String destName) {
		Destination newDest = new Destination(destName);
		destinations.add(newDest);
		return newDest;
		
	}

	@Override
	public Street addStreet(String streetName) {
		Street newStreet = new Street(streetName);
		return newStreet;
	}

	@Override
	public Driver addDriver(String driverName, Destination location) {
		Driver newDriver = new Driver(driverName, location);
		return newDriver;
	}



	@Override
	public Destination getRandomNeighbor(Destination dest) {
		int neighborSize = dest.neighbors.size();
		int random = getRandom(neighborSize);
		return dest.neighbors.get(random);
	}



	@Override
	public Destination moveDriver(Driver driver) {
		Destination currDestination = driver.location;
		Destination newDestination = getRandomNeighbor(currDestination);
		driver.setLocation(newDestination);
		return newDestination;
	}




	@Override
	public Destination getRandomDestination() {
		int rand = getRandom(destinations.size());
		return destinations.get(rand);
	}
	
}
