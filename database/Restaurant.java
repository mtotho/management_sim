/*
Patrick Welch, CS205 Final Project DB
3/16/14

This creates the database that the restaurant will query for menu items, inventory, and data


*/

import java.sql.*;

public class Restaurant{
	
	public static void main (String args[]){

		Connection c = null; //database connection
		Statement stmt = null; //database statement

		try{
			//CREATES DATABASE
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
		}//end try

		catch (Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}//end catch

		System.out.println("Opened database successfully");

		try{

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			//create the item table that holds, item name, price, and inventory quantity
			String sql = "CREATE TABLE RESTAURANT" +
						 "(ID INT PRIMARY KEY NOT NULL," +
						 "FOOD			TEXT	NOT NULL, " +
						 "PRICE			DOUBLE	NOT NULL, " +
						 "QUANTITY		INT		NOT NULL)";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		}//end try
		catch (Exception e) {

			System.err.println( e.getClass().getName() + ": " + e.getMessage() );

		}//end catch
		System.out.println("Table created succesffuly");



		try{ //add items to database

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);
			System.out.println("opened database successfully");

			stmt = c.createStatement();

			String sql = "INSERT INTO RESTAURANT (ID, FOOD, PRICE, QUANTITY)  " +
						 "VALUES (1, 'Hamburger', 1.50, 100);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO RESTAURANT (ID, FOOD, PRICE, QUANTITY) " +
				  "VALUES (2, 'Cheeseburger', 2.00, 100);";

			stmt.executeUpdate(sql);

			sql = "INSERT INTO RESTAURANT (ID, FOOD, PRICE, QUANTITY) " +
				  "VALUES (3, 'Fries', 1.75, 150);";

			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		}
		catch(Exception e){
			
			System.err.println( e.getClass().getName() + ": " + e.getMessage());

		}
		
		System.out.println("Records updated successfully");

		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM RESTAURANT;" );

			while (rs.next()) {
				String food = rs.getString("food");
				double price = rs.getDouble("price");
				int quantity = rs.getInt("quantity");

				System.out.println("FOOD = " + food);
				System.out.println("PRICE = " + price);
				System.out.println("QUANTITY = " + quantity);
				System.out.println();


			}

			rs.close();
			stmt.close();
			c.close();

		}
		catch(Exception e){
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			

		}
		System.out.println("Operation successful");
	}//end main
}//end class restaurant












