/*
Patrick Welch
CS 205 Restaurant Sim Database
3/16/14


*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.BeanListHandler;

import java.sql.*;
import java.util.*;


//restaurant object for the database table;
public class Restaurant_model{
	private int id; //restaurant id
	private int playerId; //player id
	private String name;
	//constructor()
	public Restaurant_model(int playerId, int id){
		this.id = id;
		this.playerId = playerId;
	}//end constructor
	//retrieves restaurant id
	public int getID(){
		return id;
	}//end getID
	//retrieves playerID
	public int getPlayerID(){
		return playerID;
	}//end getPlayerID()
	//setsID for the restaurant table row
	public void setID(int id){
		this.id = id;
	}//end setID

	//sets the player id for the restaurant table row
	public void setPlayerID(int playerId){

		this.playerId = playerId;

	}//end setPlayerID

	public void setName(String name){

		this.name = name;
	
	}

	public String getName(){

		return name;

	}

	public String toString(){

		String string = id + ", '" + name + "', " + playerId;

	}

}//end restaurant	

	