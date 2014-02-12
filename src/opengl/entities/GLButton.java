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

public class GLButton extends GLEntity{

	private Image image;
	private SpriteSheet sprite_sheet;
	private String label;
	//private FigureDirection direction;
	private GameContainer game;


	public GLCustomer(GameContainer gc, String label) throws SlickException{
		super(gc);
		game=gc;
		//image = new Image("res/pickachu.png");
		
		this.label-label
		//clefairy sprite
		//image = new Image("res/clefairy_sprite.png");
		//sprite_sheet = new SpriteSheet(image, 17, 17);

	}

	//@Override
	public void render(GUIContext gc, Graphics g){
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, height, width, 2);



		
	}


}