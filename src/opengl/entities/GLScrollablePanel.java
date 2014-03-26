package mftoth.restaurantsim.ogl;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import java.util.Random;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.awt.Font;
import java.util.Set;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;

public class GLScrollablePanel extends GLPanel{


	public GLScrollablePanel(Restaurant restaurant, GameContainer gc){
		super(restaurant, gc);

		this.active = true;

	  
	}

	public void render(GUIContext gc, Graphics g){


		// This is used to determine the distance between buttons
		int yDif = (int)(height/(buttons.size()+1));

	   	g.setColor(Color.white);
		g.fillRect(x,y, width, height);

  }

}
