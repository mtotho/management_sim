package mftoth.restaurantsim.ogl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;
import mftoth.restaurantsim.logic.Restaurant;
//import mftoth.restaurantsim.ogl.states.*;

public class OGLGameContainer extends StateBasedGame{

	private Restaurant restaurant;

	public OGLGameContainer(String title, Restaurant restaurant){
		super(title);
		this.restaurant=restaurant;
	}

	 @Override
    public void initStatesList(GameContainer container) throws SlickException {

    	//Initialize State List
 		addState(new StateIntro(restaurant));
 	 	addState(new StateMainMenu(restaurant));
 	 	addState(new StateGame(restaurant));
 
    }
 
}