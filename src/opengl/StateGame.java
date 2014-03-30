
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
  private ArrayList<Customer> customers;
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
	//private boolean[][] blocking;

  public StateGame(Restaurant restaurant){
    super();
    this.restaurant=restaurant;

    //
    customers = restaurant.getCustomers();
    timer = restaurant.getTimer();
    foodline = new Foodline();

  }

    @Override 
  public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {

       	this.game=game;
        map = new OGLMap();

        blocking = map.getBlockedTiles();
        for(int i=0;i<blocking.size();i++){
          //System.out.println("X: " + blocking.get(i).getX() + " Y: " + blocking.get(i).getY());
        }

        cleaning = map.getCleaningTiles();
        for(int i=0;i<cleaning.size();i++){
         // System.out.println("X: " + cleaning.get(i).getX() + " Y: " + cleaning.get(i).getY());
        }

        //GLCustomer c1 = new GLCustomer(gc,map); 

        cust_map = new HashMap<Customer, GLCustomer>();
      	//c1 = new GLCustomer(gc, map);
        //c1.setMap(map);
      	//c1.setLocation(map.getAbsX(3), map.getAbsY(1));
      	gl_customers= new ArrayList<GLCustomer>();

        //create test employee
        e1 = new GLEmployee(gc, map);

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
          System.out.println("Panel inactive");
        }
        panel.setActive(true);
      }
    }


 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
    	
      map.render();

  		g.setColor(Color.black);

      if(active_panel!=null)
        active_panel.render(gc, g);

      e1.render(gc,g);
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
    } 


 
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {
            //pass time to restaurant
        restaurant.update(delta);
       
       // System.out.println(timer.getSeconds());
        e1.update(gc,game,delta);
        //c1.update(gc, game, delta);
        //Check for new logical customer and create gl customer
        for(int i=0; i<customers.size(); i++){

          //There is no map for this customer
          if(!cust_map.containsKey(customers.get(i))){
              
            //the logical customer
            Customer logic_cust = customers.get(i);
            GLCustomer glcust = new GLCustomer(gc,map);
            cust_map.put(customers.get(i), glcust);
            Path path;

            int wayX=20;
            int wayY=20;
            switch(logic_cust.getWaypoint()){
              case FOODLINE:
                boolean success = foodline.add(glcust);
                if(!success){
                    wayX=36;
                    wayY=3;

                    path = astar.findPath(null, 0, 27, wayX,wayY);
                    glcust.setPath(path);
                }
                break;
              case MENSROOM:
                wayX=28;
                wayY=3;

                path = astar.findPath(null, 0, 27, wayX,wayY);
                glcust.setPath(path);
                break;
              case WOMENSROOM:
                wayX=36;
                wayY=3;

                path = astar.findPath(null, 0, 27, wayX,wayY);
                glcust.setPath(path);
                break;
              case RANDOM:
                wayX= 0 + (int)(Math.random()*map.getWidthInTiles()-1); 
                wayY= 0 + (int)(Math.random()*map.getHeightInTiles()-1); 
                while(map.blocked(null, wayX, wayY) || (wayX==0 && wayY==27)){ //0 and 27 are the origin of the customer
                  wayX= 0 + (int)(Math.random()*map.getWidthInTiles()-1); 
                  wayY= 0 + (int)(Math.random()*map.getHeightInTiles()-1); 
                }


                //System.out.println("Random wayX: " + wayX);
                //System.out.println("Random wayY: " + wayY);

                path = astar.findPath(null, 0, 27, wayX,wayY);
                glcust.setPath(path);

                break;
              case REGISTER:
                break;
            }

            //create new glcust
           

            

              
            //add to cust map
           
           // gl_customers.add(glcust);
          }
          else{
              GLCustomer glcust =  cust_map.get(customers.get(i));
              // glcust.
              glcust.update(gc, game, delta);
          }
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

          //  System.out.println(destX);
            if(destX<map.getWidthInTiles() && destY<map.getHeightInTiles() && !map.blocked(null, destX, destY)){
              e1.setPath(destX, destY);
            }
            
          //}

          
        }
      //	if(!blocked(c1.getX(), c1.getY())){
	  		if(input.isKeyDown(Input.KEY_UP)){
	  			//c1.setY(c1.getY()-c1.getDY()*delta);
	  			//c1.setDirection(FigureDirection.UP);
          //c1.setMoving(true);
	  			//c1.move(FigureDirection.UP);
          //System.out.println(c1.getY()-c1.getDY());
	      	}
	      	if(input.isKeyDown(Input.KEY_RIGHT)){
	      		//c1.setX(c1.getX()+c1.getDX()*delta);
	      		//c1.setDirection(FigureDirection.RIGHT);
            //c1.setMoving(true);
          //  c1.move(FigureDirection.RIGHT);
	      	}
	  		if(input.isKeyDown(Input.KEY_DOWN)){
	  			//c1.setY(c1.getY()+c1.getDY()*delta);
	  			//c1.setDirection(FigureDirection.DOWN);
         // c1.setMoving(true);
        //  c1.move(FigureDirection.DOWN);
	      	}
	      	if(input.isKeyDown(Input.KEY_LEFT)){
	      	//	c1.setX(c1.getX()-c1.getDX()*delta);
	      		//c1.setDirection(FigureDirection.LEFT);
            //c1.setMoving(true);
          //  c1.move(FigureDirection.LEFT);
	      	}  
      //	}
           
      	if(input.isKeyDown(Input.KEY_Q)){
      		
      		//customers.add(new GLCustomer(gc,map));

      		//c1.setDirection(FigureDirection.LEFT);
      	}     
 
    }
 	

    /*
    public void walkPath(GLEntity entity, Path path){
    	for(int i=0; i<path.getLength(); i++){
    		int destX = path.getX(i);
    		int destY = path.getY(i);


    	}
    }*/



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