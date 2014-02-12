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
	private SpriteSheet sprite_sheet;
	private FigureDirection direction;

	public GLCustomer(GameContainer gc) throws SlickException{
		super(gc);
		x=100;
		y=100;
		dx=0.2;
		dy=0.2;

	
		//image = new Image("res/pickachu.png");
		
		//clefairy sprite
		image = new Image("res/clefairy_sprite.png");
		sprite_sheet = new SpriteSheet(image, 17, 17);
		direction = FigureDirection.RIGHT;

	
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, height, width, 2);

		switch(direction){
			case LEFT:
				g.drawImage(sprite_sheet.getSprite(0,1), x,y);
				break;

			case UP:
				g.drawImage(sprite_sheet.getSprite(0,3), x,y);
				break; 
			case DOWN:
				g.drawImage(sprite_sheet.getSprite(0,2), x,y);
				break;
			case RIGHT:
				g.drawImage(sprite_sheet.getSprite(0,0), x,y);
				break;
		}

		
	}

	public void setDirection(FigureDirection d){
		direction=d;
	}


}