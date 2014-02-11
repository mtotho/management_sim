
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.*;
import org.newdawn.slick.*;

import java.util.*;

//enum State{
	//INTRO,MAIN_MENU, GAME
//}

public class OGLDisplay{

	private State state;
	private Map<String, SimView> views;

	private int display_width;
	private int display_height;

	private long last_frame;


	

	public OGLDisplay(){

		views = new HashMap<String, SimView>();


		display_width=800;
		display_height=600;
		state=State.INTRO;

	}

	//getTime(): return time in ms
	private long getTime(){
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}

	//getDelta(): return time that has passed since last
	private int getDelta(){
		long current_time = getTime();
		int delta = (int)(current_time-last_frame);
		last_frame=getTime();
		return delta;
	}

	//run(): starts the display and game loop
	public void run(){


		try {
			Display.setDisplayMode(new DisplayMode(this.display_width, this.display_height));
			Display.setTitle("Restuarant Simulator");
            Display.create();

            init();

            last_frame=getTime();

            views.put("MAIN_MENU", new ViewMainMenu(this));


	        while(!Display.isCloseRequested()){
	        	checkInput();

	        	System.out.println(getDelta());

	        	//Animation goes here
	            render();
	   			Display.update();
	   			Display.sync(60);
	        }//end while

	        close();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }



        //Display.destroy();
	}

	public void setState(State state){
		this.state=state;
	}


	//init(): initialize some stuff
	public void init(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, this.display_width,0,this.display_height,1,-1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	//render(): The animation loop
	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		//GL11.glLoadIdentity();

		switch (state) {
            case INTRO:
            	//Graphics.drawString("Hello World", 10,10);
            	//RenderTool.renderText("Restuarant Simulator", 50,480);
				//RenderTool.renderText("Gordon Dragoon", 50,420);
				//RenderTool.renderText("Patrick Welch", 50,400);
				//RenderTool.renderText("Mike Toth", 50,380);
                break;
            case GAME:
                GL11.glColor3f(0f, 1.0f, 0f);
                GL11.glRectf(0, 0, this.display_width, this.display_height);
                break;
            case MAIN_MENU:
            	//GL11.glColor3f(0f, 0f, 1.0f);
                //GL11.glRectf(0, 0, this.display_width, this.display_height);
            	views.get("MAIN_MENU").render();
                
                break;
        }


		
	}


	 private void checkInput() {
        switch (state) {
        	 case INTRO:

                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    state = State.MAIN_MENU;
                }
                break;
            case GAME:
                if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                    state = State.MAIN_MENU;
                }
                break;
            case MAIN_MENU:

            	//Tilde key
            	if(Keyboard.isKeyDown(Keyboard.KEY_F11)){
            		System.out.println("Console");
            	}

                if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
                    //tate = State.GAME;
                    //System.out.println("Enter pressed");
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                    state = State.INTRO;
                }
                break;
        }

        if(Mouse.isButtonDown(0)){
        	//System.out.println("Mouse X: " + Mouse.getX() + " | Mouse Y: " + Mouse.getY());
        }
    }

	public void close(){
		Display.destroy();
		System.exit(0);
	}

}