package mftoth.entities;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import java.util.Random;

public class GLCustomer extends GLEntity{

	private Image image;
	private SpriteSheet sprite_sheet;
	private FigureDirection direction;
	public boolean isAutomated;
	private GameContainer game;


	public GLCustomer(GameContainer gc) throws SlickException{
		super(gc);
		Random r = new Random();
		int low =0;
		
		x = r.nextInt(gc.getWidth()-low) +low;
		y = r.nextInt(gc.getHeight()-low) +low;
		dx=0.2;
		dy=0.2;
		isAutomated=false;
		game=gc;
		//image = new Image("res/pickachu.png");
		
		//clefairy sprite
		image = new Image("res/clefairy_sprite.png");
		sprite_sheet = new SpriteSheet(image, 17, 17);

		direction = FigureDirection.RIGHT;
	}

	public GLCustomer(GameContainer gc, boolean isAutomated) throws SlickException{
		this(gc);

		this.isAutomated=isAutomated;

		if(isAutomated){
			Random r = new Random();
			int low =0;
			
			x = r.nextInt(gc.getWidth()-low) +low;
			y = r.nextInt(gc.getHeight()-low) +low;
		}

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

	public void randomMovement(int delta){
		Random r = new Random();
		dx=0.1;
		dy=0.1;
		int low = 0;
		int high =3;
		int sign_dx = r.nextInt(high-low) -1;
		int sign_dy = r.nextInt(high-low) -1;

		if(sign_dx>0){
			direction=FigureDirection.RIGHT;
		}else{
			direction=FigureDirection.LEFT;
		}

		if(sign_dy>0){
			direction=FigureDirection.DOWN;
		}else{
			direction=FigureDirection.UP;
		}

		x=x + (int)((sign_dx)*dx*delta);
		y=y + (int)((sign_dy)*dy*delta);

		//System.out.println(sign_dy);
	}


	public void setDirection(FigureDirection d){
		direction=d;
	}


}