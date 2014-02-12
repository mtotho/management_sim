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

import java.awt.Font;
import org.newdawn.slick.UnicodeFont;

public class GLButton extends GLEntity{

	private Image image;
	private SpriteSheet sprite_sheet;
	private String label;
	//private FigureDirection direction;
	private GameContainer game;

	private TrueTypeFont ttfFont;
	private Font font;
	private int fontSize;
	private int lblX;
	private int lblY;

	private boolean isPressed;


	public GLButton(GameContainer gc) throws SlickException{
		super(gc);
		game=gc;
		//image = new Image("res/pickachu.png");
		
		//clefairy sprite
		//image = new Image("res/clefairy_sprite.png");
		//sprite_sheet = new SpriteSheet(image, 17, 17);

	}

	public GLButton(GameContainer gc, String label) throws SlickException{
		this(gc);

		this.label=label;
		setUpFonts();

	}

	public GLButton(GameContainer gc, double relX, double relY, double relWidth, double relHeight) throws SlickException{
		super(gc,relX, relY, relWidth, relHeight);
		setUpFonts();
	}

	public GLButton(GameContainer gc, String label, double relX, double relY, double relWidth, double relHeight) throws SlickException{
		super(gc,relX, relY, relWidth, relHeight);

		this.label=label;
		setUpFonts();
		
	}

	private void setUpFonts(){
		font = new Font("Verdana", Font.BOLD, 25);
		ttfFont = new TrueTypeFont(font,false);

		fontSize=font.getSize();

		if(height>fontSize){
			int temp = height-fontSize;
			lblY = temp/2;
			lblX = 0;
		}else{
			lblY=0;
			lblX=0;
		}

	}

	public void setFont(Font font){
		ttfFont = new TrueTypeFont(font, false);
	}

	public void setLabelX(int relX){
		lblX=relX;
	}



	//@Override
	public void render(GUIContext gc, Graphics g){
		g.setColor(Color.green);
		g.fillRoundRect(x, y, width, height, 2);

		g.setColor(Color.blue);
		g.setFont(ttfFont);
		g.drawString(label,x + lblX, y + lblY);
		//uFont.drawString(x + 10, y + 10, label);
		
	}

	public void mousePressed(int button, int posx, int posy){

		if(button==0 && inBounds(posx,posy)){
			isPressed=true;
		}
	}

	public void mouseReleased(int button, int posx, int posy){
		
		//Button is released.. complete click
		if(button==0 && isPressed){
			isPressed=false;


		}
	}

	public boolean inBounds(int posx, int posy){

		if((posx>=x && posx<= x+width) && (posy>=y && posy<=y+height)){
			return true;
		}else{
			return false;
		}

	}


}