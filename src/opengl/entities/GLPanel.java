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
import org.newdawn.slick.UnicodeFont;

public class GLPanel extends GLEntity{

	private String label;
	private ArrayList<GLButton> buttons = new ArrayList<GLButton>();
	private Boolean active;
	private GameContainer game;
	private int gameWidth, gameHeight;


	public GLPanel(GameContainer gc, String label, boolean active){
		super(gc);
		this.game = gc;

		this.label = label;
		this.active = true;

		this.gameWidth = gc.getWidth();
		this.gameHeight = gc.getHeight();
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

	public void setActivity(Boolean active){
		this.active = active;
	}

	public void render(GUIContext gc, Graphics g){
		// This is used to determine the distance between buttons
		int yDif = (int)(gameHeight/(buttons.size()+1));

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