package mftoth.restaurantsim.logic;

import mftoth.restaurantsim.ogl.*;
import pjwelch.restaurantsim.database.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Customer{

private ArrayList<Items_model> order;
private int items;
private int nextItem;
private boolean isRendered;
private Locations waypoint;
private Locations location;
private Restaurant restaurant;
public DBmapper db;
private List<Items_model> menu;
public Items_model model;
private GLCustomer glcust;


	//Constructor()
	public Customer(Restaurant restaurant, List<Items_model> menu){
		isRendered=false;
		this.restaurant=restaurant;
		location = Locations.ENTRANCE;
		waypoint = Locations.FOODLINE;
		this.db = db;
		this.menu = menu;

	}//end: Constructor()

	public void update(){
		//if(location==waypoint){
		//	System.out.println("Have arrived");
		//	waypoint=Locations.MENSROOM;
		//}
	}

	public void setGLCustomer(GLCustomer glcust){
		this.glcust=glcust;
	}

	public GLCustomer getGLCustomer(){
		return glcust;
	}
	
	public void getTraits(){



	}//end getTraits

	public void giveOrder(){


		order = new ArrayList<Items_model>();

		Random random = new Random();

		items = (random.nextInt() % 6) + 1;

		for (int i = 0; i < items + 1; i++){

			nextItem = ((random.nextInt() % 6) + 1);
			
			order.add(menu.get(i));

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