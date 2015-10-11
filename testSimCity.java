import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// You could also do this to make this a little cleaner.  
//import  org.mockito.Mockito.*;

import org.mockito.*;

import static org.mockito.Mockito.when;   // ...or...

import java.util.ArrayList;

import static org.mockito.Mockito.*;  
public class testSimCity {
	@SuppressWarnings("unchecked")	
	
	stubMap methodStubsForMap = new stubMap();
	
	
	// create a new street
	// ensure that the street created is not null
	@Test
	public void testNewStreet(){
		Street street = new Street("fifth ave");
		assertNotNull(street);
	}
	
	 // create two different streets
	 // check that two different streets do equal each other
	@Test
	public void testStreetsAreDifferent(){
		Street street1 = new Street("Fourth ave");
		Street street2 = new Street("Fifth ave");
		assertNotSame(street1, street2);
	}
	
	
	// create two destinations and give each destination a set of different neighbors
	// check that the neighbors of the two different destinations are not the same
	@Test
	public void testDestinationNeighbors(){
		ArrayList<Destination> neighbors1 = new ArrayList<Destination> ();
		ArrayList<Destination> neighbors2 = new ArrayList<Destination> ();
		Destination destination1 = new Destination("Mall");
		Destination destination2 = new Destination("Mall");
		
		for(int i=0; i<10; i++){
			destination1.AddNeighbor(Mockito.mock(Destination.class), Mockito.mock(Street.class) );
		}
		
		for(int i=0; i<11; i++){
			destination2.AddNeighbor(Mockito.mock(Destination.class), Mockito.mock(Street.class) );
		}
		
		assertNotSame(destination1.neighbors, destination2.neighbors);
	}
	
	
	// ensure that creating new CitySim9000 does not result in error
	@Test
	public void testNewCitySim9000(){
		int random = methodStubsForMap.getRandom(1);
		CitySimDriver CitySim = new CitySimDriver(random);
		assertNotNull(CitySim);
	}
	
	// create two destinations 
	// add different neighbor to each destinations via the same street
	// check that the street is the same when we getStreet()
	@Test
	public void testDestinationsSameStreet(){
		Destination destination1 = Mockito.mock(Destination.class);
		Destination destination2 = Mockito.mock(Destination.class);
		Destination neighbor1 = new Destination("mall");
		Destination neighbor2 = new Destination("bookstore");
		Street street = new Street("Fourth Ave");
		
		destination1.AddNeighbor(neighbor1, street);
		destination2.AddNeighbor(neighbor2, street);
		
		assertEquals(destination1.getStreet(neighbor1), destination2.getStreet(neighbor2));
	}
	
	
	// create destination and add two neighbor to it
	// check that street the street leading to the neighbor is not null
	@Test
	public void testNeighborNotNull(){
		Destination neighbor = Mockito.mock(Destination.class);
		Destination destination = new Destination("mall");
		Street street = Mockito.mock(Street.class);
		destination.AddNeighbor(destination, street);
		assertNotNull(destination.getStreet(destination));
	}
	
	
	// add two destinations to map
	// check that getRandomDestination does not return a null destination
	@Test
	public void testGetRandomDestination(){
		int random = methodStubsForMap.getRandom(10);
		Map map = new Map(random);
		map.addDestination("home");
		map.addDestination("school");
		Destination destination = map.getRandomDestination();
		assertNotNull(destination);
	}
	
	
	// create new destination and add two neighbors to it
	// add destination to map
	// check that getRandomNeighbor returns a neighbor that is not null
	@Test
	public void testGetRandomNeighbor(){
		Destination destination = methodStubsForMap.getRandomDestination();
		Destination neighbor1 = new Destination("mall");
		Destination neighbor2 = new Destination("bookstore");
		Street street1 = Mockito.mock(Street.class);
		Street street2 = Mockito.mock(Street.class);
		destination.AddNeighbor(neighbor1, street1);
		destination.AddNeighbor(neighbor2, street2);
		Map map = new Map(5);
		Destination randomNeighbor = map.getRandomNeighbor(destination);
		assertNotNull(randomNeighbor);
	}
	
	
	// create new driver with starting position
	// ensure that moveDriver sets the current location of driver to a new destination
	@Test
	public void testMoveDriver(){
		Map map = new Map(5);
		Destination currDestination = new Destination("home");
		Destination neighbor1 = new Destination("school");
		Destination neighbor2 = new Destination("university");
		Street street1 = Mockito.mock(Street.class);
		Street street2 = Mockito.mock(Street.class);
		currDestination.AddNeighbor(neighbor1, street1);
		currDestination.AddNeighbor(neighbor2, street2);
		
		Driver driver = new Driver("Dan", currDestination);
		Destination nextDestination = methodStubsForMap.getRandomNeighbor(currDestination);
		map.moveDriver(driver);
		assertNotEquals(driver.location, currDestination);
	}
	
	
	// add destination to map
	// ensure new destination is not null
	@Test
	public void testAddingDestination(){
		Map map = new Map(7);
		Destination newDestination = map.addDestination("home");
		assertNotNull(newDestination);
	}
	
	
	
	// add new Street to map
	// check that new street is not null
	@Test
	public void testAddStreet(){
		Map map = new Map(10);
		Street street = map.addStreet("fourth ave");
		assertNotNull(street);
	}
	
	
	// create new driver and add it to map
	// ensure that driver is not null
	@Test
	public void testAddDriver(){
		Map map = new Map(1);
		Destination location = Mockito.mock(Destination.class);
		Driver driver = map.addDriver("Dan", location);
		assertNotNull(driver);
	}
	
	
	// create two destinations and a street
	// create a path from destination A to destination B via street
	// now get the street connection destination A and B and ensure it's the right street
	@Test
	public void testAddPath(){
		Destination A = new Destination("A");
		Destination B = new Destination("B");
		int random = methodStubsForMap.getRandom(10);
		Map map = new Map(random);
		Street street = new Street("Forbes Ave");
		map.createPath(A, B, street);
		Street streetCheck = A.getStreet(B);
		assertEquals(street, streetCheck);
	}
	
	
	// create new CitySimDriver
	// check that only one seed can be entered as argument
	@Test
	public void testIsArgValid(){
		CitySimDriver CitySim = new CitySimDriver(1);
		String args[] = {"1","2"};
		boolean isArgValid = CitySim.isArgValid(args);
		assertFalse(isArgValid);
		
	}
}








