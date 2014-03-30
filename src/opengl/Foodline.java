package mftoth.restaurantsim.ogl;
import java.util.ArrayList;

public class Foodline{

	private final static int CAPACITY = 14;

	private ArrayList<GLCustomer> customers;
	private ArrayList<GLTile> linetiles; 

	public Foodline(){

		linetiles = new ArrayList<GLTile>();

		linetiles.add(new GLTile(8,17));
		linetiles.add(new GLTile(9,17));
		linetiles.add(new GLTile(10,17));
		linetiles.add(new GLTile(11,17));
		linetiles.add(new GLTile(12,17));
		linetiles.add(new GLTile(13,17));
		linetiles.add(new GLTile(14,17));
		linetiles.add(new GLTile(15,17));
		linetiles.add(new GLTile(16,17));
		linetiles.add(new GLTile(17,17));
		linetiles.add(new GLTile(18,17));
		linetiles.add(new GLTile(19,17));
		linetiles.add(new GLTile(20,17));
		linetiles.add(new GLTile(21,17));
		linetiles.add(new GLTile(22,17));



		customers = new ArrayList<GLCustomer>();
	}

	public boolean add(GLCustomer glcust){

		//If  not at max capacity, add customer
		if(customers.size()<CAPACITY){
			customers.add(glcust);

			//get the tile for the spot in the line
			GLTile linespot = linetiles.get(customers.size()-1);

			glcust.setPath(linespot.getX(), linespot.getY());
			//customer.setDirection(FigureDirection.LEFT);
			return true;

		}else{
			return false;
		}

	}

	public GLCustomer getNext(){
		if(customers.size()>0){
			
			//Move each customer up one spot
			for(int i=0; i<customers.size(); i++){
				GLCustomer glcust = customers.get(0);
				GLTile linespot = linetiles.get(i);
				glcust.setPath(linespot.getX(), linespot.getY());
			}
			
			//get the first customer
			GLCustomer nextcust = customers.get(0);


			//remove the first customer
			customers.remove(0);

			return nextcust;
		}else{
			return null;
		}
	}


}