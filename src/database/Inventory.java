/*
Patrick Welch
CS 205 Restaurant Sim database

This is the inventory object that will hold the rows for each entry in the inventory dtaabase table
*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.BeanListHandler;

import java.sql.*;
import java.util.*;

//inventory object for each row in the database
public class Inventory{

	private int item_id;
	private int quantity;
	private int restaurant_id;


	//constructor
	public Inventory(int itemId, int quantity, int restaurantId){

		this.item_id = itemId;
		this.quantity = quantity;
		this.restaurant_id = restaurantId;

	}//end constructor

	//returns the itemID for the entry
	public int getItemID(){

		return itemId;

	}//end getItemID

	//returns the quantity of hte item
	public int getQuantity(){

		return quantity;

	}//end getQuantity

	//returns the restauarant id of the correspoding item and quantity in the entry
	public int getRestaurantID(){

		return restaurantId;

	}//end getRestaurantID

	//createNewInventory generates a new inventory for a new restauarant;
	public void createNewInventory(int restaurant_id){




	}//end createNewInventory

	public String toString(){

		String string = restaurantId + ", " + itemId + ", " + quantity;

	}



}//end Inventory