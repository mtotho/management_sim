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

public class GLEmployeePanel extends GLPanel{
	
	private StateGame game;
	private GameContainer gameCon;
	private Restaurant restaurant;

	private ArrayList<Employee> employees;
	private Employee active_employee;
	private boolean active_list = false;
	
	public GLEmployeePanel(Restaurant restaurant, GameContainer gc, StateGame game) throws SlickException{
		super(restaurant, gc);
		this.game = game;
		this.gameCon = gc;
		this.restaurant = restaurant;


		addButton(gc,"btnEmployeeList", "Employee List", 300, 100);
        addButton(gc, "btnBack", "Back", 300, 100);

        padding=10;

        employees = restaurant.getEmployees();
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
		else if(active_list && active_employee!=null){
			g.setColor(Color.black);
			g.fillRect(x,y, width, height);

			g.setColor(Color.orange);
		    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    g.drawString("Day: " + timer.getDay(), x+250, y+5);
		}
		else{
			g.setColor(Color.black);
			g.fillRect(x,y, width, height);

			g.setColor(Color.orange);
		    g.drawString("Time: " + timer.getFormattedTime(), x+10, y+5);

		    g.drawString("Day: " + timer.getDay(), x+250, y+5);

		    employees = restaurant.getEmployees();
		    for(int i=0; i<employees.size();i++){
		    	String employeeName = employees.get(i).getName();
		    	if(!buttons.containsKey("btn"+employeeName)){
		    		addButton(gameCon, "btn"+employeeName, employeeName, 300, 80);
		  		}
		  		else{
		  			//System.out.println(buttons.get("btn"+employeeName).getName());
		  		}
		  		GLButton tempButton = buttons.get("btn" + employeeName);
		    	tempButton.setLabelX(60);
				tempButton.setX(padding+x);
				tempButton.setY((y+40)+(80*(i+1)));
				tempButton.render(gc, g);	
		    	//g.drawString("Employee: " + employees.get(i).getName(), x+30, (y+30)+(30*(i+1)));*/
		    }

		    GLButton tempButton = buttons.get("btnBack");
		    tempButton.setLabelX(60);
			tempButton.setX(padding+x);
			tempButton.setY(y+350);
			tempButton.render(gc, g);
		}
	}

	public void mousePressed(int button, int posx, int posy){
	   // ArrayList<GLButton> buttons = getButtons();
	    //System.out.println(buttons.size());
	    
	    //Only register mouse clicks if mouseDown is false (this should prevent the click action being called multiple times per click)
	    if(!mouseDown && active){	
	    	mouseDown=true; //change to true so we cannot re enter this block during this click
	    	
		    if(buttons.get("btnEmployeeList").isPressed() && !active_list){
		      active_list = !active_list;
		    }
		    if(buttons.get("btnBack").isPressed() && !active_list){
		    	game.activatePanel("OVERVIEW");
		    }
		   	else if(buttons.get("btnBack").isPressed() && active_list){
		    	active_list = !active_list;
		    }
		    //if(buttons.get("btn" + employees.get(0).getName()).isPressed()){
		    //	System.out.println(employees.get(0).getName());
		    //}
		    for(int i=0; i<employees.size(); i++){
		    	String employeeName = employees.get(i).getName();
		    	if(buttons.get("btn"+employeeName)!=null && buttons.get("btn" + employeeName).isPressed() && active_list){
		    		System.out.println("btn" + employeeName);
		    	}
		    }
	    }
  }
  
  	public void mouseReleased(int button, int x, int y){
  		
  		
  		mouseDown=false; //change flag back to false after click is done
  	} 
  
  
  
}
