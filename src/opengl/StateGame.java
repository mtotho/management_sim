
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

  private GLButton btn_spawnCust;
  private Font font;
  private TrueTypeFont ttf;

	private GLCustomer c1;
  private HashMap<Customer,GLCustomer> cust_map;
	private ArrayList<GLCustomer> gl_customers;
  private ArrayList<Customer> customers;

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
  }

    @Override 
  public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {

       	this.game=game;
        map = new OGLMap();

        cust_map = new HashMap<Customer, GLCustomer>();
      	//c1 = new GLCustomer(gc, map);
        //c1.setMap(map);
      	//c1.setLocation(map.getAbsX(3), map.getAbsY(1));
      	gl_customers=new ArrayList<GLCustomer>();

        btn_spawnCust =new GLButton(gc, "Spawn", 240, 80);
        btn_spawnCust.setLabelX(60);
        btn_spawnCust.setX(1000);
        btn_spawnCust.setY(40);

        astar =new AStarPathFinder(map, 400, false);
       
       // c1.setPath(path);
      //  System.out.println("path length: " + path.getLength()); 

    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
    	
      map.render();

  		g.setColor(Color.white);

      btn_spawnCust.render(gc, g);
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
        

        //Check for new logical customer and create gl customer
        for(int i=0; i<customers.size(); i++){

          //There is no map for this customer
          if(!cust_map.containsKey(customers.get(i))){
            
            //create new glcust
            GLCustomer glcust = new GLCustomer(gc,map);  
            Path path = astar.findPath(null, 1, 1, 53,1);
            glcust.setPath(path);
              
            //add to cust map
            cust_map.put(customers.get(i), glcust);

           // gl_customers.add(glcust);
          }else{
              GLCustomer glcust =  cust_map.get(customers.get(i));
              // glcust.
              glcust.update(gc, game, delta);
          }
        }

      	Input input = gc.getInput();
      	
        int mousex = input.getMouseX();
        int mousey = input.getMouseY();

        //System.out.println("Map coord: x- " + map.getTileX(mousex) + " | y- " +map.getTileY(mousey));

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
    if(btn_spawnCust.isPressed()){
      restaurant.addCustomer();
    }

  }

}