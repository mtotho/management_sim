/*
Patrick Welch
CS 205 Restaurant Sim database

This will be the class that queries, updates, and sets the details of the Inventory table
*/

import java.sql.*

public class InventoryDB{

	Connection c;
	Statement stmt;

	//Update, updates the inventory quantity given an item ID and restaurant ID
	public void UpdateInventory(int itemID, int restaurantID, int newQTY){

		try{

			c = DriverManger.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();

			stmt.executeUpdate("UPDATE INVENTORY SET QUANTITY = " + newQTY + 
							  " WHERE RESTAURANT ID = " + restaurantID + " and ITEM_ID = " + itemID);

			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() +": " + e.getMessage());

		}// end catch

	}//end UpdateInventory

	//QueryQuantity returns the quantity of an item based on the provided ItemID and restauarantID
	public int QueryQuantity(int itemID, int restaurantID){

		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM INVENTORY");

			while(rs.next()){

				if(itemID == rs.getInt("ITEM_ID") && restaurantID == (rs.getInt("RESTAURANT_ID"))){

					return rs.getInt("QUANTITY");

				}//end if

			}//end while

			rs.close();
			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());


		}//end catch

	}//end QueryQuantity

	//addEntry adds a new item with quantity to the inventory
	public void addEntry(int restaurantID, int itemID, int quantity){

		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();

			String sql = "INSERT VALUES INTO INVENTORY " +
						 "VALUES (" + restaurantID + ", " + itemId + ", " + quantity + ");";

			stmt.executeUpdate(sql);

			stmt.close();
			c.close;

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch		


	}//end addEntry

	//createNewInventory is invoked when a new player generates a new restaurant
	//creating a seperate inventory for the new restaurant
	public void createNewInventory(int restaurantID){

		try{

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:sim.db");

			stmt = c.createStatement();
			Result rs = stmt.executeQuery("SELECT * FROM ITEMS;");

			while(rs.next()){

				int id = rs.getInt("ID");

				sql = "INSERT INTO INVENTORY " + 
					  "VALUES (" + restaurantID + ", " + id + ", " + "100);";

				stmt.executeUpdate(sql);

			}//end while

			rs.close();
			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catch

	}//end CreateNewInventory

}//end Inventory