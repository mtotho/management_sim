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

public class DBmapper{

	private String dbpath = "jdbc:sqlite:sim.db";
	private String dbDriver = "org.sqlite.JDBC";
	private String sql = "";

	public Connection c = null; 
	public QueryRunner qRunner;


	public DBmapper(){

		this.dbpath = dbpath;
		this.qRunner = new QueryRunner();

		
			

		if (!this.connect()){

			new DatabaseInit();
			new DatabasePop();

		}


	}

	public synchronized boolean connect() {

		try{
			Class.forName(dbDriver);
			this.close();
			c = DriverManager.getConnection(dbpath);
			return true;		
		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;

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

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

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

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;

		}

	}

	public List select(Class<?> clazz, Model param){

		String selectStatement = "SELECT * FROM " + clazz;

		try{

			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.c, selectStatement, new BeanListHandler(clazz));

			/*for (int i = 0; i < beans.size(); i++){

				Class<?>clazz bean = (clazz) beans.get(i);
				bean.print();

				return beans;
			}*/

			return beans;

		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
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

			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

		return success;

	}
	public boolean update(Class<?> clazz, List<Model> params){

		boolean success = true;

		try{
			String updateStatement = "UPDATE " + params.get(0) + " WHERE RESTAURANT_ID = " + params.get(0).getRestaurantID() + ";";

			qRunner = new QueryRunner();
			qRunner.update(this.c, updateStatement, params);



		}
		catch(Exception e){

			success = false;
			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}


		return success;
	}
}