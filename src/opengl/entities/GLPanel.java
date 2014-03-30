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
import java.awt.Font;
import java.util.Set;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;

public class GLPanel extends GLEntity{

	private String label;
	//protected ArrayList<GLButton> buttons = new ArrayList<GLButton>();

	protected Boolean active;
	private GameContainer game;
	private int gameWidth, gameHeight;
	
	protected Restaurant restaurant;
	protected Time timer;
	protected int padding;
	protected LinkedHashMap<String, GLButton> buttons;
	
	protected boolean mouseDown;



	public GLPanel(Restaurant restaurant, GameContainer gc){
		super(gc);
		this.game = gc;
		this.restaurant=restaurant;
		//this.label = label;
		this.active = false;
		
		this.mouseDown=false;

		this.gameWidth = gc.getWidth();
		this.gameHeight = gc.getHeight();

		buttons = new LinkedHashMap<String, GLButton>();
		setLocation(640,0);
       	setDimension(320, 480);   

		timer=restaurant.getTimer();
	}

	public void addButton(GameContainer gc, String buttonName) throws SlickException{
		GLButton tempButton = new GLButton(gc, buttonName, 499, 80);
		//buttons.add(tempButton);
	}

	public void addButton(GameContainer gc, String id, String label, int width, int height) throws SlickException{
		GLButton tempButton = new GLButton(gc, label, width, height);
		buttons.put(id, tempButton);
		//buttons.add(tempButton);
	}


	public LinkedHashMap<String, GLButton> getButtons(){
		return buttons;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void render(GUIContext gc, Graphics g){
		

		// This is used to determine the distance between buttons
		int yDif = (int)(height/(buttons.size()+1));

	   	g.setColor(Color.white);
		g.fillRect(x,y, width, height);

		g.setColor(Color.orange);
	    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

	    g.drawString("Day: " + timer.getDay(), x+250, y+5);

	    Object[] keys = buttons.keySet().toArray();
	    
	    for(int i=0; i<keys.length; i++){
	    
				GLButton tempButton = buttons.get(keys[i]);
				tempButton.setLabelX(60);
				tempButton.setX(padding+x);
				tempButton.setY((yDif * (i+1)) - 60);
				tempButton.render(gc, g);

		}
	    

	    /*
		if((buttons.size()>0) && (active==true)){
			for(int i=0; i<buttons.size(); i++){
				GLButton tempButton = buttons.get(i);
				tempButton.setLabelX(60);
				tempButton.setX(padding+x);
				tempButton.setY((yDif * (i+1)) - 80);
				tempButton.render(gc, g);
			}
		}*/
	}

}
