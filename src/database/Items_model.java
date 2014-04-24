/*
Patrick Welch
CS 205 Restaurant Sim database

This will be the row object for each item entry int the items table in the database
*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.*;

import java.sql.*;
import java.util.*;

public class Items_model implements Model{

	private int id;
	private String name;
	private double price;

	//constructor
	public Items_model(int id, String name, double price){

		System.out.println("ITEM ID: " + id);

		this.id = id;
		this.name = name;
		this.price = price;

	}//end constructor

	public Items_model(){

		
	}

	//sets the name for the item entry
	public void setName(String name){

		this.name = name;

	}//end setName();

	//returns the name for the item entry
	public String getName(){

		return name;

	}//end getName

	public String getTable(){
		return "Items";
	}

	//sets an ID for the item
	public void setID(int id){

		System.out.println("ITEM ID: " + id);

		this.id = id;

	}//end setID

	//returns the id for the item
	public int getID(){

		return id;

	}//end getID()

	public int getRestaurantID(){
		return 1;
	}

	//sets the price for the item
	public void setPrice(double price){

		this.price = price;

	}//end setprice

	//returns the price for the item
	public double getPrice(){

		return price;

	}//end getPrice()

	public String toString(){

		String string = id + ", '" + name + "', " + price;

		return string;
	}

}//end Items