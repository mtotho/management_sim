
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

import java.util.ArrayList;
import java.awt.Font;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;

public class GLOverviewPanel extends GLPanel{

	public GLOverviewPanel(Restaurant restaurant, GameContainer gc) throws SlickException{
		super(restaurant, gc);


		addButton(gc,"btnAddCustomer", "Add Customer", 300, 100);
        addButton(gc,"btnDebug", "Numbah 2", 300, 100);

        padding=10;

	}

	public void render(GUIContext gc, Graphics g){
		

		//Render parent, comment this out if you want to replace default render
		super.render(gc,g);
	}

	public void mousePressed(int button, int posx, int posy){
	   // ArrayList<GLButton> buttons = getButtons();
	    //System.out.println(buttons.size());

	    if(buttons.get("btnAddCustomer").isPressed()){
	      restaurant.addCustomer();
	    }
	    if(buttons.get("btnDebug").isPressed()){
	     //1 GLCustomer glcust =  cust_map.get(customers.get(0));
	      //glcust.setPath(cleaning.get(0).getX(), cleaning.get(0).getY());
	    }
  }
}