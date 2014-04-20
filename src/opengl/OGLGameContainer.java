package mftoth.restaurantsim.ogl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;
import mftoth.restaurantsim.logic.Restaurant;
import pjwelch.restaurantsim.database.*;
//import mftoth.restaurantsim.ogl.states.*;

public class OGLGameContainer extends StateBasedGame{

	private Restaurant restaurant;
	public DBmapper db;

	public OGLGameContainer(String title, DBmapper db){
		super(title);
		
		this.db = db;

		//Determine which restaurant to load from DB;

		restaurant = new Restaurant(db);
		//this.restaurant=restaurant;
	}

	 @Override
    public void initStatesList(GameContainer container) throws SlickException {

    	//Initialize State List
 		addState(new StateIntro(restaurant));
 	 	addState(new StateMainMenu(restaurant));
 	 	addState(new StateGame(restaurant));
 	 	addState(new StateNewGame(restaurant, db));
 	 	addState(new StateLoadGame(restaurant, db));
 
    }
 
}