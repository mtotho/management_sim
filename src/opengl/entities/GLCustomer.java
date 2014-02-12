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

//	protected double x,y, width, height;


	public GLCustomer(GameContainer gc){
		super(gc);
		x=100;
		y=100;
		dx=1;
		dy=1;
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		g.fillRoundRect(x, y, height, width, 2);
	}

	/*
	//@Override
	public double getHeight(){
		return height;
	}

	//@Override
	public double getWidth(){
		return width;
	}

	//@Override
	public double getX(){
		return x;
	}
	//@Override
	public double getY(){
		return y;
	}

	//[@Override
	public void setLocation(double x, double y){
		this.x=x;
		this.y=y;
	}*/


}