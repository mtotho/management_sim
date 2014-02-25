
package mftoth.restaurantsim.ogl;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import mftoth.restaurantsim.logic.*;

public class StateIntro extends BasicGameState{

	
	private int ID = 1;
	private StateBasedGame game;
	private Restaurant restaurant;

	public StateIntro(Restaurant restaurant){
		super();
		this.restaurant=restaurant;
	}

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
       	this.game=game;
 
    }
 
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
       	
       	g.setColor(Color.white);
	    g.drawString("Restaurant Simulator", 50, 10);
	 	
 		g.setColor(Color.orange);
	    g.drawString("Gordon Dragoon", 50, 100);
	    g.drawString("Patrick Welch", 50, 130);
	    g.drawString("Mike Toth", 50, 160);

	    g.setColor(Color.cyan);
	    g.drawString("Please press Enter to continue...", 200, 400);
 
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
	    case Input.KEY_RETURN:
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

}