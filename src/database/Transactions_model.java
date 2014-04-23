/*



Transactions is the class object that will be used to interact and abstract the rows of the transactions table
*/
package pjwelch.restaurantsim.database;


public class Transactions_model implements Model{

		private int restaurantID;
		private int transactionID;
		private double transactionPrice;


	public void Transactions_model(int restaurantID, int transactionID, double transactionPrice){

		this.restaurantID = restaurantID;
		this.transactionID = transactionID;
		this.transactionPrice = transactionPrice;


	}
	public void Transactions_model(){





	}
	public void setRestaurantID(int restaurantID){

		this.restaurantID = restaurantID;

	}

	public int getRestaurantID(){

		return restaurantID;

	}

	public void setTransactionID(int transactionID){

		this.transactionID = transactionID;

	}

	public int getTransactionID(){

		return transactionID;

	}

	public void setTransactionPrice(int price){

		this.transactionPrice = price;

	}

	public double getTransactionPrice(){

		return transactionPrice;

	}

	public String getTable(){

		return "Transactions";

	}

}