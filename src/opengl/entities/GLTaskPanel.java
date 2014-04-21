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

public class GLTaskPanel extends GLPanel{
	
	private StateGame game;
	private GameContainer gameCon;
	private Scheduler scheduler;
	private Restaurant restaurant;
	private GLScrollablePanel scroll_panel;

	private ArrayList<Task> tasks = new ArrayList<Task>();
	private boolean active_list = false;
	
	public GLTaskPanel(Restaurant restaurant, GameContainer gc, StateGame game) throws SlickException{
		super(restaurant, gc);
		this.game = game;
		this.gameCon = gc;
		this.restaurant = restaurant;
		this.scheduler = restaurant.getScheduler();


		addButton(gc,"btnTaskList", "Task List", 300, 100);
        addButton(gc, "btnBack", "Back", 300, 100);

        padding=10;


        scroll_panel = new GLScrollablePanel(restaurant, gc, x+15, y+30, 290, 250);
   
	}

	public void render(GUIContext gc, Graphics g) throws SlickException{
		if(!active_list){
			int yDif = (int)(height/(3));

		   	g.setColor(Color.black);
			g.fillRect(x,y, width, height);

			g.setColor(Color.orange);
		    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    g.drawString("Day: " + timer.getDay(), x+250, y+5);

		    Object[] keys = buttons.keySet().toArray();
		    
		    for(int i=0; i<2; i++){
		    
					GLButton tempButton = buttons.get(keys[i]);
					tempButton.setLabelX(60);
					tempButton.setX(padding+x);
					tempButton.setY((yDif * (i+1)) - 60);
					tempButton.render(gc, g);
			}
		}
		else{
			

			
			//g.setColor(Color.black);
			//g.fillRect(x,y, width, height);

			g.setColor(Color.orange);
		    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    g.drawString("Day: " + timer.getDay(), x+250, y+5);

		    scheduler = restaurant.getScheduler();
			tasks = scheduler.getTaskArray(3);
			ArrayList<String> renderedButtons = new ArrayList<String>();
	
		    for(int i=0; i<tasks.size();i++){
		    	String taskName = tasks.get(i).getName();
		    	Integer timeRemaining = tasks.get(i).getTimeRemaining();
		    	
		    	//if(i<scroll_panel.size()){

		    	//String output = String.format(taskName + " : " + (int)(timeRemaining / 1000) + "s remaining");
		    		scroll_panel.add(taskName + " : " + (int)(timeRemaining / 1000) + "s remaining");
		    	//}		
		    	
		    	/*
		    	if(!buttons.containsKey("btn"+taskName)){
		    		addButton(gameCon, "btn"+taskName, taskName, 300, 80);
		  		}
		  		else{
		  			//.out.println("Task " + (i+1) + ": " + buttons.get("btn"+taskName).getName());
		  		}
		  		if(!renderedButtons.contains(tasks.get(i).getName())){
			  		GLButton tempButton = buttons.get("btn" + taskName);
			  		//tempButton.changeName(tempButton.getName() + " Time: " + timeRemaining);
			    	tempButton.setLabelX(40);
			    	//tempButton.setUpFonts(8);
					tempButton.setX(padding+x);
					tempButton.setY((y+40)+(80*(i+1)));
					renderedButtons.add(tasks.get(i).getName());
					tempButton.render(gc, g);	
				}*/
		    	//g.drawString("Employee: " + employees.get(i).getName(), x+30, (y+30)+(30*(i+1)));*/
		    }

		    GLButton tempButton = buttons.get("btnBack");
		    tempButton.setLabelX(60);
			tempButton.setX(padding+x);
			tempButton.setY(y+350);
			tempButton.render(gc, g);

			scroll_panel.render(gc, g);	
			scroll_panel.clear();
		}
	}

	public void mousePressed(int button, int posx, int posy){
	   // ArrayList<GLButton> buttons = getButtons();
	    //System.out.println(buttons.size());
	    
	    //Only register mouse clicks if mouseDown is false (this should prevent the click action being called multiple times per click)
	    if(!mouseDown && active){	
	    	mouseDown=true; //change to true so we cannot re enter this block during this click
	    	
		    if(buttons.get("btnTaskList").isPressed() && !active_list){
		      active_list = !active_list;
		    }
		    if(buttons.get("btnBack").isPressed() && !active_list){
		    	game.activatePanel("OVERVIEW");
		    }
		   	else if(buttons.get("btnBack").isPressed() && active_list){
		    	active_list = !active_list;
		    }
	    }
  }
  
  	public void mouseReleased(int button, int x, int y){
  		
  		
  		mouseDown=false; //change flag back to false after click is done
  	} 
  
  
  
}
