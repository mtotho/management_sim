//package mftoth.restaurantsim.logic;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.GameContainer;

import mftoth.restaurantsim.ogl.*;
import mftoth.restaurantsim.logic.Restaurant;

class Simulator{

	//main()
	public static void main(String[] args) throws InterruptedException{

		new Simulator();
	}//end: main()

	//public OGLDisplay display;

	//Constructor()
	public Simulator(){

		Restaurant restaurant = new Restaurant();

		//System.out.println("Simulator started");

		OGLGameContainer game = new OGLGameContainer("Management Simulator", restaurant);
	     try {
	          AppGameContainer container = new AppGameContainer(game);
	          container.setDisplayMode(960, 640, false);
	          container.setShowFPS(true);
	          container.start();
	     } catch (SlickException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	     }

		//display = new OGLDisplay();
		//display.run();


	}//end: Constructor()
}