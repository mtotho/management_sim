
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;

import mftoth.restaurantsim.ogl.*;
import mftoth.restaurantsim.logic.Restaurant;
import pjwelch.restaurantsim.database.*;


class Simulator{

	//main()
	public static void main(String[] args) throws InterruptedException{

		new Simulator();

	}//end: main()


	//Constructor()
	public Simulator(){

		DBmapper db = new DBmapper();

		//System.out.println("Simulator started");

		OGLGameContainer game = new OGLGameContainer("Management Simulator", db);
	     try {
	          AppGameContainer container = new AppGameContainer(game);
	          container.setDisplayMode(960, 520, false);
	          container.setTargetFrameRate(80);
	          container.setAlwaysRender(true);
	          container.start();

	     } catch (SlickException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	     }


	}//end: Constructor()
}