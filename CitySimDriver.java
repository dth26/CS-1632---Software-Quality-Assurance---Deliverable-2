

public class CitySimDriver {
	public static void main(String [] args){
		if(isArgValid(args)){
			new CitySimDriver(Integer.parseInt(args[0]));	
		}else{
			System.out.println("Must enter exactly one integer seed argument!");
		}
	}
	
	public CitySimDriver(int seed){
		Map map = new Map(seed);
		
		// add new destinations to map
		Destination Mall = map.addDestination("Mall");
		Destination Bookstore = map.addDestination("Bookstore");
		Destination Coffee = map.addDestination("Coffee");
		Destination University = map.addDestination("University");
		Destination Outside = map.addDestination("Outside City");
		
		// add new street to mao
		Street Fourth = map.addStreet("Fourth Ave");
		Street Fifth = map.addStreet("Fifth Ave");
		Street Meow = map.addStreet("Meow St");
		Street Chirp = map.addStreet("Chirp St");
		
		// create five drivers
		Driver[] drivers = new Driver[5];
		for(int i=0; i<drivers.length; i++){
			Destination startDestination = map.getRandomDestination();
			drivers[i] = new Driver(Integer.toString(i), startDestination);
		}
		
		map.createPath(Outside, Mall, Fourth);
		map.createPath(Outside, University, Fifth);
		map.createPath(Mall, Bookstore, Fourth);
		map.createPath(Mall, Coffee, Meow);
		map.createPath(Bookstore, Outside, Fourth);
		map.createPath(Bookstore, University, Chirp);
		map.createPath(University, Coffee, Fifth);
		map.createPath(University, Bookstore, Chirp);
		map.createPath(Coffee, Mall, Meow);
		map.createPath(Coffee, Outside, Fifth);
		
		for(int i=0; i<drivers.length; i++){
			Driver driver = drivers[i];
		
			do{
				Destination from = driver.location;
				Destination to = map.moveDriver(driver);
				Street street = from.getStreet(to);
				
				System.out.println("Driver " + driver.driverName + " from " + from.name + " to " + to.name + " via " + street.name);
			}while(driver.location != Outside);
			System.out.println("-------------------------------------");
		}
	}
	
	
	
	public static boolean isArgValid(String [] args)
	{
		boolean validArg = true;
		
		if(args.length == 0 || args.length > 1)
		{
			validArg = false;
		}
		else
		{
			try{
				Integer.parseInt(args[0]);
			}catch(NumberFormatException x){
				validArg = false;
			}
		}
		
		return validArg;
	}
}
