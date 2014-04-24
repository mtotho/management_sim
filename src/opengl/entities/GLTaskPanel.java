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
	private GLScrollablePanel<Task> scroll_panel;

	private ArrayList<Task> tasks = new ArrayList<Task>();
	private ArrayList<Task> last_tasks = new ArrayList<Task>();
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


        scroll_panel = new GLScrollablePanel<Task>(restaurant, gc, x+15, y+30, 290, 250);
   
	}

	public void render(GUIContext gc, Graphics g) throws SlickException{
		if(!active_list){
			int yDif = (int)(height/(3));

		   	g.setColor(Color.black);
			g.fillRect(x,y, width, height);


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
			
			g.setColor(Color.orange);
		    g.drawString("Ongoing Tasks", x+10, y+5);

		    scheduler = restaurant.getScheduler();
			tasks = scheduler.getTaskArray(3);
			ArrayList<String> renderedButtons = new ArrayList<String>();
			
			//Remove the tasks that have expired
			for(int i=0; i<last_tasks.size(); i++){
				Task prev_task = last_tasks.get(i);

				if(!tasks.contains(prev_task)){
					scroll_panel.remove(prev_task);
				}

			}


		    for(int i=0; i<tasks.size();i++){
		    

		    	String taskName = tasks.get(i).getName();
		    	Integer timeRemaining = tasks.get(i).getTimeRemaining();
		    	
		    	

		    	if(!scroll_panel.contains(tasks.get(i))){
		    		scroll_panel.add(restaurant.timer.toSeconds(timeRemaining) + "s | " + taskName, tasks.get(i));
		    	}else{
		    		scroll_panel.updateLabel(tasks.get(i), restaurant.timer.toSeconds(timeRemaining) + "s | " + taskName);	    		
	    		}


		    }

		    last_tasks = (ArrayList<Task>)tasks.clone();

		    GLButton tempButton = buttons.get("btnBack");
		    tempButton.setLabelX(60);
			tempButton.setX(padding+x);
			tempButton.setY(y+350);
			tempButton.render(gc, g);

			scroll_panel.render(gc, g);	
			//scroll_panel.clear();
		}
	}

	public void mousePressed(int button, int posx, int posy){
	    
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
