
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

  private GLPanel buttonPanel;
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

	//private Path customerPath;
	//private TiledMap map;
  private OGLMap map;
  private AStarPathFinder astar;

  private Restaurant restaurant;
	//private boolean[][] blocking;

  public StateGame(Restaurant restaurant){
    super();
    this.restaurant=restaurant;

    //
    customers = restaurant.getCustomers();
    timer = restaurant.getTimer();

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

        buttonPanel = new GLPanel(restaurant, gc, "Panel 1", true);
        buttonPanel.setLocation(640,0);
        buttonPanel.setDimension(320, 480);               
        buttonPanel.addButton(gc, "Add Customer", 240, 100);
        buttonPanel.addButton(gc, "Numbah 2", 240, 100);

        /*buttonMenu = 1;

        btn_spawnCust = new GLButton(gc, "Spawn", 240, 80);
        btn_spawnCust.setLabelX(60);
        btn_spawnCust.setX(640);
        btn_spawnCust.setY(40);

        btn_randCust = new GLButton(gc, "Employees", 240, 80);
        btn_randCust.setLabelX(60);
        btn_randCust.setX(640);
        btn_randCust.setY(160);

        btn_next1 = new GLButton(gc, "Next", 240, 80);
        btn_next1.setLabelX(60);
        btn_next1.setX(640);
        btn_next1.setY(280);

        btn_back2 = new GLButton(gc, "Back", 240, 80);
        btn_back2.setLabelX(60);
        btn_back2.setX(640);
        btn_back2.setY(280);*/

        astar = new AStarPathFinder(map, 400, false);
       

       // Path path = astar.findPath(null, 1, 1, 53,1);
        //c1.setPath(path);
       // c1.setPath(path);
      //  System.out.println("path length: " + path.getLength()); 

    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
    	
      map.render();

  		g.setColor(Color.white);

      buttonPanel.render(gc, g);

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

            int wayX=20;
            int wayY=20;
            switch(logic_cust.getWaypoint()){
              case FOODLINE:
                wayX=9;
                wayY=16;
                break;
              case MENSROOM:
                wayX=28;
                wayY=3;
                break;
              case WOMENSROOM:
                wayX=36;
                wayY=3;
                break;
              case RANDOM:

                break;
              case REGISTER:
                break;
            }

            //create new glcust
            GLCustomer glcust = new GLCustomer(gc,map);  

            

            Path path = astar.findPath(null, 0, 27, wayX,wayY);
            glcust.setPath(path);
              
            //add to cust map
            cust_map.put(customers.get(i), glcust);

           // gl_customers.add(glcust);
          }
          else{
              GLCustomer glcust =  cust_map.get(customers.get(i));
              // glcust.
              glcust.update(gc, game, delta);
          }
        }

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

  public void mousePressed(int button, int posx, int posy){
    ArrayList<GLButton> buttons = buttonPanel.getButtons();
    if(buttons.get(0).isPressed()){
      restaurant.addCustomer();
    }
    if(buttons.get(1).isPressed()){
      GLCustomer glcust =  cust_map.get(customers.get(0));
      glcust.setPath(cleaning.get(0).getX(), cleaning.get(0).getY());
    }
  }

}