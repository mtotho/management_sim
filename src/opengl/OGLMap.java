package mftoth.map;

import org.newdawn.slick.*;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.tiled.TiledMap;

public class OGLMap implements TileBasedMap{
	private static final int WIDTH = 54;
  	private static final int HEIGHT = 34;
  	private TiledMap map;
  	private String blocking_property="blocking";

  	private boolean blocking[][];

 	public OGLMap() throws SlickException{
		map = new TiledMap("res/maps/tile_test.tmx");
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

 	}

    @Override
    public boolean blocked(PathFindingContext ctx, int x, int y) {
    	return map.getTileProperty(map.getTileId(x, y, 0), blocking_property, "false").equals("true");
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

    @Override
    public void pathFinderVisited(int x, int y) {}

    public int getTileX(int absx){
    	int width = map.getTileWidth();
    	return (int)(absx/width);
    }
    public int getTileY(int absy){
    	int height = map.getTileHeight();
    	return (int)(absy/height);
    }
    public int getAbsX(int tileX){
    	int width = map.getTileWidth();
    	return tileX*width;
    }
    public int getAbsY(int tileY){
    	int height = map.getTileHeight();
    	return tileY*height;
    }


    public void render(){
		map.render(0,0);
    }

}