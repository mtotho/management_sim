/*
Patrick Welch

This will be the object for the row entry in the database for the player table
*/
package pjwelch.restaurantsim.database;

public interface Model{

	
	public String getTable();

	//public void setId(int id);

	//public int getId();

	public String toString();

	public int getRestaurantID();

}