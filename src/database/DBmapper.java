/*
Patrick Welch

DB Mapper can be called to query, update, and insert into the respective database tables
*/
package pjwelch.restaurantsim.database;

//import org.apache.commons.dbutils.DbUtils;
//import org.apache.commons.dbutils.QueryRunner;
//import org.apache.commons.dbutils.BeanListHandler;
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


	public DBmapper(){

		this.dbpath = dbpath;
		this.qRunner = new QueryRunner();

		System.out.println("dbmapper constructor called");
		//System.out.println(this.connect());

		File f = new File("res/sim.db");

		if (!f.exists()){

			System.out.println("1");
			new DatabaseInit();
			System.out.println("2");
			new DatabasePop();

		}

		connect();

	}
	/*public boolean DBcheck(){

		boolean exists = true;
		ArrayList<String> list = new ArrayList<String>();
		String databases = "";

		try{
			Class.forName(dbDriver);
			conn = DriverManager.getConnection("jdbc:sqlite:src/database/sim.db");
			//stmt = conn.createStatement();
			meta = conn.getMetaData();
			rs = meta.getCatalogs();

			while(rs.next()){

				databases = rs.getString("TABLE_CAT");
				list.add(databases);
				System.out.println(databases);

			}
			System.out.println("this is" + databases.toString() + " databases");
			if(list.contains("sim.db")){
				return exists;
			}
			else{

				exists = false;			
			}

			rs.close();
			conn.close();
			stmt.close();	
			return exists;		

		}	
		catch(Exception e){
			System.out.println("DSFEFWGSGFSDF");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "DBCHECK");
			return false;

		}

	}*/

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

	public List select(Model param){

		String selectStatement = "SELECT * FROM " + param.getTable();

		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			/*for (int i = 0; i < beans.size(); i++){

				Class<?>clazz bean = (clazz) beans.get(i);
				bean.print();

				return beans;
			}*/

			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}



	}
	public List selectData(Model param, Player_model player){

		System.out.println("PLAYER ID DB CALL: " + player.getID());

		String selectStatement = "SELECT * FROM " + param.getTable() + " WHERE RESTAURANT_ID = " + player.getID();

		System.out.println(selectStatement);
		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			/*for (int i = 0; i < beans.size(); i++){

				Class<?>clazz bean = (clazz) beans.get(i);
				bean.print();

				return beans;
			}*/
			System.out.println(beans);
			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}



	}
	public List selectPlayer(Model param, String name){

		String selectStatement = "SELECT * FROM PLAYER WHERE NAME = '" + name + "'";

		try{

			ResultSetHandler h = new BeanListHandler(param.getClass());
			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, h);

			/*for (int i = 0; i < beans.size(); i++){

				Class<?>clazz bean = (clazz) beans.get(i);
				bean.print();

				return beans;
			}*/

			return beans;

		}
		catch(Exception e){
			System.out.println("SELECT");
			System.err.println(e.getClass().getName() + ": " + e.getMessage() + "SELECT");
			return null;

		}


	}


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

	public boolean updateRestaurant(List<Restaurant_model> param){

		String updateStatement  = "UPDATE " + param.get(0).getTable()+ " SET MONEY = " + param.get(0).getMoney() + ", TIME = " + param.get(0).getTime() + " WHERE RESTAURANT_ID = " + param.get(0).getRestaurantID() + ";" ;

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

	public boolean updateInventory(List<Inventory_model> params){

		boolean success = true;

		try{

			for(int i = 0; i < params.size(); i++){

				String updateStatement = "UPDATE " + params.get(0).getTable() + " SET QUANTITY = " + params.get(i).getQuantity() +  " WHERE RESTAURANT_ID = " + params.get(i).getRestaurantID() + " AND ITEM_ID = " + params.get(i).getItemID() + ";";

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

	public boolean newPlayer(int playerID){

		boolean success = true;
		
		try{

			insert(new Restaurant_model(playerID, playerID, "restaurant", 0, 0));
			
			items = select(new Items_model());
			for(int i = 0; i < items.size(); i++){

				insert(new Inventory_model(i, playerID, 100));


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