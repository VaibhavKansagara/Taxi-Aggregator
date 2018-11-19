package iiitb.ess201a7.r16068;

import iiitb.ess201a7.a7base.*;
import static java.lang.Math.*;

public class Car16068 extends Car{
	private Location currlocation;
	private int Status;
	private Trip trip;
	
	Car16068(int fid, int speed){
		super(fid,speed);
		currlocation=new Location(0,0);
		setStatus(Idle);
		trip=null;
	}
	
	public int distSqrd(Location loc) {
		return (int) (pow(loc.getX() - currlocation.getX(), 2) + pow(loc.getY() - currlocation.getY(), 2));
	}
	
	
	
	public void setLocation(Location l) {
		currlocation.set(l.getX(),l.getY());
	}
	
	public Location getLocation() {
		return currlocation;
	}
	
	public void setStatus(int s) {
		Status=s;
	}
	
	public int getStatus() {
		return Status;
	}
	
	public void assignTrip(Trip trip) {
		this.trip=new Trip(trip.getStart(),trip.getDest());
		this.setStatus(Car.Booked);
	}
	
	public Trip getTrip() {
		return trip;
	}
	
	public Location getStart() {
		return trip.getStart();
	}

	public Location getDest() {
		return trip.getDest();
	}
	
	public void updateLocation(double deltaT) {
		if(Status == Idle) {
			return;
		}
		else if(Status == OnTrip) {
			if(getLocation().getX() == getDest().getX() && getLocation().getY() == getDest().getY()) {
				Status=Idle;
				return;
			}
			double r=deltaT*maxSpeed;
			
			int a=getDest().getX()-getLocation().getX();
			int b=getDest().getY()-getLocation().getY();
			int tempx=(int)(getLocation().getX()+r*a/sqrt(pow(a,2)+pow(b,2)));
			int tempy=(int)(getLocation().getY()+r*b/sqrt(pow(a,2)+pow(b,2)));
			if(r>sqrt(distSqrd(trip.getDest()))) {
				setLocation(trip.getDest());
				Status=Idle;
			}
			else {
				currlocation.set(tempx,tempy);
			}
		}
		else {
			if(getLocation().getX() == getStart().getX() && getLocation().getY() == getStart().getY()) {
				Status=OnTrip;
				return;
			}
			double r=deltaT*maxSpeed;
			int a=getStart().getX()-getLocation().getX();
			int b=getStart().getY()-getLocation().getY();
			int tempx=(int)(getLocation().getX()+r*a/sqrt(pow(a,2)+pow(b,2)));
			int tempy=(int)(getLocation().getY()+r*b/sqrt(pow(a,2)+pow(b,2)));
			if(r>sqrt(distSqrd(trip.getStart()))) {
				setLocation(trip.getStart());
				Status=OnTrip;
			}
			else {
				currlocation.set(tempx,tempy);
			}
		}
	}
}
