package mftoth.restaurantsim.ogl;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import java.util.Random;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import java.util.Set;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;
import org.lwjgl.input.Mouse;
import pjwelch.restaurantsim.database.*;

public class GLInfoBar extends GLEntity{

	private TrueTypeFont ttfont;
	private Font font;
	private int fontSize = 15;
	private Restaurant restaurant;
	
	private int padding;

//	private boolean hasFocus;


	public GLInfoBar(Restaurant restaurant, GameContainer gc){
		super(gc,0,480,960,40, false);
		this.restaurant=restaurant;
		

		padding=10;

		
 	 	font = new Font("Verdana", Font.BOLD, fontSize);
		ttfont = new TrueTypeFont(font, false);


		//hasFocus=false;
	}
	


	public void render(GUIContext gc, Graphics g){
		g.setFont(ttfont);

		g.setColor(Color.red);
		g.fillRect(x,y,width,height);

		g.setColor(Color.orange);
	    g.drawString("Time: " + restaurant.timer.getFormattedTime(), x+padding, y+padding);

	    g.drawString("Day: " + restaurant.timer.getDay(), x+padding+120, y+padding);

	    g.drawString("Money: $" + restaurant.getMoney(), x+padding+200, y+padding);
	
	

	

 	 }//end: render





 	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
     
      
 	
    }

    public void keyReleased(int key, char c) {

	    switch(key) {
		    case Input.KEY_ESCAPE:
		    //    item_selected=-1;
		        break;
		    case Input.KEY_2:
		        // TODO: Implement later
		        break;
		    case Input.KEY_3:
		        // TODO: Implement later
		        break;
	        case Input.KEY_RIGHT:
		        // TODO: Implement later
		        break;
		    case Input.KEY_LEFT:
		        // TODO: Implement later
		        break;
	        case Input.KEY_DOWN:
		      
		        break;

	        case Input.KEY_UP:
	        	
		        break;
		    default:
		        break;
	    }
	}


  	public void mouseReleased(int button, int x, int y){
  		
  		//mouseDown=false; //change flag back to false after click is done
  	} 

  	public void mousePressed(int button, int x,int y){


  	


  	}

  	public void mouseDragged (int oldx,int oldy, int newx, int newy){


  	


  	}

  	public void mouseMoved(int oldx, int oldy, int newx, int newy){

  	
  	}
  

}
