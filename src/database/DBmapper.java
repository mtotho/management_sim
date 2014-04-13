/*
Patrick Welch

DB Mapper can be called to query, update, and insert into the respective database tables
*/


import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.QueryRunner;
import org.apache.commons.BeanListHandler;

import java.sql.*;
import java.util.*;
import java.lang.*;

public class DBmapper{

	private String dbpath = "jdbc:sqlite:sim.db";
	private String dbDriver = "org.sqlite.JDBC";
	private String sql = "";

	public Connection c = null; 
	public QueryRunner qRunner;


	public DBconnection (String dbpath) throws Exception{

		this.dbpath = dbpath;
		this.qRunner = new QueryRunner();

		if (!this.connect()){

			throw new Excception("Failed to connect to database");

		}


	}
	public void DBcheck(){







	}

	public synchronized boolean connect() {

		try{
			Class.forName(dbDriver);
			this.close();
			c = DriverManager.getConnection(dbpath);
			return true;		
		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " e.getMessage());

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

	public boolean insert(Object obj){

		String insertStatment = "INSERT INTO " + obj.getName() + " VALUES (" + obj.toString +");";

		try{

			Class<?> clazz = obj.getClass();
			qRunner.update(this.c, insertStatement)
			return true;

		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;

		}

	}

	public List select(Class<?> clazz, Object param){

		String selectStatment = "SELECT * FROM " + clazz;

		try{

			qRunner = new QueryRunner();
			List beans = (List) qRunner.query(this.cm, selectStatment, new BeanListHandler(clazz));

			for (int i = 0; i < beans.size(); i++){

				clazz bean = (clazz) beans.get(i)
				bean.print();

				return beans;
			}

		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			Return beans;

		}



	}

	public boolean update(Class<?> clazz, Object param){

		String updateStatement  = "UPDATE " + clazz + " WHERE RESTAURANT RESTAURANT_ID = " + clazz.getRestaurantID() + ";" ;

		try{

			qRunner = new QueryRunner();

			qRunner.update(this.c, updateStatement, param);


		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}

	}
	public boolean update(Class<?> clazz, Object[] params){

		String updateStatement = "UPDATE " + clazz + " WHERE RESTAURANT RESTAURANT_ID = " + clazz.getRestaurantID() + ";";

		try{

			qRunner = new QueryRunner();
			qR.update(this.c, updateStatement, params);


		}
		catch(Exception e){

			System.err.println(e.getClass().getName() + ": " + e.getMessage());

		}



	}
}