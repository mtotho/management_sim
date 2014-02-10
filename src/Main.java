//package restuarantsim;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

class Main{

	//main()
	public static void main(String[] args) throws InterruptedException{
			

		Restaurant restaurant = new Restaurant();

		System.out.println("Program executed");

		
		start();

	}//end: main()

	//start(): start opengl window
	public static void start(){



		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Restuarant Simulator");
            Display.create();

        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }


        while(!Display.isCloseRequested()){

        	//Animation goes here
                
   			Display.update();
        }//end while

        Display.destroy();
	}
}