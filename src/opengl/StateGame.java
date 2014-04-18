
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

  //private GLPanel buttonPanel;
  private int buttonMenu;
  private GLButton btn_spawnCust;
  private GLButton btn_randCust;
  private GLButton btn_next1;
  private GLButton btn_back2;

  private Font font;
  private TrueTypeFont ttf;

	private GLCustomer c1;
  private HashMap<Customer,GLCustomer> cust_map;
	private ArrayList<GLCustomer> gl_customers;
  private ArrayList<GLEmployee> gl_employees;
  private ArrayList<Customer> customers;
  private ArrayList<Employee> employees;
  private Time timer;

  private GLEmployee e1;

  private ArrayList<GLTile> blocking = new ArrayList<GLTile>();
  private ArrayList<GLTile> cleaning = new ArrayList<GLTile>();

  private HashMap<String, GLPanel> panels;
  private GLPanel active_panel;

	//private Path customerPath;
	//private TiledMap map;
  private OGLMap map;
  private AStarPathFinder astar;

  public Restaurant restaurant;

  public Foodline foodline;
  public Foodline waitline;
	//private boolean[][] blocking;

  public StateGame(Restaurant restaurant){
    super();
    this.restaurant=restaurant;

    //
    customers = restaurant.getCustomers();
    employees = restaurant.getEmployees();


    timer = restaurant.getTimer();
    foodline = new Foodline(8, 17, FigureDirection.RIGHT, 14);
    waitline = new Foodline(12, 12, FigureDirection.RIGHT, 12);

    this.restaurant.setStateGame(this);

    this.restaurant.setFoodline(foodline);
  }

    @Override 
  public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {







       	this.game=game;
        map = new OGLMap();


        blocking = map.getBlockedTiles();

        /*
        for(int i=0;i<blocking.size();i++){
          //System.out.println("X: " + blocking.get(i).getX() + " Y: " + blocking.get(i).getY());
        }

        cleaning = map.getCleaningTiles();
        for(int i=0;i<cleaning.size();i++){
         // System.out.println("X: " + cleaning.get(i).getX() + " Y: " + cleaning.get(i).getY());
        }*/

        gl_employees = new ArrayList<GLEmployee>();
        for(int i=0; i<employees.size(); i++){
          gl_employees.add(new GLEmployee(gc, map, this, employees.get(i)));
        }


        //GLCustomer c1 = new GLCustomer(gc,map); 

        cust_map = new HashMap<Customer, GLCustomer>();
      	//c1 = new GLCustomer(gc, map);
        //c1.setMap(map);
      	//c1.setLocation(map.getAbsX(3), map.getAbsY(1));
      	gl_customers= new ArrayList<GLCustomer>();

        //create test employee
        //e1 = new GLEmployee(gc, map, this);

        panels = new HashMap<String, GLPanel>();

        GLOverviewPanel overviewPanel = new GLOverviewPanel(restaurant, gc, this);
        GLEmployeePanel employeePanel = new GLEmployeePanel(restaurant, gc, this);
        GLTaskPanel taskPanel = new GLTaskPanel(restaurant, gc, this);
        addPanel("OVERVIEW", overviewPanel);
        addPanel("EMPLOYEES", employeePanel);
        addPanel("TASKS", taskPanel);

        //set this panel to active
        activatePanel("OVERVIEW");
      
        astar = new AStarPathFinder(map, 400, false);
       
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

    public void removeCustomer(GLCustomer glcust, Customer logic_cust){


     // Customer logic_cust = cust_map.get(cust);
      cust_map.remove(logic_cust);
      customers.remove(logic_cust);
      gl_customers.remove(glcust);
    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
    	
      map.render(0);

  		g.setColor(Color.black);

      if(active_panel!=null)
        active_panel.render(gc, g);

      //1.render(gc,g);
      /*if(buttonMenu==1){
        btn_spawnCust.render(gc, g);
        btn_randCust.render(gc, g);
        btn_next1.render(gc, g);
      }
      else{
        btn_back2.render(gc, g);
      }*/

      //c1.render(gc,g);

	   // g.drawString("Game State", 50, 10);

	   //	c1.setLocation(c1.getX(),c1.getY());
	   // c1.render(gc, g);

      //font = new Font("Verdana", 20);
      //TrueTypeFont ttf = new TrueTypeFont(font, true);
      //g.setFont(ttf);
      //g.drawString("Number of customers: "+ Integer.toString(customers.size()), 700, 100);
      //Render the customers


    	for(int i=0; i<customers.size(); i++){
        if(cust_map.containsKey(customers.get(i))){
          GLCustomer glcust = cust_map.get(customers.get(i));
          glcust.render(gc, g); 
        }
    	}
      for(int j=0; j<gl_employees.size(); j++){
        //if(cust_map.containsKey(customers.get(i))){
          GLEmployee glemp = gl_employees.get(j);
          glemp.render(gc, g); 
       // }
      }
      map.render(1);
      map.render(2);
      map.render(3);
      map.render(4);
      map.render(5);
    } 


 
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
        //pass time to restaurant
        restaurant.update(delta);
       
      



       // System.out.println(timer.getSeconds());
      //  e1.update(gc,game,delta);
      


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

        //System.out.println("customer amount: " + customers.size());
      	Input input = gc.getInput();
      	
        int mousex = input.getMouseX();
        int mousey = input.getMouseY();

        //System.out.println("Map coord: x- " + map.getTileX(mousex) + " | y- " +map.getTileY(mousey));
        if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
         
         // GLCustomer glcust = cust_map.get(customers.get(0));
         // System.out.println(glcust);

         //if(!glcust.isPathing()){
           

            int destX = map.getTileX(mousex);
            int destY = map.getTileY(mousey);

            if(destX<map.getWidthInTiles() && destY<map.getHeightInTiles() && !map.blocked(null, destX, destY) && mousex<=640){
              gl_employees.get(0).setPath(destX, destY);
            }


            
          //}

          
        }
      //	if(!blocked(c1.getX(), c1.getY())){
	  		 if(input.isKeyDown(Input.KEY_UP)){
	  		
	      	}
	      	if(input.isKeyDown(Input.KEY_RIGHT)){
	  
	      	}
	  		  if(input.isKeyDown(Input.KEY_DOWN)){

	      	}
	      	if(input.isKeyDown(Input.KEY_LEFT)){
	      
	      	}  
      
           
        	if(input.isKeyDown(Input.KEY_Q)){
        		
        	}     
 
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
		//System.out.println("game entered");
	}


	//Clean up entities
	public void clean_up(){

	}

  

}