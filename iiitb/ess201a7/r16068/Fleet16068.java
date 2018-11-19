package iiitb.ess201a7.r16068;
import java.util.ArrayList;
import iiitb.ess201a7.a7base.*;


public class Fleet16068 extends Fleet{
	static private int idno = 1;
	private ArrayList<Car> ListOfCars;
	public Fleet16068(String col){
		super(16068,col);
		ListOfCars=new ArrayList<Car>();
		//idno=1;
	}
	
	public void addCar(int speed) {
		String carIdString = Integer.toString(idno);
		int newCarId = Integer.parseInt("16068"+carIdString);
		idno+=1;
		ListOfCars.add(new Car16068(newCarId,speed));
	}
	
	public Car findNearestCar(Location loc) {
		int minimum = Integer.MAX_VALUE;
		Car minCar = null;
		for(Car car: ListOfCars) {
			if(car.getStatus() == Car.Idle) {
				if(car.distSqrd(loc) < minimum) {
					minimum = car.distSqrd(loc);
					minCar = car;
				}
			}
			
		}
		return minCar;
	}
	
	public ArrayList<? extends Car> getCars(){
		return ListOfCars;
	}
	
}
