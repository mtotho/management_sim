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
import mftoth.restaurantsim.logic.*;

public class GLMoveableEntity extends GLEntity implements Mover{

	protected boolean use_path=false;

	private AStarPathFinder astar;
	private Image image;
	private SpriteSheet sprite_sheet;
	protected FigureDirection direction;
	protected GameContainer game;
	protected OGLMap map;
	protected Path path;
	protected int path_step;
	private boolean isMoving;
	protected int destx, desty;
	protected Locations location;
	protected Locations destination;
	protected LocationHandler loc_handler;
	private boolean walking_path=false; 		//Whether or not the character is in the progress of walking a path
	private boolean inbetween_nodes=false;	 	//Whether or not the character is between 2 nodes of the current path


	protected StateGame sg;

	public GLMoveableEntity(GameContainer gc, OGLMap map, StateGame sg) throws SlickException{
		super(gc);

		loc_handler = new LocationHandler(map);
		this.map=map;
		game=gc;
		this.sg=sg;

		//Default values
		x=16;
		y=432;
		destx=0;
		desty=0;

		dx=0.2;
		dy=0.2;

		//clefairy sprite
		image = new Image("res/sprites/employee_spritesheet1.png");
		sprite_sheet = new SpriteSheet(image, 16, 20);

		direction = FigureDirection.RIGHT;
		isMoving=false;

		astar =new AStarPathFinder(map, 400, false);
		walking_path=false;

		//set default path so we dont have null
		//setPath(6,16);
		//path_step=0;
	}

	public void setMap(OGLMap map){
		this.map=map;
	}

	public void setSpriteSheet(String filename) throws SlickException{
		image = new Image("res/sprites/" + filename);
		sprite_sheet = new SpriteSheet(image, 16, 20);
	}

	public void get_next_path_node(){

		//only get next node if we are not at the end of the path, and are not inbetween nodes
		if(path_step<path.getLength() && !inbetween_nodes){

			//Set the current entity's destx,desty to the next path node
			destx=map.getAbsX(path.getX(path_step));
			desty=map.getAbsY(path.getY(path_step));

			//Indicate that we will now be inbetween path nodes
			inbetween_nodes=true;

			//Indicate that we are currently walking a path
			walking_path=true;

			//Increment the path_step for the next node
			path_step++;
		}

		if(path_step>=path.getLength() && (x==destx && y==desty)){

			//The path has ended so we can set walking_path to false
			walking_path=false;

			//No path left so we must be at our destination
			location=destination;
		}
	}

	protected void update(GameContainer gc, StateBasedGame game, int delta){
		
//		System.out.println(delta);

		if(use_path){
			get_next_path_node();
		}	

//		System.out.println(delta);

		//System.out.println("x: " + x + " | destx: " + destx);

		//System.out.println("y: " + y + " | desty: " + desty);

		//if the current x position is with +/- 2 of the destination, just make x the destination
		double mod=1;
		if(x-destx<8 && x-destx>=-8){
			//x=destx;
		//	mod=0.5;
		}
		if(y-desty<8 && y-desty>=-8){
			//y=desty;
		//	mod=0.5;
		}
		if(x!=destx || y!=desty){
			isMoving=true;
			//inbetween_nodes=true;
			if(x>destx){
				x=x-(int)(delta*dx*mod);
				direction=FigureDirection.LEFT;
			}else if(x<destx){
				x=x+(int)(delta*dx*mod);
				direction=FigureDirection.RIGHT;
			}
			if(y>desty){
				y=y-(int)(delta*dy*mod);
				direction=FigureDirection.UP;
			}else if(y<desty){
				y=y+(int)(delta*dy*mod);
				direction=FigureDirection.DOWN;
			}
		}else{

			//Since x and y are at their destination, must not be moving
			isMoving=false;
			//if(inbetween_nodes){
			inbetween_nodes=false;
				//path_step++;
			//}
		}

	}

	//@Override
	public void render(GUIContext gc, Graphics g){
		

		//Change image dependent on direction
		switch(direction){
			case LEFT:
				g.drawImage(sprite_sheet.getSprite(0,1), (int)x,(int)y);
				break;

			case UP:
				g.drawImage(sprite_sheet.getSprite(0,2), (int)x,(int)y);
				break; 
			case DOWN:
				g.drawImage(sprite_sheet.getSprite(0,0), (int)x,(int)y);
				break;
			case RIGHT:
				g.drawImage(sprite_sheet.getSprite(0,3), (int)x,(int)y);
				break;
		}
		
	}

	//Move to the given tile x,y
	public void move(int tilex, int tiley){
		//isMoving=true;
		this.destx=map.getAbsX(tilex);
		this.desty=map.getAbsY(tiley);
	}

	//Move 1 tile in the given direction. This is
	public void move(FigureDirection direction){
		//get current tile coord
		int tilex = map.getTileX(x);
		int tiley = map.getTileY(y);
		int dest_tilex,dest_tiley;
		dest_tilex=0;
		dest_tiley=0;
		this.direction=direction;

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

	public void setPath(GLTile dest){
		setPath(dest.getX(), dest.getY());
	}

	//public void setDestination()
	public void setPath(Path path){
		
		this.path=path;
		path_step=0;
		use_path=true;
	}

	public void setPath(int tilex, int tiley){
		
		if(!(tilex==map.getTileX(x) && tiley==map.getTileY(y))){


			this.path=astar.findPath(null, map.getTileX(x), map.getTileY(y), tilex, tiley);
			path_step=0;
			use_path=true;
		}
	}

	//return whether or not the character is currently walking on a path
	public boolean isPathInProgress(){
		return walking_path;
	}

	//return whether or not the character is moving at all (for any reason)
	public boolean isMoving(){
		return isMoving;
	}

	public void setDirection(FigureDirection d){
		direction=d;
	}



}