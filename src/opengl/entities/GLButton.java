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
	

	private TrueTypeFont ttfFont;
	private Font font;
	private int fontSize;
	private int lblX;
	private int lblY;

	private boolean isPressed;
	private boolean isHovered;


	public GLButton(GameContainer gc) throws SlickException{
		super(gc);
		
		init();
		//clefairy sprite
		//image = new Image("res/clefairy_sprite.png");
		

	}

	public GLButton(GameContainer gc, String label) throws SlickException{
		this(gc);

		this.label=label;
		
	}

	public GLButton(GameContainer gc, double x, double y, double width, double height, boolean isRel) throws SlickException{
		super(gc,x, y, width, height, isRel);
		
		init();
	}

	public GLButton(GameContainer gc, String label, double x, double y, double width, double height, boolean isRel) throws SlickException{
		super(gc,x, y, width, height, isRel);

		this.label=label;
		
		init();
	}

	//Only width height given, assume centered
	public GLButton(GameContainer gc, String label, double width, double height) throws SlickException{
		super(gc, width, height);
		this.label=label;
		init();
	}

	private void init() throws SlickException{
		setUpFonts();
		image = new Image("res/buttons/sprite_mc_medium.png");
		sprite_sheet = new SpriteSheet(image, 499, 80);
	}

	private void setUpFonts(){
		font = new Font("Verdana", Font.BOLD, 25);
		ttfFont = new TrueTypeFont(font,false);

		fontSize=font.getSize();

		if(height>fontSize){
			int temp = (int)(height-fontSize);
			lblY = temp/2 -(fontSize/4);
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
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, width, height, 2);
		//image.draw(x,y,width,height);
		
		if(!isHovered){
			g.drawImage(sprite_sheet.getSprite(0,0), (int)x,(int)y);
		}else{
			g.drawImage(sprite_sheet.getSprite(0,1), (int)x,(int)y);
		}
		g.setColor(Color.blue);
		g.setFont(ttfFont);
		g.drawString(label,(int)(x + lblX), (int)(y + lblY));
		//uFont.drawString(x + 10, y + 10, label);
		
	}

	public boolean isPressed(){
		return isPressed;
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

	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		if(inBounds(newx, newy)){
			isHovered=true;
		}else{
			isHovered=false;
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