package mftoth.restaurantsim.ogl;

//simport mftoth.map.*;
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
	private GameContainer game;
	private Path path;
	private int path_step;
	private double doubleX,doubleY;
	private OGLMap map;
	private AStarPathFinder astar;

	private boolean isMoving;
	private int destx, desty;

	private boolean walking_path;

	//private Customer customer;

	public GLCustomer(GameContainer gc, OGLMap map) throws SlickException{
		super(gc);
		this.map=map;
		game=gc;

		//this.customer=customer; //the customer logic object

		x=16;
		y=432;
		destx=0;
		desty=0;

		dx=0.2;
		dy=0.2;

		
		//System.out.println("Path length " + path.getLength());
		walking_path=false;
		path_step=0;
		//image = new Image("res/pickachu.png");
		
		//clefairy sprite
		image = new Image("res/clefairy_sprite.png");
		sprite_sheet = new SpriteSheet(image, 17, 17);

		//Default values
		direction = FigureDirection.RIGHT;
		isMoving=false;

		astar =new AStarPathFinder(map, 400, false);
		//path = new Path();
		
	}

	public void setMap(OGLMap map){
		this.map=map;
	}

	//@Override
	public void render(GUIContext gc, Graphics g){
		//g.setColor(Color.green);
		//g.fillRoundRect(x, y, height, width, 2);

		//Change image dependent on direction
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

	public void update(GameContainer gc, StateBasedGame game, int delta){

		walkPath();

		//Movement handler
		if(x-destx<2 && x-destx>=-2){
			x=destx;
		}
		if(y-desty<2 && y-desty>=-2){
			y=desty;
		}

		if(x!=destx || y!=desty){
			isMoving=true;
			walking_path=true;
			if(x>destx){
				x=x-(int)(delta*dx);
				direction=FigureDirection.LEFT;
			}else if(x<destx){
				x=x+(int)(delta*dx);
				direction=FigureDirection.RIGHT;
			}
			if(y>desty){
				y=y-(int)(delta*dy);
				direction=FigureDirection.UP;
			}else if(y<desty){
				y=y+(int)(delta*dy);
				direction=FigureDirection.DOWN;
			}
		}else{
			isMoving=false;
			if(walking_path){
				walking_path=false;
				path_step++;
			}
		}



	}


	//Move to the given tile x,y
	public void move(int tilex, int tiley){
		//isMoving=true;
		this.destx=map.getAbsX(tilex);
		this.desty=map.getAbsY(tiley);
	}

	//Move 1 tile in the given direction
	public void move(FigureDirection direction){
		//get current tile coord
		int tilex = map.getTileX(x);
		int tiley = map.getTileY(y);
		int dest_tilex,dest_tiley;
		dest_tilex=0;
		dest_tiley=0;
		this.direction=direction;

	//	System.out.println("origin tile x " + tilex);
	//	System.out.println("origin tile y " + tiley);
		if(!isMoving){	

			switch(direction){
				case LEFT:
					if(tilex>0){
						dest_tilex=tilex-1;
					}else{
						dest_tilex=0;
					}

					destx=map.getAbsX(dest_tilex);
					break;
				case UP:
					if(tiley>0){
						dest_tiley=tiley-1;
					}else{
						dest_tiley=0;
					}
					desty=map.getAbsY(dest_tiley);
					break; 
				case DOWN:
					if(tiley<map.getHeightInTiles()){
						dest_tiley=tiley+1;
					}else{
						dest_tiley=map.getHeightInTiles();
					}

					desty=map.getAbsY(dest_tiley);
					break;
				case RIGHT:
					if(tilex<map.getWidthInTiles()){
						dest_tilex=tilex+1;
					}else{
						dest_tilex=map.getWidthInTiles();
					}	
					destx=map.getAbsX(dest_tilex);
					break;
			}
		}

	}

	public void setMoving(boolean flag){
		isMoving=flag;
	}

	//public void setDestination()
	public void setPath(Path path){
		

		this.path=path;
		path_step=0;
	}

	public void setPath(int tilex, int tiley){
		//System.out.println("TileX: "+ tilex + " TileY: " + tiley);
		this.path=astar.findPath(null, map.getTileX(x), map.getTileY(y), tilex, tiley);
		path_step=0;
		walking_path=true;
	}

	public void walkTileX(int tileX){
		int absx = map.getAbsX(tileX);

		int diff = x-absx;



	}

	public void walkPath(){
		//System.out.println("path step :" + path_step);
		//System.out.println("path step: " + path_step);
		//System.out.println("path step: "+ path_step + " path length : "  + path.getLength());
		if(path_step<path.getLength() && !walking_path){
			destx=map.getAbsX(path.getX(path_step));
			desty=map.getAbsY(path.getY(path_step));

			


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

	public boolean isPathing(){
		return walking_path;
	}

	public void setDirection(FigureDirection d){
		direction=d;
	}

	public String toString(){
		String output = "";
		output+="<Customer>\n";
		output+="|xpos (absolute): " + x + "\n";
		output+="|ypos (absolute): " + y + "\n";
		output+="|xtile          : " + map.getTileX(x) + "\n";
		output+="|ytile          : " + map.getTileY(y) + "\n";

		return output;
	}


}