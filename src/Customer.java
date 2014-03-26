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
private Waypoint waypoint;

	//Constructor()
	public Customer(){
		isRendered=false;
	}//end: Constructor()

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

	public void setWayPoint(Waypoint waypoint){
		this.waypoint=waypoint;
	}

	public Waypoint getWaypoint(){
		return waypoint;
	}

	public boolean isRendered(){
		return isRendered;
	}

	public void setRendered(boolean rendered){
		isRendered=rendered;
	}
}