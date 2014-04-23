/*
Patrick Welch
CS 205 Final Project DB
3/16/14

This populates the database sim.db tables created by DatabaseInit

*/
package pjwelch.restaurantsim.database;

import java.sql.*;

public class DatabasePop{

	public DatabasePop(){

		Connection c; //connection
		Statement stmt; // sql statement


		try{//populates the tables with the included data

			Class.forName("org.sqlite.JDBC"); //returns the jdbc classes
			c = DriverManager.getConnection("jdbc:sqlite:res/sim.db");
	
			System.out.println("opened database successfully");

			stmt = c.createStatement();

			String sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (0, 'Squirtle Burger', 20.00);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (1, 'Pikachu Sandwhich', 1.50);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (2, 'Filet O'' Feebas', 2.00);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (3, 'Magikarp n'' Chips', 1.25);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (4, 'Pidgey Wings', 2.00);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (5, 'Slowpoke Tail', 2.25);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO ITEMS (ID, NAME, PRICE) " +
				  "VALUES (6, 'Miltank Milk', 2.25);";

			stmt.executeUpdate(sql);


			stmt.close();
			c.close();

		}//end try

		catch(Exception e){
			System.out.println("Here");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}//end catch

	}//end main

}//end DatabasePop