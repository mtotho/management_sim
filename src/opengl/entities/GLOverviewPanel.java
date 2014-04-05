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

import java.util.ArrayList;
import java.awt.Font;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;

public class GLOverviewPanel extends GLPanel{

	private StateGame game;
	
	public GLOverviewPanel(Restaurant restaurant, GameContainer gc, StateGame game) throws SlickException{
		super(restaurant, gc);
		this.game = game;


		addButton(gc,"btnEmployee", "Employees", 300, 100);
        addButton(gc,"btnTask", "Tasks", 300, 100);
		addButton(gc,"btnInventory", "Inventory", 300, 100);
        addButton(gc,"btnStats", "Statistics", 300, 100);

        padding=10;

	}

	public void render(GUIContext gc, Graphics g) throws SlickException{
		

		//Render parent, comment this out if you want to replace default render
		super.render(gc,g);
	}

	public void mousePressed(int button, int posx, int posy){
	   // ArrayList<GLButton> buttons = getButtons();
	    //System.out.println(buttons.size());
	    
	    //Only register mouse clicks if mouseDown is false (this should prevent the click action being called multiple times per click)
	    if(!mouseDown && (this.active==true)){
	    	mouseDown=true; //change to true so we cannot re enter this block during this click
	    	
		    if(buttons.get("btnEmployee").isPressed()){
		    	game.activatePanel("EMPLOYEES");
		    }
		    if(buttons.get("btnTask").isPressed()){
		    	game.activatePanel("TASKS");
		    }
	    }
  }
  
  	public void mouseReleased(int button, int x, int y){
  		
  		
  		mouseDown=false; //change flag back to false after click is done
  	} 
  
  
  
}
