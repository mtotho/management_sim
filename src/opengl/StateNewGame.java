package mftoth.restaurantsim.ogl;

//import mftoth.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
 
import mftoth.restaurantsim.logic.*;

public class StateNewGame extends BasicGameState{

	//private GLButton btnNewGame; 
	//private OGLGameContainer game;

	//public ViewMainMenu(OGLGameContainer game){
		//this.game=game;
		//super(screen);

		//Width: 60% of display, height: 20% of display, X: 20% from left, Ystart: 40% from top
		
		//btnNewGame= new GLButton(Display.getWidth()*0.2, Display.getHeight()-Display.getHeight()*0.4, Display.getWidth()*0.6,Display.getHeight()*0.2, "New Game");
		//btnNewGame= new GLButton(100f,40f, 20f, 20f, "New Game");
		
	//}

	private int ID = 4;
	private StateBasedGame game;

	//private GLButton btnNewGame;
	//private GLButton btnAbout;

	private Restaurant restaurant;

	public StateNewGame(Restaurant restaurant){
		super();
		this.restaurant=restaurant;
	}

	@Override
    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {
        this.game=game;
 	
 	

 		//btnNewGame.mousePressed(){
 		
 		

    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {

       	g.setColor(Color.white);
		g.fillRect(0,0, gc.getWidth(), gc.getHeight());

		g.setColor(Color.black);
	    g.drawString("New Game", 50, 100);

	   // g.drawString("1. Press 1 to start game", 50, 130);

    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
 
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return ID;
    }

    public void keyReleased(int key, char c) {
	    switch(key) {
	    case Input.KEY_1:
	        game.enterState(2);
	        break;
	    case Input.KEY_2:
	        // TODO: Implement later
	        break;
	    case Input.KEY_3:
	        // TODO: Implement later
	        break;
	    default:
	        break;
	    }
	}

	public void mousePressed(int button, int posx, int posy){
		/*if(btnNewGame.isPressed()){
			game.enterState(3);
		}

		if(btnAbout.isPressed()){
			System.out.println("btnAbout clicked");
		}*/

	}

}