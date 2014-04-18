package mftoth.restaurantsim.ogl;

import org.newdawn.slick.*;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class OGLMap implements TileBasedMap{
	private static final int WIDTH = 40;
  	private static final int HEIGHT = 30;

  	private TiledMap map;
  	private String blocking_property="blocking";
    private String cleaning_property="cleaning";
    private boolean[][] arrBlocking = new boolean[WIDTH][HEIGHT];

    private ArrayList<GLTile> blocking = new ArrayList<GLTile>();
    private ArrayList<GLTile> cleaning = new ArrayList<GLTile>();

    private GLTile tempTile;

 	public OGLMap() throws SlickException{
		map = new TiledMap("res/maps/restaurant_v2.tmx");
  	 	/*blocking = new boolean[map.getWidth()][map.getHeight()];
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
       // System.out.println(map.getTileProperty(map.getTileId(11, 1, map.getLayerIndex("collision")), blocking_property, "false").equals("true"));

        for(int x=0;x<WIDTH;x++){
            for(int y=0;y<HEIGHT;y++){
                arrBlocking[x][y]=false;
                if(map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("collision")), blocking_property, "false").equals("true")){
                    blocking.add(new GLTile(x, y));
                     arrBlocking[x][y]=true;
                }
                if(map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("decorative_collision")), blocking_property, "false").equals("true")){
                    blocking.add(new GLTile(x, y));
                     arrBlocking[x][y]=true;
                }
                if(map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("wall_collision")), blocking_property, "false").equals("true")){
                    blocking.add(new GLTile(x, y));
                     arrBlocking[x][y]=true;
                }
            }
        }

        for(int x=0;x<WIDTH;x++){
            for(int y=0;y<HEIGHT;y++){
                if(map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("cleaning")), cleaning_property, "false").equals("true")){
                    cleaning.add(new GLTile(x, y));
                }
            }
        }
 	}

    public ArrayList<GLTile> getBlockedTiles(){

        return blocking;
    }

    public ArrayList<GLTile> getCleaningTiles(){

        return cleaning;
    }

    @Override
    public boolean blocked(PathFindingContext ctx, int x, int y) {



     //   boolean collision = arrBlocking[x][y];
        boolean collision=map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("collision")), blocking_property, "false").equals("true");
        boolean dec_collision = map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("decorative_collision")), blocking_property, "false").equals("true");
        boolean wall_collision = map.getTileProperty(map.getTileId(x, y, map.getLayerIndex("wall_collision")), blocking_property, "false").equals("true");

    	return (collision || dec_collision || wall_collision);
    }

    @Override
    public float getCost(PathFindingContext ctx, int x, int y) {
        return 1.0f;
    }

    @Override
    public int getHeightInTiles() {
        return HEIGHT;
    }

    @Override
    public int getWidthInTiles() {
        return WIDTH;
    }

    public int getTileHeight(){
    	return map.getTileHeight();
    }

    public int getTileWidth(){
    	return map.getTileWidth();
    }

    @Override
    public void pathFinderVisited(int x, int y) {}

    public int getTileX(int absx){
    	int width = map.getTileWidth();
        if((int)(absx/width)>(WIDTH-1)){
            return (WIDTH-1);
        }
        else{
            return (int)(absx/width);
        }
    }
    public int getTileY(int absy){
    	int height = map.getTileHeight();
    	if((int)(absy/height)>(HEIGHT-1)){
            return (HEIGHT-1);
        }
        else{
            return (int)(absy/height);
        }
    }
    public int getAbsX(int tileX){
    	int width = map.getTileWidth();
    	return tileX*width;
    }
    public int getAbsY(int tileY){
    	int height = map.getTileHeight();
    	return tileY*height;
    }


    public void render(int layer){
		map.render(0,0, layer);

    }

}