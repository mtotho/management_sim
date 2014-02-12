//package restuarantsim;
import org.newdawn.slick.*;
import org.newdawn.slick.AppGameContainer;
//import org.newdawn.slick.GameContainer;


class Simulator{

	//main()
	public static void main(String[] args) throws InterruptedException{

		new Simulator();
	}//end: main()

	//public OGLDisplay display;

	//Constructor()
	public Simulator(){

		Restaurant restaurant = new Restaurant();

		System.out.println("Simulator started");

		OGLGameContainer game = new OGLGameContainer("Management Simulator");
	     try {
	          AppGameContainer container = new AppGameContainer(game);
	          container.setDisplayMode(800, 600, false);
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