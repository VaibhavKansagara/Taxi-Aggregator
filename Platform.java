import java.util.ArrayList;

import iiitb.ess201a7.a7base.*;

public class Platform {

// all the methods in this class need to be implemented
	private ArrayList<Fleet> ListOfFleets;
	public Platform() {
		ListOfFleets=new ArrayList<Fleet>();
	}

	public void addFleet(Fleet f) {
		ListOfFleets.add(f);
	}

	// for a request defined as a Trip, find the best car by checking each of its fleets
	// and assigns the car to this trip
	public Car assignCar(Trip trip) {
		int dist = Integer.MAX_VALUE;
		Car car = null, minimum = null;
		
		for(Fleet f: ListOfFleets) {
			car = f.findNearestCar(trip.getStart());
			try {
				if(dist > car.distSqrd(trip.getStart())) {
					minimum = car;
					dist = car.distSqrd(trip.getStart());
				}
			}
			catch (NullPointerException e) {
				
			}
		}
		
		try {
			minimum.assignTrip(trip);
		
		}
		catch (NullPointerException e) {
			
		}
		
		return minimum;
	}

	// returns list of all cars (in all the fleets) managed by this platform
	public ArrayList<Car> findCars() {
		ArrayList<Car> temp=new ArrayList<Car>();
		for(Fleet f:ListOfFleets) {
			temp.addAll(f.getCars());
		}
		return temp;
	}
}
