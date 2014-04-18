package mftoth.restaurantsim.ogl;

//simport mftoth.map.*;
import mftoth.restaurantsim.logic.*;
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

public class GLEmployee extends GLMoveableEntity{

	private Employee logical_emp;
	private OGLMap map;

	public GLEmployee(GameContainer gc, OGLMap map, StateGame sg, Employee logical_emp) throws SlickException{
		super(gc, map, sg);

		this.logical_emp = logical_emp;
		this.location = logical_emp.getLocation();
		this.destination = logical_emp.getWaypoint();
		this.map = map;

		GLTile tileLocation =  loc_handler.getTile(this.location);
		
		
		//this.
		//default some values		
		x=map.getAbsX(tileLocation.getX());
		y=map.getAbsY(tileLocation.getY());
		destx=x;
		desty=y;
		//destx=96;
		//desty=256;
		
		dx=0.1;
		dy=0.1;

		

		//Set the spritesheet path. Will use default if not indicated
		setSpriteSheet("employee_spritesheet1.png");
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		

		//leave this at the end
		super.render(gc,g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta){
		sync();
		

		//If not pathing, not at destination 
		if(!isPathInProgress() && (destination==location || destination==Locations.RANDOM)){

			//System.out.println("Im here");
			//If we arent going to the foodline, get the tile
			if(destination!=Locations.FOODLINE){
				 GLTile destTile = loc_handler.getTile(destination);
				 setPath(destTile);
			
			//Not ever going to foodline
			}else{
			//	sg.foodline.add(this);
			//	location=destination;
			}
		}
		//leave this at the end of this call. Call some parent functionality
		super.update(gc,game,delta);

		int tilex = map.getTileX(this.getX());
		int tiley = map.getTileY(this.getY());
		//System.out.println("X: " + tilex + " Y: " + tiley);
		logical_emp.update(tilex, tiley);
	}

		//sync the gl and logical customers
	public void sync(){

		//set the GLcustomer destination based on the logic destination value
		this.destination = logical_emp.getWaypoint();
		if(this.destination==null)
			this.destination=this.location;

		//set logic location according to the graphical location value
		logical_emp.setLocation(this.location);
	}

	public String toString(){
		String output = "";
		output+="<Employee>\n";
		output+="|xpos (absolute): " + x + "\n";
		output+="|ypos (absolute): " + y + "\n";
		output+="|xtile          : " + map.getTileX(x) + "\n";
		output+="|ytile          : " + map.getTileY(y) + "\n";

		return output;
	}


}