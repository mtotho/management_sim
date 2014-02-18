package mftoth.entities;
import mftoth.map.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.util.pathfinding.*;
import java.util.Random;

public class GLCustomer extends GLEntity implements Mover{

	private Image image;
	private SpriteSheet sprite_sheet;
	private FigureDirection direction;
	public boolean isAutomated;
	private GameContainer game;
	private Path path;
	private int path_step;
	private double doubleX,doubleY;
	private OGLMap map;


	public GLCustomer(GameContainer gc) throws SlickException{
		super(gc);
		Random r = new Random();
		int low =0;
		
		//x = r.nextInt(gc.getWidth()-low) +low;
		//y = r.nextInt(gc.getHeight()-low) +low;
		x=0;
		y=0;

		doubleX=x;
		doubleY=y;
		dx=0.1;
		dy=0.1;
		isAutomated=false;
		game=gc;
		//image = new Image("res/pickachu.png");
		
		//clefairy sprite
		image = new Image("res/clefairy_sprite.png");
		sprite_sheet = new SpriteSheet(image, 17, 17);

		direction = FigureDirection.RIGHT;

		path = new Path();
		path_step=0;
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

	public void setMap(OGLMap map){
		this.map=map;
	}

	//@Override
	public void render(GUIContext gc, Graphics g){
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, height, width, 2);


		switch(direction){
			case LEFT:
				g.drawImage(sprite_sheet.getSprite(0,1), (int)x,(int)y);
				break;

			case UP:
				g.drawImage(sprite_sheet.getSprite(0,3), (int)x,(int)y);
				break; 
			case DOWN:
				g.drawImage(sprite_sheet.getSprite(0,2), (int)x,(int)y);
				break;
			case RIGHT:
				g.drawImage(sprite_sheet.getSprite(0,0), (int)x,(int)y);
				break;
		}

		
	}
	//public void setDestination()
	public void setPath(Path path){
		this.path=path;
	}

	public void walkTileX(int tileX){
		int absx = map.getAbsX(tileX);

		
	}

	public void walkPath(int delta){
		if(path_step<path.getLength()){
			int destX = path.getX(path_step);
			int destY = path.getY(path_step);
			
	


			if( !(  (destX<=(doubleX+5)) && (destX>=(doubleX-5)) ) || !( (destY<=(doubleY+5)) && (destY>=(doubleY-5)) )){


				if(destX>doubleX){
					
					direction=FigureDirection.RIGHT;
					doubleX = doubleX + (dx*delta);
					
					
				}else{
					direction=FigureDirection.LEFT;
					
					doubleX = doubleX - (dx*delta);
				}


				if(destY>doubleY){
					direction=FigureDirection.DOWN;
					doubleY = doubleY + (dy*delta);

				}else{
					direction=FigureDirection.UP;
					doubleY = doubleY - (dy*delta);
				}

				x=(int)doubleX;
				y=(int)doubleY;
			}else{
				path_step++;
			}
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