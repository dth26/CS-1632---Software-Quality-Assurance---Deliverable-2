import static org.junit.Assert.*;
import java.util.Random;
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
		Map map = mock(Map.class);
		
		
		CitySimDriver citySim = Mockito.mock(CitySimDriver.class);
		when(citySim.getRandomGenerator()).thenReturn(new Random(5));
		Random rand = citySim.getRandomGenerator();
	
		doReturn(new Destination("home")).when(map).getRandomDestination(rand);

		Destination destination1 = map.getRandomDestination(rand);
		Destination destination2 = new Destination("home");
		
		for(int i=0; i<10; i++){
			destination1.AddNeighbor(Mockito.mock(Destination.class), Mockito.mock(Street.class) );
		}
		
		for(int i=0; i<11; i++){
			destination2.AddNeighbor(Mockito.mock(Destination.class), Mockito.mock(Street.class) );
		}
		
		assertNotSame(destination1.neighbors, destination2.neighbors);
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
		
		doReturn(new Street("Fourth Ave")).when(destination1).getStreet(destination1);
		Street street = destination1.getStreet(destination1);
		
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
		Map map = new Map();
		CitySimDriver CitySim = Mockito.mock(CitySimDriver.class);
		when(CitySim.getRandomGenerator()).thenReturn(new Random(5));
		map.addDestination("home");
		map.addDestination("school");
		Destination destination = map.getRandomDestination(CitySim.getRandomGenerator());
		assertNotNull(destination);
	}
	
	
	// create new destination and add two neighbors to it
	// add destination to map
	// check that getRandomNeighbor returns a neighbor that is not null
	@Test
	public void testGetRandomNeighbor(){
		Map map = Mockito.mock(Map.class);
		when(map.getRandomDestination(new Random(5))).thenReturn(new Destination("home"));
		
		
		Destination destination = new Destination("home");
		Destination neighbor1 = new Destination("mall");
		Destination neighbor2 = new Destination("bookstore");
		
		Street street1 = Mockito.mock(Street.class);
		Street street2 = Mockito.mock(Street.class);
		
		destination.AddNeighbor(neighbor1, street1);
		destination.AddNeighbor(neighbor2, street2);
		
		CitySimDriver CitySim = Mockito.mock(CitySimDriver.class);
		when(CitySim.getRandomGenerator()).thenReturn(new Random(5));
		
		Map m = new Map();
		Destination randomNeighbor = m.getRandomNeighbor(destination, CitySim.getRandomGenerator());
		assertNotNull(randomNeighbor);
	}
	
	
	// create new driver with starting position
	// ensure that moveDriver sets the current location of driver to a new destination
	@Test
	public void testMoveDriver(){
		Map map = new Map();
		Destination currDestination = new Destination("home");
		Destination neighbor1 = new Destination("school");
		Destination neighbor2 = new Destination("university");
		
		Street street1 = Mockito.mock(Street.class);
		Street street2 = Mockito.mock(Street.class);
		
		currDestination.AddNeighbor(neighbor1, street1);
		currDestination.AddNeighbor(neighbor2, street2);
		
		CitySimDriver CitySim = Mockito.mock(CitySimDriver.class);
		when(CitySim.getRandomGenerator()).thenReturn(new Random(5));
		
		Driver driver = new Driver("Dan", currDestination);
		
		map.moveDriver(driver, CitySim.getRandomGenerator());
		assertNotEquals(driver.location, currDestination);
	}
	
	
	// add destination to map
	// ensure new destination is not null
	@Test
	public void testAddingDestination(){
		Map map = new Map();
		Destination newDestination = map.addDestination("home");
		assertNotNull(newDestination);
	}
	
	
	
	// add new Street to map
	// check that new street is not null
	@Test
	public void testAddStreet(){
		Map map = new Map();
		Street street = map.addStreet("fourth ave");
		assertNotNull(street);
	}
	
	
	// create new driver and add it to map
	// ensure that driver is not null
	@Test
	public void testAddDriver(){
		Map map = new Map();
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

		Map map = new Map();
		Street street = new Street("Forbes Ave");
		map.createPath(A, B, street);
		Street streetCheck = A.getStreet(B);
		assertEquals(street, streetCheck);
	}
	
	
	// create new CitySimDriver
	// check that only one seed can be entered as argument
	@Test
	public void testIsArgValid(){
		String args[] = {"1","2"};
		boolean isArgValid = CitySimDriver.isArgValid(args);
		assertFalse(isArgValid);
		
	}
}








