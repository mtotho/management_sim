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

public class GLCustomer extends GLMoveableEntity{


	public GLCustomer(GameContainer gc, OGLMap map) throws SlickException{
		super(gc, map);
		
		//default some values		
		x=16;
		y=432;
		
		dx=0.2;
		dy=0.2;

		
		//Set the spritesheet path. Will use default if not indicated
		setSpriteSheet("man_whiteshirt.png");
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
		output+="<Customer>\n";
		output+="|xpos (absolute): " + x + "\n";
		output+="|ypos (absolute): " + y + "\n";
		output+="|xtile          : " + map.getTileX(x) + "\n";
		output+="|ytile          : " + map.getTileY(y) + "\n";

		return output;
	}


}