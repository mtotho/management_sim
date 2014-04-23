/*
Patrick Welch
CS 205 Restaurant Sim database

This is the inventory object that will hold the rows for each entry in the inventory dtaabase table
*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.*;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.BeanListHandler;

import java.sql.*;
import java.util.*;

//inventory object for each row in the database
public class Inventory_model implements Model{

	private int item_id;
	private int quantity;
	private int restaurant_id;
	private int amount_sold;


	//constructor
	public Inventory_model( int restaurant_id, int item_id, int quantity, int amount_sold){

		this.item_id = item_id;
		this.quantity = quantity;
		this.restaurant_id = restaurant_id;
		this.amount_sold = amount_sold;

	}//end constructor
	public Inventory_model(){

		
	}

	public String getTable(){
		return "Inventory";
	}
	//returns the itemID for the entry
	public int getItemID(){

		return item_id;

	}//end getItemID

	//returns the quantity of hte item
	public int getQuantity(){

		return quantity;

	}//end getQuantity
	public void setQuantity(int newQuantity){

		quantity = newQuantity;


	}

	//returns the restauarant id of the correspoding item and quantity in the entry
	public int getRestaurantID(){

		return restaurant_id;

	}//end getRestaurantID

	public String toString(){

		String string = restaurant_id + ", " + item_id + ", " + quantity + ", " + amount_sold;

		return string;

	}
	public void setRestaurant_id(int restaurant_id){

		this.restaurant_id = restaurant_id;

	}

	public void setItem_id(int item_id){


		this.item_id = item_id;
	}
	public void setAmount_Sold(int amount_sold){

		this.amount_sold = amount_sold;

	}

	public int getAmount_Sold(){

		return amount_sold;

	}



}//end Inventory