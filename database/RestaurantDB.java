/*
Patrick Welch
CS 205 Restaurant Sim Database
3/16/14

This class will update, query, and add enteries to the Restaurant table in the database
*/
package pjwelch.restaurantsim.database;

import java.sql.*;

public class RestaurantDB{

	Connection c;
	Statement stmt;

	//returns a restauarant ID based on the playerID
	public int QueryRestaurantID(int playerID){

		int id = 0;

		try{
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FORM ITEMS");

			while(rs.next()){

				if(rs.getInt("PLAYER_ID") == playerID){

					id = rs.getInt("ID");

				} 

			}

			rs.close();
			stmt.close();
			c.close();

		}//end try

		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch

		return id;

	}//end QueryRestauarantID

	//creates a new restauarant based on the player ID
	public void NewRestaurant(int playerID, String name){

		int restaurantID = 0;

		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM RESTAURANT");

			while(rs.next()){

				if(restaurantID < rs.getInt("RESTAURANT_ID")){

					restaurantID = rs.getInt("RESTAURANT_ID");

				}//end if


			}//end while

			restaurantID++;

			String sql = "INSERT VALUES INTO RESTAURANT(ID, NAME, PLAYER_ID) " +
						"VALUES (" + restaurantID + ", " + name + ", " + playerID + ");";

			stmt.executeUpdate(sql);

			rs.close();
			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch

	}//end NewRestauarant
	
}//end Restaurant
