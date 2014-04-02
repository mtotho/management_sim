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

public class GLEmployee extends GLMoveableEntity{


	public GLEmployee(GameContainer gc, OGLMap map, StateGame sg) throws SlickException{
		super(gc, map, sg);
		
		//default some values		
		x=96;
		y=256;
		destx=96;
		desty=256;
		
		dx=0.2;
		dy=0.2;



		//Set the spritesheet path. Will use default if not indicated
		setSpriteSheet("employee_spritesheet1.png");
	}


	//@Override
	public void render(GUIContext gc, Graphics g){
		

		//leave this at the end
		super.render(gc,g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta){

		


		//leave this at the end of this call. Call some parent functionality
		super.update(gc,game,delta);
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