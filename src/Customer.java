//package restuarantsim;
import java.util.ArrayList;
import java.util.Random;

public class Customer{

private ArrayList<String> order;
private int items;
private int nextItem;
private String  cb =  "cheeseburger";
private String drink = "drink";
private String fries = "fries";

	//Constructor()
	public Customer(){

	}//end: Constructor()

	public void getTraits(){



	}//end getTraits

	public void giveOrder(){

		Random random = new Random();

		items = random.nextInt() % 10;


		for (int i = 0; i < items + 1; i++){

			nextItem = ( random.nextInt() % 10 );
			
			


		}




	}//end giveOrder

	public void getOrder(){

		

	}//end getOrder()



}