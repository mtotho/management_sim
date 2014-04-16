package mftoth.restaurantsim.logic;
import java.util.ArrayList;
import java.util.Random;

public class Customer{

private ArrayList<String> order;
private int items;
private int nextItem;
private String  cb =  "cheeseburger";
private String drink = "drink";
private String fries = "fries";
private boolean isRendered;
private Locations waypoint;
private Locations location;
private Restaurant restaurant;

	//Constructor()
	public Customer(Restaurant restaurant){
		isRendered=false;
		this.restaurant=restaurant;
		location = Locations.ENTRANCE;
		waypoint = Locations.FOODLINE;
	}//end: Constructor()

	public void update(){
		//if(location==waypoint){
		//	System.out.println("Have arrived");
		//	waypoint=Locations.MENSROOM;
		//}
	}

	public void getTraits(){



	}//end getTraits

	public void giveOrder(){

		Random random = new Random();

		items = random.nextInt() % 10;

		for (int i = 0; i < items + 1; i++){

			nextItem = ( random.nextInt() % 10 );
			
			


		}




	}//end giveOrder

	public void getOrder(){

		

	}//end getOrder()

	public void setWayPoint(Locations waypoint){
		this.waypoint=waypoint;
	}

	public Locations getWaypoint(){
		return waypoint;
	}
	public Locations getLocation(){
		return location;
	}
	public void setLocation(Locations location){
		this.location=location;
	}

	public boolean isRendered(){
		return isRendered;
	}

	public void setRendered(boolean rendered){
		isRendered=rendered;
	}


}