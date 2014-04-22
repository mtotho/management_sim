
package mftoth.restaurantsim.ogl;

//import mftoth.entities.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.*;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.HashMap;
import mftoth.restaurantsim.logic.*;


public class StateGame extends BasicGameState{

	
	private int ID = 3;
	private StateBasedGame game;

  private Font font;
  private TrueTypeFont ttf;

	private GLCustomer c1;
  private HashMap<Customer,GLCustomer> cust_map;
	private ArrayList<GLCustomer> gl_customers;
  private ArrayList<GLEmployee> gl_employees;
  private ArrayList<Customer> customers;
  private ArrayList<Employee> employees;
  private Time timer;
  private int counter;

  private GLEmployee e1;  

  private HashMap<String, GLPanel> panels;
  private GLPanel active_panel;

  private OGLMap map;

  public Restaurant restaurant;

  public Foodline foodline;
  public Foodline waitline;

  public StateGame(Restaurant restaurant){
    super();
    this.restaurant=restaurant;

  }

    @Override 
  public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {
       	this.game=game;

        //Initiate the Game Map
        map = new OGLMap();


        //Get the employee/customer entities from the logical restaurant.
        customers = restaurant.getCustomers();
        employees = restaurant.getEmployees();


        timer = restaurant.getTimer();
        foodline = new Foodline(8, 17, FigureDirection.RIGHT, 12);
        waitline = new Foodline(13, 14, FigureDirection.RIGHT, 12);


        this.restaurant.setStateGame(this);

        this.restaurant.setFoodline(foodline);

        //Initiate a GL employee for each logical employee, store in ArrayList
        gl_employees = new ArrayList<GLEmployee>();
        for(int i=0; i<employees.size(); i++){
          gl_employees.add(new GLEmployee(gc, map, this, employees.get(i)));
        }

        //Initialize hashmap between the logical customer and graphical customer
        cust_map = new HashMap<Customer, GLCustomer>();
      	gl_customers= new ArrayList<GLCustomer>();

        //Setup panels
        panels = new HashMap<String, GLPanel>();

        GLOverviewPanel overviewPanel = new GLOverviewPanel(restaurant, gc, this);
        GLEmployeePanel employeePanel = new GLEmployeePanel(restaurant, gc, this);
        GLTaskPanel taskPanel = new GLTaskPanel(restaurant, gc, this);
        GLInventoryPanel inventoryPanel = new GLInventoryPanel(restaurant, gc, this);
        addPanel("OVERVIEW", overviewPanel);
        addPanel("EMPLOYEES", employeePanel);
        addPanel("TASKS", taskPanel);
        addPanel("INVENTORY", inventoryPanel);

        //set this panel to active
        activatePanel("OVERVIEW");

        counter=0;
       
    }

    public void addPanel(String index, GLPanel panel){
      panels.put(index,panel);
    }

    public void activatePanel(String index){
      if(panels.containsKey(index)){  
        GLPanel panel = panels.get(index);

        if(active_panel!=null){
          active_panel.setActive(false);
        }

        active_panel = panel;
        for(GLPanel panelValue : panels.values()){
          panelValue.setActive(false);
        }
        panel.setActive(true);
      }
    }

    //removeCustomer(): remove the specified customer from the game. 
    public void removeCustomer(GLCustomer glcust, Customer logic_cust){

      //Remove the map between logical customer and graphical
      cust_map.remove(logic_cust);

      //Remove the logical customer from the customers arraylist
      customers.remove(logic_cust);

      //remove the graphical customer from the gl_customers arraylist
      gl_customers.remove(glcust);
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
    	
      //Render the floor layer of the map
      map.render(0);
      map.render(1);

  		g.setColor(Color.black);

      if(active_panel!=null)
        active_panel.render(gc, g);

      //Render all the customers that exist
    	for(int i=0; i<customers.size(); i++){
        if(cust_map.containsKey(customers.get(i))){
          GLCustomer glcust = cust_map.get(customers.get(i));
          glcust.render(gc, g); 
        }
    	}//end: foreach customer

      //Render all the employees that exist
      for(int j=0; j<gl_employees.size(); j++){
          GLEmployee glemp = gl_employees.get(j);
          glemp.render(gc, g); 
      }

      //Render all the map layers that should appear "in front of" the employees and customers
      map.render(2);
      map.render(3);
      map.render(4);
      map.render(5);
      map.render(6);
    } 


 
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
       
        //Call  the restaurant update 
        restaurant.updateTime(delta);
        if(counter % 3==0 )
          restaurant.update();
       
      


        //Check for new logical customer and create gl customer
        for(int i=0; i<customers.size(); i++){

          //There is no matching GLCustomer for this logical customer
          if(!cust_map.containsKey(customers.get(i))){
              
            //Pull the ogical customer from the arraylist
            Customer logic_cust = customers.get(i);

            //Create a new GLCustomer and pass the logical customer to it
            GLCustomer glcust = new GLCustomer(gc,map, this, logic_cust);

            //Add the map between the logic/gui objects using a hashmap
            cust_map.put(customers.get(i), glcust); 

      
          //Otherwise we can simply update the customer
          }else{
              GLCustomer glcust =  cust_map.get(customers.get(i));
              glcust.update(gc, game, delta);
          }
        }//end for

        for(int i=0; i<gl_employees.size(); i++){
          gl_employees.get(i).update(gc, game, delta);
        }

      	Input input = gc.getInput();
      	
        int mousex = input.getMouseX();
        int mousey = input.getMouseY();

        //Move the employee to where we left click
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
        

            int destX = map.getTileX(mousex);
            int destY = map.getTileY(mousey);

            if(destX<map.getWidthInTiles() && destY<map.getHeightInTiles() && !map.blocked(null, destX, destY) && mousex<=640){
              gl_employees.get(0).setPath(destX, destY);
            }
          
        }

        counter++;   
 
    }
 

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return ID;
    }

    public void keyReleased(int key, char c) {
	    switch(key) {
	    case Input.KEY_ESCAPE:
	        game.enterState(1);
	        break;
	    case Input.KEY_2:
	        // TODO: Implement later
	        break;
	    case Input.KEY_3:
	        // TODO: Implement later
	        break;
	    default:
	        break;
	    }
	}

	public void keyPressed(int key, char c){
		switch(key) {
	    case Input.KEY_UP:
	       // game.enterState(1);

	    	
	        break;
	    case Input.KEY_RIGHT:
	        // TODO: Implement later
	        break;
	    case Input.KEY_LEFT:
	        // TODO: Implement later
	        break;
        case Input.KEY_DOWN:
	        // TODO: Implement later
	        break;
	    default:
	        break;
	    }
	}

	public void leave(GameContainer gc, StateBasedGame game){
		//System.out.println("game left");
	}

	public void enter(GameContainer gc, StateBasedGame game){
    clean_up(gc, game);
	}


	//Clean up entities
	public void clean_up(GameContainer gc, StateBasedGame game){
    cust_map.clear();
    gl_customers.clear();
    gl_employees.clear();
    //counter=0;

    try{
        init(gc, game);
    }catch(SlickException e){
      System.out.println("slick exception: " + e);
    }
	}

  

}