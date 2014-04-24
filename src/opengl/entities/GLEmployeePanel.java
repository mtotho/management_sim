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
	private GLButton btnBack;
	private GLButton btnEmployeeList;
	private GLScrollablePanel<Employee> scroll_panel;

	private TrueTypeFont ttfont;
	private Font font;
	private int fontSize = 15;

	public GLEmployeePanel(Restaurant restaurant, GameContainer gc, StateGame game) throws SlickException{
		super(restaurant, gc);
		this.game = game;
		//this.gameCon = gc;
		this.restaurant = restaurant;


 	 	font = new Font("Verdana", Font.BOLD, fontSize);
		ttfont = new TrueTypeFont(font, false);

		btnBack=new GLButton(gc, "Back",290, 80);
		btnBack.setLocation(x+15, 370);
		btnBack.setLabelX(80);	

		scroll_panel = new GLScrollablePanel<Employee>(restaurant, gc, x+15, y+30, 290, 250);

        padding=10;

        employees = restaurant.getEmployees();


	}

	public void render(GUIContext gc, Graphics g) throws SlickException{
		
		g.setColor(Color.black);
		g.fillRect(x,y, width, height);

		g.setFont(ttfont);
		g.setColor(Color.orange);
	    g.drawString("Employees", x+10, y+5);

	    for(int i=0; i<employees.size();i++){
	    	Employee tempEmployee = employees.get(i);

	    	if(!scroll_panel.contains(tempEmployee)){

	    		scroll_panel.add(tempEmployee.getName(), tempEmployee);

	    	}
		   

		}//end for

		
		btnBack.render(gc,g);
		scroll_panel.render(gc, g);
		//btnEmployeeList.render(gc,g);
	
	}

	public void mousePressed(int button, int posx, int posy){
	   // ArrayList<GLButton> buttons = getButtons();
	    //System.out.println(buttons.size());
	    
	    //Only register mouse clicks if mouseDown is false (this should prevent the click action being called multiple times per click)
	    if(!mouseDown && active){	
	    	mouseDown=true; //change to true so we cannot re enter this block during this click
	    	
	    /*	if(btnBack.isPressed()){
	    		game.activatePanel("OVERVIEW");
	    	}*/

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
