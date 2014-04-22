/*
Patrick Welch
CS 205 Restaurant Sim Database
3/16/14


*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.*;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.BeanListHandler;

import java.sql.*;
import java.util.*;


//restaurant object for the database table;
public class Restaurant_model implements Model{
	private int restaurant_id; //restaurant id
	private int player_id; //player id
	private String name;
	private double money;
	private int time;

	//constructor()
	public Restaurant_model(int restaurant_id, String name, int player_id, double money, int time){
		System.out.println(restaurant_id);

		this.restaurant_id = restaurant_id;
		this.player_id = player_id;
		this.name = name;
		this.money = money;
		this.time = time;

	}//end constructor
	public Restaurant_model(){


	}
	//retrieves playerID
	public int getPlayerID(){
		return player_id;
	}//end getPlayerID()
	//setsID for the restaurant table row
	public void setRestaurant_id(int restaurant_id){

		System.out.println("RESTAURANT ID : " + restaurant_id);
		this.restaurant_id = restaurant_id;
	}//end setID
	public void setID(int id){

		this.restaurant_id = id;
		this.player_id = id;

	}
	public int getRestaurantID(){

		return restaurant_id;

	}

	//sets the player id for the restaurant table row
	public void setPlayer_id(int player_id){

		this.player_id = player_id;

	}//end setPlayerID

	public void setRestaurantName(String name){
		System.out.println("RESTAURANT NAME: " + name);

		this.name = name;
	
	}

	public int getID(){
		return restaurant_id;

	}
	public void setName(String name){


		this.name = name;
	}

	public String getName(){

		return name;

	}

	public String getTable(){
		return "Restaurant";
	}

	public String toString(){

		String string = restaurant_id + ", '" + name + "', " + player_id + ", " + money + ", " + time;
		return string;
	}

	public void setTime(int time){

		this.time = time;
	}

	public int getTime(){

		return time;
	}

	public void setMoney(double money){

		this.money = money;
	}

	public double getMoney(){

		return money;

	}


}//end restaurant	

	