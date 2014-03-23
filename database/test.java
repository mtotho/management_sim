
import java.sql.*;

public class test{
	
	public static void main(String args[]){


		Connection c = null;
		Statement stmt = null;


		try{

			Class.forName("org.sqlite.JDBC");
		
		}
		catch(Exception e){



		}


		try{

			c = DriverManager.getConnection("jdbc:sqlite:sim.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select ID, NAME from PLAYER" );
			String playerName = "player1";


			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");


				if(name.equals(playerName)){

					//System.out.println(playerName);
					System.out.println(id + ", " + name);
					break;

				}


			}

	
		rs.close();
		stmt.close();
//s		c.close();

		}
		catch(Exception e){

			System.err.println( e.getClass().getName() + ": " + e.getMessage());


		}




	}
	

}