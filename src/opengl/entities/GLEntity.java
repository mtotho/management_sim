package mftoth.entities;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;


public abstract class GLEntity extends AbstractComponent{

	protected int x,y, width, height;
	protected double dx, dy;


	public GLEntity(GameContainer gc){
		super(gc);

		x=0;
		y=0;
		width=10;
		height=10;
	}


	
	@Override
	public int getHeight(){
		return height;
	}

	@Override
	public int getWidth(){
		return width;
	}

	@Override
	public int getX(){
		return x;
	}
	@Override
	public int getY(){
		return y;
	}

	public void setX(int x){
		this.x=x;
	}
	public void setX(double x){
		this.x=(int)x;
	}

	public void setY(int y){
		this.y=y;
	}
	public void setY(double y){
		this.y=(int)y;
	}

	public double getDX(){
		return dx;
	}

	public double getDY(){
		return dy;
	}

	public void setDX(double dx){
		this.dx=dx;
	}

	public void setDY(double dy){
		this.dy=dy;
	}

	//[@Override
	public void setLocation(int x, int y){
		this.x=x;
		this.y=y;
	}


}