/*
Patrick Welch
CS 205 Restaurant Sim database

This will be the class that queries, updates, and sets the details of the player table
*/

import java.sql.*;

public class PlayerDB{

	Connection c; //database connection
	Statement stmt; //sql statement

	public void Update(){


	}//end update

	//queryID returns the player ID based on the player name
	public int queryID(String playerName){


		try{

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER;");

			while (rs.next()) {

				int id = rs.getInt("ID");
				String name = rs.getString("NAME");

				if (name.equals(playerName())){

					return id;

				}//end if

			}//end while


			rs.close();
			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch

	}//end queryID

	//queryName returns the player name based on the ID
	public String queryName(int playerID){

		try{

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);

			stmt = c.createStatemet();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER:");

			while (rs.next()) {

				int id = rs.getInt("ID");
				String name = rs.getString("NAME");

				if(id == playerID){

					return name;

				}//end if


			}//end while

		rs.close();
		stmt.close();
		c.close();

		}//end try

	}//end queryName


	//add entry adds a new player to the database with an ID and name
	public void addEntry(String name){

		int id = 0;
		try{

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			System.out.println("Opened database successfully");


			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER");

			while(rs.next()){

				if(id < rs.getInt("ID")){

					id = rs.getInt("ID");
				}


			}

			id++;

			String sql = "INSERT INTO PLATER(ID, NAME) " +
						 "VALUES (" + id + ", " + name + ");";

			stmt.executeUpdate(sql);

			rs.close();
			stmt.close();
			c.commit();
			c.close();


		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch
		
	}//end addEntry

}//end Player
