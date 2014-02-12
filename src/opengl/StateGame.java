
package mftoth.states;

import mftoth.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class StateGame extends BasicGameState{

	
	private int ID = 3;
	private StateBasedGame game;

	//private GLCustomer c1;

    @Override
    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {
       	this.game=game;

      // 	c1 = new GLCustomer(gc);


 
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
      	
  		g.setColor(Color.white);
	    g.drawString("Game State", 50, 10);

	   // c1.setLocation(100,100);
	    //c1.render(gc, g);

 
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
	    case Input.KEY_ESCAPE:
	        game.enterState(1);
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