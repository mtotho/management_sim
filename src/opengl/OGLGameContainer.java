import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;
import mftoth.states.*;

//enum State{
	//INTRO,MAIN_MENU, GAME
//}

public class OGLGameContainer extends StateBasedGame{
	
	//private State state;
	//private Map<String, BasicGameState> views;

	private int display_width;
	private int display_height;

	private long last_frame;

	public OGLGameContainer(String title){
		super(title);

		//views = new HashMap<String, BasicGameState>();
		//state=State.INTRO;
	}

	 @Override
    public void initStatesList(GameContainer container) throws SlickException {
 		addState(new StateIntro());
 	 	addState(new StateMainMenu());
 
    }


	//public void setState(State state){
	//	this.state=state;
	//}
    /*
	//getTime(): return time in ms
	private long getTime(){
		//return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}

	//getDelta(): return time that has passed since last
	private int getDelta(){
		long current_time = getTime();
		int delta = (int)(current_time-last_frame);
		last_frame=getTime();
		return delta;
	}
*/
/*
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		//views.put("MAIN_MENU", new ViewMainMenu(this));

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		 Input input = gc.getInput();
		switch (state) {
        	 case INTRO:

                if (input.isKeyDown(Input.KEY_RETURN)) {
                    state = State.MAIN_MENU;
                }
                break;
            case GAME:
                if (input.isKeyDown(Input.KEY_BACK)) {
                    state = State.MAIN_MENU;
                }
                break;
            case MAIN_MENU:

            	//Tilde key
            	if(input.isKeyDown(Input.KEY_F11)){
            		System.out.println("Console");
            	}

                if (input.isKeyDown(Input.KEY_RETURN)) {
                    //tate = State.GAME;
                    //System.out.println("Enter pressed");
                }
                if (input.isKeyDown(Input.KEY_SPACE)) {
                    state = State.INTRO;
                }
                break;
        }

        if(Mouse.isButtonDown(0)){
        	//System.out.println("Mouse X: " + Mouse.getX() + " | Mouse Y: " + Mouse.getY());
        }
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
	
		//GL11.glLoadIdentity();

		switch (state) {
            case INTRO:
            	g.drawString("intro", 10,10);
            	//RenderTool.renderText("Restuarant Simulator", 50,480);
				//RenderTool.renderText("Gordon Dragoon", 50,420);
				//RenderTool.renderText("Patrick Welch", 50,400);
				//RenderTool.renderText("Mike Toth", 50,380);
                break;
            case GAME:
          		g.drawString("Game", 10,10);
               // GL11.glColor3f(0f, 1.0f, 0f);
               // GL11.glRectf(0, 0, this.display_width, this.display_height);
                break;
            case MAIN_MENU:
            	//GL11.glColor3f(0f, 0f, 1.0f);
                //GL11.glRectf(0, 0, this.display_width, this.display_height);
            	views.get("MAIN_MENU").enter(gc, this);
                
                break;
        }

	}

	private void checkInput() {
        
    }*/
 
}