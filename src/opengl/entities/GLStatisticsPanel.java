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
import java.util.List;

import java.util.ArrayList;
import java.awt.Font;
import mftoth.restaurantsim.logic.*;
import pjwelch.restaurantsim.database.*;

import org.newdawn.slick.UnicodeFont;

public class GLStatisticsPanel extends GLPanel{
	
	private StateGame game;
	private GameContainer gameCon;
	private Scheduler scheduler;
	private Restaurant restaurant;
	private GLScrollablePanel<Items_model> scroll_panel;

	private List<Items_model> menu;
	private List<Inventory_model> inventory;
	private boolean active_list = false;

	private GLButton btnBack;
	private GLButton btnBuyInventory;


	public GLStatisticsPanel(Restaurant restaurant, GameContainer gc, StateGame game) throws SlickException{
		super(restaurant, gc);
		this.game = game;
		this.gameCon = gc;
		this.restaurant = restaurant;
		this.inventory = restaurant.getInventory();
		this.menu = restaurant.getMenu();



		btnBack = new GLButton(gc, "Back", 290, 80);
		

		btnBack.setY(370);
		btnBack.setX(x+15);
		btnBack.setLabelX(25);


		//addButton(gc,"btnBuyMore", "Buy More", 300, 100);
        //addButton(gc, "btnBack", "Back", 300, 100);

        padding=10;


        scroll_panel = new GLScrollablePanel<Items_model>(restaurant, gc, x+15, y+30, 290, 250);
   
	}

	public void render(GUIContext gc, Graphics g) throws SlickException{
		if(!active_list){
			int yDif = (int)(height/(3));

		   	g.setColor(Color.black);
			g.fillRect(x,y, width, height);

			g.setColor(Color.orange);
		    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    g.drawString("Day: " + timer.getDay(), x+120, y+5);

		    g.drawString("Money: $" + restaurant.getMoney(), x+200, y+5);

		    //Object[] keys = buttons.keySet().toArray();

		    btnBack.render(gc, g);

		   /* for(int i=0; i<2; i++){
		    
					GLButton tempButton = buttons.get(keys[i]);
					tempButton.setLabelX(60);
					tempButton.setX(padding+x);
					tempButton.setY((yDif * (i+1)) - 60);
					tempButton.render(gc, g);
			}*/
		}
		
			

			
			//g.setColor(Color.black);
			//g.fillRect(x,y, width, height);

		//	g.setColor(Color.orange);
		  //  g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    //g.drawString("Day: " + timer.getDay(), x+250, y+5);

			ArrayList<String> renderedButtons = new ArrayList<String>();
			
			//Remove the tasks that have expired
		

		for(int i = 0; i < inventory.size(); i++){

			String itemName = menu.get(i).getName();
			int itemQty = inventory.get(i).getAmount_Sold();

			if(!scroll_panel.contains(menu.get(i))){
				scroll_panel.add(itemName + ": " + itemQty + " Sold", menu.get(i));
			}
			else{
				scroll_panel.updateLabel(menu.get(i), itemName + ": " + itemQty + " Sold ");
			} 
		}

		scroll_panel.render(gc, g);	

 	 }
  
  	public void mouseReleased(int button, int x, int y){
  		
  		
  		mouseDown=false; //change flag back to false after click is done
  	 }
  
  	public void mousePressed(int button, int posx, int posy){

  		//if(!mouseDown && active){
  		if(btnBack.isPressed()){

  			game.activatePanel("OVERVIEW");

  		}
  		
  	}


}
  
  

