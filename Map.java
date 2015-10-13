
import java.util.ArrayList;
import java.util.Random;

public class Map implements map_functions{

	ArrayList<Destination> destinations;

	
	public Map(){
		destinations = new ArrayList<Destination>();
	}
	
//	public int getRandom(int max){
//		return randomGenerator.nextInt(max);
//	}


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
	public Destination getRandomNeighbor(Destination dest, Random rand) {
		int neighborSize = dest.neighbors.size();
		int random = rand.nextInt(neighborSize);
		return dest.neighbors.get(random);
	}



	@Override
	public Destination moveDriver(Driver driver, Random rand) {
		Destination currDestination = driver.location;
		Destination newDestination = getRandomNeighbor(currDestination, rand);
		driver.setLocation(newDestination);
		return newDestination;
	}




	@Override
	public Destination getRandomDestination(Random rand) {
		int random = rand.nextInt(destinations.size());
		return destinations.get(random);
	}
	
}
