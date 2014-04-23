package mftoth.restaurantsim.ogl;

//import mftoth.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.gui.AbstractComponent.*;
 
import mftoth.restaurantsim.logic.*;
import pjwelch.restaurantsim.database.*;

import java.awt.Font;
import org.newdawn.slick.UnicodeFont;
import java.util.List;

public class StateLoadGame extends BasicGameState{

	//private GLButton btnNewGame; 
	//private OGLGameContainer game;

	//public ViewMainMenu(OGLGameContainer game){
		//this.game=game;
		//super(screen);

		//Width: 60% of display, height: 20% of display, X: 20% from left, Ystart: 40% from top
		
		//btnNewGame= new GLButton(Display.getWidth()*0.2, Display.getHeight()-Display.getHeight()*0.4, Display.getWidth()*0.6,Display.getHeight()*0.2, "New Game");
		//btnNewGame= new GLButton(100f,40f, 20f, 20f, "New Game");
		
	//}

	private int ID = 5;
	private StateBasedGame game;
	private TrueTypeFont ttfont;
	private Font font;
	private int fontSize = 19;
	private TextField text;
	private GLButton btnStartGame;
	private GLButton btnBack;
	private String playerName;

	
	private DBmapper db;
	public List<Player_model> players;
	public Player_model player;

	private GLScrollablePanel<Player_model> loadgames;



	//private GLButton btnNewGame;
	//private GLButton btnAbout;

	private Restaurant restaurant;

	public StateLoadGame(Restaurant restaurant, DBmapper db){
		super();
		this.restaurant=restaurant;
		this.db = db;
		font = new Font("Verdana", Font.BOLD, fontSize);
		ttfont = new TrueTypeFont(font, false);

		players = db.select(new Player_model());	

	}

	@Override
    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {
        this.game=game;
		btnStartGame = new GLButton(gc, "Start Game", 499, 80);
		btnStartGame.setLocation(230, 350);

		btnBack = new GLButton(gc, "Back", 160, 60);
		btnBack.setLocation(740, 420);
		btnBack.setLabelX(25);

		btnStartGame.setLabelX(170);
		//btnStartGame.setY(300);
 		
 		//text = new TextField(gc, ttfont, 250, 97, 200, 25);
 		
 		loadgames = new GLScrollablePanel<Player_model>(restaurant, gc,200, 75,550, 250);
 	
 	
 		//Start the button out disabled
	 	btnStartGame.setDisabled(true);
 		/*
 		loadgames.add("a");
 		loadgames.add("b");
 		loadgames.add("c");
 		loadgames.add("d");
 		loadgames.add("e");
 		loadgames.add("f");
 		loadgames.add("g");
 		loadgames.add("h");
 		loadgames.add("i");
 		loadgames.add("j");
 		loadgames.add("k");
 		loadgames.add("l");
 		loadgames.add("z");
 		loadgames.add("x");
 		loadgames.add("@");
 		loadgames.add("#");
 		loadgames.add("$");
 		loadgames.add("k");
 	loadgames.add("l");
 		loadgames.add("z");
 		loadgames.add("x");
 		loadgames.add("@");
 		loadgames.add("#");
 		loadgames.add("$");
 		 		loadgames.add("f");
 		loadgames.add("g");
 		loadgames.add("h");
 		loadgames.add("i");
 		loadgames.add("j");
 		loadgames.add("k");
 		loadgames.add("l");
 		loadgames.add("z");
 		loadgames.add("x");
 		loadgames.add("@");
 		loadgames.add("#");
 		loadgames.add("$");
 		loadgames.add("k");
 		loadgames.add("l");
 		loadgames.add("z");
 		loadgames.add("x");
 		loadgames.add("@");
 		loadgames.add("#");
 		loadgames.add("$");
*/



    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {

       	g.setColor(Color.white);
		g.fillRect(0,0, gc.getWidth(), gc.getHeight());

		for(int i=0; i<players.size(); i++){
 			Player_model player = players.get(i);

 			if(!loadgames.contains(player))
 				loadgames.add(player.getName(), player);
 		}

	    loadgames.render(gc,g);
		
	  
	   	btnStartGame.render(gc, g);
	   	btnBack.render(gc, g);
	  	


	    g.setColor(Color.black);
	    g.drawString("Select a Restaurant profile to load: ", 50, 25);

	   // g.drawString("1. Press 1 to start game", 50, 130);

    }
 
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
        // TODO Auto-generated method stub
 		loadgames.update(container, game, delta);

 		//if not items are selected, disable startgame button
 		if(loadgames.itemSelected()==false){
 			btnStartGame.setDisabled(true);

 		//enable start button if something is selected
 		}else{
 			btnStartGame.setDisabled(false);
 		}


 		if(btnStartGame.isPressed()){
 			
			//int selected_index = loadgames.getSelectedIndex();
			//System.out.println("selected index: " + selected_index);
 			Player_model player = loadgames.getSelected();// players.get(selected_index);

 			System.out.println("Selected player: " + player.getName());

 			restaurant.loadGame(player);
 			
 			//StateGame stategame = game.getState(3);
 			//stategame.clean_up(container, game);

 			game.enterState(3);
 			
 		}
		if(btnBack.isPressed()){
				game.enterState(2);
		}
    }
 
    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return ID;
    }

    public void keyReleased(int key, char c) {
	    switch(key) {
	    case Input.KEY_1:
	        game.enterState(2);
	        break;
	    case Input.KEY_2:
	        break;
	    case Input.KEY_3:
	        // TODO: Implement later
	        break;
	    default:
	        break;
	    }
	}

	public void leave(GameContainer gc, StateBasedGame game){
		//System.out.println("game left");
		players.clear();
		loadgames.clear();
	}

	public void enter(GameContainer gc, StateBasedGame game){
		System.out.println("load game entered");
		players = db.select(new Player_model());

		System.out.println("player size: " + players.size());	
	}

	public void mousePressed(int button, int posx, int posy){

		/*if(btnStartGame.isPressed()){
			String playerName = text.getText();
			players = (db.selectPlayer(new Player_model(), playerName));
			player = players.get(0);
			restaurant.loadGame(player);
			game.enterState(3);

		}*/




	}

}