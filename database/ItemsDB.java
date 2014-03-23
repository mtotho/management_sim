/*
Patrick Welch
CS 205 Restaurant Sim database

This will be the class that queries, updates, and sets the details of the items table
*/

import java.sql.*;

public class ItemsDB{

	Connection c; //connection to db
	Statement stmt; //sql statement


	public void Update(){

		




	}
	//QueryPrice returns an items price based on the item name;
	public double QueryPrice(String itemName){

		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ITEMS" );

			while (rs.next()) {

				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				double price = rs.getString("PRICE");

				if(name.equals(itemName)){

					return price;

				}//end if

			}//end while

			rs.close();
			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end Catch

	}//end QueryPrice

	public int QueryID(String itemName){

		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from ITEMS");

			while(rs.next()){

				int id = rs.getInt("ID");
				String name = rs.getString("NAME");

				if(name.equals(itemName)){

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

	}//end QueryID

	//addEntry adds a new item row to the Items table
	public void addEntry(int id, String name, double price){

	
		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			stmt = c.createStatement();

			String sql = "INSERT INTO ITEMS " +
						 "VALUES (" + id + ", '" + name + "', " + price ");"; 

			stmt.executeUpdate(sql);

			stmt.close();
			c.close();

		}//end try
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}//end catcg

	}//end addEntry

	
}//end Items