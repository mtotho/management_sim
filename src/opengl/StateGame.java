
package mftoth.states;

import mftoth.entities.*;
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
import mftoth.map.*;


public class StateGame extends BasicGameState{

	
	private int ID = 3;
	private StateBasedGame game;

	private GLCustomer c1;
	private ArrayList<GLCustomer> customers;
	//private Path customerPath;
	//private TiledMap map;
  private OGLMap map;
  private AStarPathFinder astar;
	//private boolean[][] blocking;

    @Override 
    public void init(GameContainer gc, StateBasedGame game)
            throws SlickException {

       	this.game=game;
        map = new OGLMap();

        //AStarPathFinder pathFinder = new AStarPathFinder(map, 100, false);
        //Path path = pathFinder.findPath(null, 0, 0, 50, 30);

      /* 	map = new TiledMap("res/maps/tile_test.tmx");
       	blocking = new boolean[map.getWidth()][map.getHeight()];
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                int tileID = map.getTileId(x, y, 0);

                String value = map.getTileProperty(tileID, "blocking", "false");
                if ("true".equals(value)) {
                	System.out.println(x + "," + y);
                    blocking[x][y] = true;
                }
            }
        }*/

        
      	c1 = new GLCustomer(gc, map);
        c1.setMap(map);
      	c1.setLocation(map.getAbsX(3), map.getAbsY(1));
      	customers=new ArrayList<GLCustomer>();

        astar =new AStarPathFinder(map, 200, false);
        Path path = astar.findPath(null, 1, 1, 53,1);
        c1.setPath(path);
      //  System.out.println("path length: " + path.getLength()); 

    }
 
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g)
            throws SlickException {
      	map.render();


  		g.setColor(Color.white);
	   // g.drawString("Game State", 50, 10);

	   	c1.setLocation(c1.getX(),c1.getY());
	    c1.render(gc, g);

    	for(int i=0; i<customers.size(); i++){
    		GLCustomer customer = (GLCustomer)customers.get(i);
    		customer.render(gc, g);	
    	}


    } 
 
    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta)
            throws SlickException {

        c1.update(gc,game,delta);
        //for(int i=0; i<customerPath.getLength(); i++){
     
        	//c1.walkPath(delta);
       
       // } 
        

      	Input input = gc.getInput();
      	
        int mousex = input.getMouseX();
        int mousey = input.getMouseY();

        //System.out.println("Map coord: x- " + map.getTileX(mousex) + " | y- " +map.getTileY(mousey));

      //	if(!blocked(c1.getX(), c1.getY())){
	  		if(input.isKeyDown(Input.KEY_UP)){
	  			//c1.setY(c1.getY()-c1.getDY()*delta);
	  			//c1.setDirection(FigureDirection.UP);
          //c1.setMoving(true);
	  			c1.move(FigureDirection.UP);
          //System.out.println(c1.getY()-c1.getDY());
	      	}
	      	if(input.isKeyDown(Input.KEY_RIGHT)){
	      		//c1.setX(c1.getX()+c1.getDX()*delta);
	      		//c1.setDirection(FigureDirection.RIGHT);
            //c1.setMoving(true);
            c1.move(FigureDirection.RIGHT);
	      	}
	  		if(input.isKeyDown(Input.KEY_DOWN)){
	  			//c1.setY(c1.getY()+c1.getDY()*delta);
	  			//c1.setDirection(FigureDirection.DOWN);
         // c1.setMoving(true);
          c1.move(FigureDirection.DOWN);
	      	}
	      	if(input.isKeyDown(Input.KEY_LEFT)){
	      	//	c1.setX(c1.getX()-c1.getDX()*delta);
	      		//c1.setDirection(FigureDirection.LEFT);
            //c1.setMoving(true);
            c1.move(FigureDirection.LEFT);
	      	}  
      //	}
           
      	if(input.isKeyDown(Input.KEY_Q)){
      		
      		customers.add(new GLCustomer(gc,map));

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