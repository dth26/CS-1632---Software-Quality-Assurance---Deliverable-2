import java.util.ArrayList;



public class Destination {

	ArrayList<Destination> neighbors;
	private ArrayList<Street> streets;
	String name;
	
	public Destination(String destinationName){
		neighbors = new ArrayList<Destination>();
		streets = new ArrayList<Street>();
		name = destinationName;
	}
	
	
	
	public void AddNeighbor(Destination dest, Street street){
		neighbors.add(dest);
		streets.add(street);
	}
	
	
	// get street from 'this' object -> 'to'
	public Street getStreet(Destination to){
		Street street = null;
		
		for(int i=0; i<neighbors.size(); i++)
		{
			if(neighbors.get(i) == to)
			{
				street = streets.get(i);
				break;
			}
		}
		
		return street;
	}
}