package mftoth.entities;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;


public class GLCustomer extends GLEntity{

	private Image image;

	public GLCustomer(GameContainer gc) throws SlickException{
		super(gc);
		x=100;
		y=100;
		dx=0.2;
		dy=0.2;

	
		image = new Image("res/pickachu.png");
	

	
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, height, width, 2);
		g.drawImage(image, x,y);
	}


}