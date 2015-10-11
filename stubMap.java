
public class stubMap implements map_functions{
	
	
	public int getRandom(int max){
		return 0;
	}


	@Override
	public void createPath(Destination from, Destination to, Street street) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Destination addDestination(String destName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Street addStreet(String streetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Driver addDriver(String driver, Destination location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Destination getRandomNeighbor(Destination dest) {
		// TODO Auto-generated method stub
		return new Destination("bookstore");
	}

	@Override
	public Destination moveDriver(Driver driver) {
		// TODO Auto-generated method stub
		return new Destination("mall");
	}

	@Override
	public Destination getRandomDestination() {
		// TODO Auto-generated method stub
		return new Destination("University");
	}

}
