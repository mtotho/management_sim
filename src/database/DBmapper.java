/*
Patrick Welch

DB Mapper can be called to query, update, and insert into the respective database tables
*/
package pjwelch.restaurantsim.database;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class DBmapper{

	private String dbpath = "jdbc:sqlite:sim.db";
	private String dbDriver = "org.sqlite.JDBC";
	private String sql = "";

	public Connection c = null; 
	public QueryRunner qRunner;
	public Connection conn = null;
	public Statement stmt;
	public DatabaseMetaData meta;
	public ResultSet rs;

	public List<Items_model> items;

	public Inventory_model inventory;


	public DBmapper(){

		this.dbpath = dbpath;
		this.qRunner = new QueryRunner();

		System.out.println("dbmapper constructor called");
		//System.out.println(this.connect());

		File f = new File("res/sim.db");

		//checks to see if database exists, if no creates
		if (!f.exists()){

			System.out.println("1");
			new DatabaseInit();
			System.out.println("2");
			new DatabasePop();

		}

		connect();

	}
	//opens connection to the database
	public synchronized void connect(){

		try{
			
			Class.forName(dbDriver);
			//this.close();
			System.out.println(DriverManager.getConnection("jdbc:sqlite:res/sim.db"));
			c = DriverManager.getConnection("jdbc:sqlite:res/sim.db");
			//return true;		
		}
		catch(Exception e){
			
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "CONNECT");
			//return false;

		}
		
	}
	//closes the connection
	public synchronized void close() {

		try{

			if (this.c != null){
				this.c.close();
				this.c = null;
			}

		}
		catch(Exception e){
			System.out.println("CLOSE!!!");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "CLOSE");

		}

	}
	//generic insert statement for all objects
	public boolean insert(Model obj){

		String insertStatement = "INSERT INTO " + obj.getTable() + " VALUES (" + obj.toString() +");";

		try{

			Class<?> clazz = obj.getClass();
			qRunner.update(this.c, insertStatement);
			return true;

		}
		catch(Exception e){
			System.out.println("INSERT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "INSERT");
			return false;

		}

	}
	//generic select statement for the database, returns all rows in a table
	public List select(Model param){

		String selectStatement = "SELECT * FROM " + param.getTable();

		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}



	}
	//selects game data in the program based on the playerID given with newgame or loadgame
	public List selectData(Model param, Player_model player){

		System.out.println("PLAYER ID DB CALL: " + player.getID());

		String selectStatement = "SELECT * FROM " + param.getTable() + " WHERE RESTAURANT_ID = " + player.getID();

		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			System.out.println(beans);
			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}



	}
	//selects the player from the database based on name
	public List selectPlayer(Model param, String name){

		String selectStatement = "SELECT * FROM PLAYER WHERE NAME = '" + name + "'";

		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}


	}

	//generic update function
	public boolean update(Model param){

		String updateStatement  = "UPDATE " + param + " WHERE RESTAURANT_ID = " + param.getRestaurantID() + ";" ;

		boolean success = true;
		try{

			qRunner = new QueryRunner();

			qRunner.update(this.c, updateStatement, param);


		}
		catch(Exception e){
			System.out.println("UPDATE");
			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "UPDATE");

		}

		return success;

	}
	//update function for restaurant data
	public boolean updateRestaurant(List<Restaurant_model> param){

		String updateStatement  = "UPDATE " + param.get(0).getTable()+ " SET MONEY = " + param.get(0).getMoney() + ", TIME = " + param.get(0).getTime() + " WHERE RESTAURANT_ID = " + param.get(0).getID() + ";" ;

		boolean success = true;
		try{

			qRunner = new QueryRunner();

			qRunner.update(this.c, updateStatement);


		}
		catch(Exception e){
			System.out.println("UPDATE");
			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "UPDATE");

		}

		return success;
	}
	//generic update function for batch updates from an aray
	public boolean update(List<Model> params){

		boolean success = true;

		try{
			String updateStatement = "UPDATE " + params.get(0) + " WHERE RESTAURANT_ID = " + params.get(0).getRestaurantID() + ";";

			qRunner = new QueryRunner();
			qRunner.update(this.c, updateStatement, params);



		}
		catch(Exception e){
			System.out.println("UPDATE[]");
			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "UPDATE[]");

		}


		return success;
	}
	//inventory update statement for updateing player inventory
	public boolean updateInventory(List<Inventory_model> params){

		boolean success = true;

		try{

			for(int i = 0; i < params.size(); i++){

				String updateStatement = "UPDATE " + params.get(0).getTable() + " SET QUANTITY = " + params.get(i).getQuantity() + ", AMOUNT_SOLD = " + params.get(i).getAmount_Sold() + " WHERE RESTAURANT_ID = " + params.get(i).getRestaurantID() + " AND ITEM_ID = " + params.get(i).getItemID() + ";";

				qRunner = new QueryRunner();
				qRunner.update(this.c, updateStatement);

			}
			



		}
		catch(Exception e){
			System.out.println("UPDATE[]");
			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "UPDATE[]");

		}


		return success;
	}
	//generates needed data when a new player is created
	public boolean newPlayer(int playerID){

		boolean success = true;
		
		try{

			insert(new Restaurant_model(playerID, "restaurant", playerID, 0, 0));
			
			items = select(new Items_model());
			for(int i = 0; i < items.size(); i++){

				insert(new Inventory_model(playerID, i, 100, 0));


			}

			success = true;

		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			success = false;

		}




		return success;
	}

}