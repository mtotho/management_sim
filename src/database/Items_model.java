/*
Patrick Welch
CS 205 Restaurant Sim database

This will be the row object for each item entry int the items table in the database
*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.BeanListHandler;

import java.sql.*;
import java.util.*;

class Items_model{

	private int id;
	private String name;
	private double price;

	//constructor
	public Items_model(int id, String name, double price){

		this.id = id;
		this.name = name;
		this.price = price;

	}//end constructor

	//sets the name for the item entry
	public void setName(String name){

		this.name = name;

	}//end setName();

	//returns the name for the item entry
	public String getName(){

		return name;

	}//end getName

	//sets an ID for the item
	public void setID(int id){

		this.id = id;

	}//end setID

	//returns the id for the item
	public int getID(){

		return id;

	}//end getID()

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

	}

}//end Items