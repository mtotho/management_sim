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

public class GLPanel extends GLEntity{

	private String label;
	protected ArrayList<GLButton> buttons = new ArrayList<GLButton>();

	protected Boolean active;
	private GameContainer game;
	private int gameWidth, gameHeight;
	
	protected Restaurant restaurant;
	protected Time timer;



	public GLPanel(Restaurant restaurant, GameContainer gc){
		super(gc);
		this.game = gc;
		this.restaurant=restaurant;
		//this.label = label;
		this.active = false;

		this.gameWidth = gc.getWidth();
		this.gameHeight = gc.getHeight();

		setLocation(640,0);
       	setDimension(320, 480);   

		timer=restaurant.getTimer();
	}

	public void addButton(GameContainer gc, String buttonName) throws SlickException{
		GLButton tempButton = new GLButton(gc, buttonName, 499, 80);
		buttons.add(tempButton);
	}

	public void addButton(GameContainer gc, String buttonName, int width, int height) throws SlickException{
		GLButton tempButton = new GLButton(gc, buttonName, width, height);
		buttons.add(tempButton);
	}


	public ArrayList<GLButton> getButtons(){
		return buttons;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void render(GUIContext gc, Graphics g){
		

		// This is used to determine the distance between buttons
		int yDif = (int)(gameHeight/(buttons.size()+1));

	   	g.setColor(Color.white);
		g.fillRect(x,y, width, height);

		g.setColor(Color.orange);
	    g.drawString("time: " + timer.getFormattedTime(), x+10, y+5);

	    g.drawString("day: " + timer.getDay(), x+250, y+5);

		if((buttons.size()>0) && (active==true)){
			for(int i=0; i<buttons.size(); i++){
				GLButton tempButton = buttons.get(i);
				tempButton.setLabelX(60);
				tempButton.setX(640);
				tempButton.setY((yDif * (i+1)) - 80);
				tempButton.render(gc, g);
			}
		}
	}

}