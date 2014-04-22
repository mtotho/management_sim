/*
Patrick Welch
CS 205 Restaurant Sim Database
3/16/14

This file initializes the database for the restaurant simulator, named sim.database
*/

package pjwelch.restaurantsim.database;
import java.sql.*;

public class DatabaseInit{

	public DatabaseInit (){

		Connection c;
		Statement stmt;

		try{
			//Creates Database
			Class.forName("org.sqlite.JDBC");
		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

		try{
			c = DriverManager.getConnection("jdbc:sqlite:res/sim.db");
			
			System.out.println("Opened Database successfully");

			stmt = c.createStatement();

			String sql = "CREATE TABLE PLAYER" +
						 "(ID INT PRIMARY KEY   NOT NULL," +
						 "NAME 			TEXT	NOT NULL)";

			

			stmt.executeUpdate(sql);
			
			System.out.println("player created");

			sql = "CREATE TABLE RESTAURANT" +
				  "(RESTAURANT_ID INT PRIMARY KEY NOT NULL," +
				  "NAME 		TEXT	NOT NULL, " +
				  "PLAYER_ID	INT 	NOT NULL, " +
				  "MONEY DOUBLE NOT NULL, " +
				  "TIME INT NOT NULL, " +
				  "FOREIGN KEY(PLAYER_ID) REFERENCES PLAYER(ID))";

			stmt.executeUpdate(sql);

			System.out.println("restaurant created");

			sql = "CREATE TABLE ITEMS" +
				  "(ID INT PRIMARY KEY NOT NULL," +
				  "NAME 		TEXT	NOT NULL, " +
				  "PRICE		DOUBLE	NOT NULL)";


			stmt.executeUpdate(sql);

			System.out.println("items created");

			sql = "CREATE TABLE INVENTORY" +
				  "(RESTAURANT_ID INT NOT NULL," +
				  "ITEM_ID INT 	NOT NULL, " +
				  "QUANTITY	INT NOT NULL, " +
				  "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANT(ID), " +
				  "FOREIGN KEY(ITEM_ID) REFERENCES ITEMS(ID)" +
				  "PRIMARY KEY(RESTAURANT_ID, ITEM_ID))";

			stmt.executeUpdate(sql);

			System.out.println("inventory created");

			sql = "CREATE TABLE EMPLOYEE" +
				  "(RESTAURANT_ID INT NOT NULL, " +
				  "EMPLOYEE_ID INT NOT NULL, " +
				  "EMPLOYEE_NAME TEXT NOT NULL, " +
				  "PRIMARY KEY(RESTAURANT_ID, EMPLOYEE_ID), "+
				  "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANT(ID))";
				  

			stmt.executeUpdate(sql);

			System.out.println("employee table created");

			sql = "CREATE TABLE TRANSACTIONS" +
				  "(RESTAURANT_ID INT NOT NULL, " +
				  "TRANSACTION_ID INT NOT NULL, " +
				  "TRANSACTION_PRICE DOUBLE NOT NULL, " +
				  "PRIMARY KEY(RESTAURANT_ID, TRANSACTION_ID), " +
				  "FOREIGN KEY(RESTAURANT_ID) REFERENCES RESTAURANT(ID))";

			stmt.executeUpdate(sql);

			System.out.println("transaction table created");

			stmt.close();
			c.close();
		}//end try
		catch (Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0); 

		}//end catch

		System.out.println("Created Database Successfully");

		
	}//end Main
}//end DatabaseInit