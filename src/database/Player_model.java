/*
Patrick Welch

This will be the object for the row entry in the database for the player table
*/

class Player_model{

	private String name;
	private int id;

	public Player_model(int id, String name){

		this.name = name;
		this.id = id;

	}

	public void setName(String name){

		this.name = name;

	}

	public String getName(){

		return name;

	}

	public void setId(int id){

		this.id = id;

	}

	public int getId(){

		return id;
		
	}

	public String toString(){

		String string = id + ", '" + name + "'";
	}

}