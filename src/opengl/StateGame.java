
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

	private GLCustomer c1;

    @Override
    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {
       	this.game=game;

      	c1 = new GLCustomer(gc);
      	c1.setDX(0.2);
      	c1.setDY(0.2);


 
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
      	
  		g.setColor(Color.white);
	    g.drawString("Game State", 50, 10);

	   	c1.setLocation(c1.getX(),c1.getY());
	    c1.render(gc, g);

 
    }
 
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {

      	Input input = gc.getInput();
  		if(input.isKeyDown(Input.KEY_UP)){
  			c1.setY(c1.getY()-c1.getDY()*delta);
  			//System.out.println(c1.getY()-c1.getDY());
      	}
      	if(input.isKeyDown(Input.KEY_RIGHT)){
      		c1.setX(c1.getX()+c1.getDX()*delta);
      	}
  		if(input.isKeyDown(Input.KEY_DOWN)){
  			c1.setY(c1.getY()+c1.getDY()*delta);
      	}
      	if(input.isKeyDown(Input.KEY_LEFT)){
      		c1.setX(c1.getX()-c1.getDX()*delta);
      	}     
 
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

	public void keyPressed(int key, char c){
		switch(key) {
	    case Input.KEY_UP:
	       // game.enterState(1);

	    	
	        break;
	    case Input.KEY_RIGHT:
	        // TODO: Implement later
	        break;
	    case Input.KEY_LEFT:
	        // TODO: Implement later
	        break;
        case Input.KEY_DOWN:
	        // TODO: Implement later
	        break;
	    default:
	        break;
	    }
	}

}