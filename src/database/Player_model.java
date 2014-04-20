/*
Patrick Welch

This will be the object for the row entry in the database for the player table
*/
package pjwelch.restaurantsim.database;

public class Player_model implements Model{

	private String name;
	private int id;

	public Player_model(int id, String name){

		this.name = name;
		this.id = id;

	}
	public Player_model(){



	}

	public void setName(String name){

		this.name = name;

	}

	public String getName(){

		return name;

	}

	public String getTable(){
		return "Player";
	}

	public void setId(int id){

		this.id = id;

	}

	public int getRestaurantID(){
		return 0;
	}

	public int getID(){

		return id;
		
	}

	public String toString(){

		String string = id + ", '" + name + "'";

		return string;
	}

}