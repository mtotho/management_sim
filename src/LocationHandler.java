package mftoth.restaurantsim.logic;

import java.util.LinkedHashMap;
import mftoth.restaurantsim.ogl.*;


public class LocationHandler{

	private LinkedHashMap<Locations, GLTile> tilemapper;
	private OGLMap map;

	public LocationHandler(OGLMap map){
		this.map=map;

		tilemapper=new LinkedHashMap<Locations, GLTile>();
		tilemapper.put(Locations.MENSROOM, new GLTile(28,3));
		tilemapper.put(Locations.WOMENSROOM, new GLTile(36,3));
		tilemapper.put(Locations.REGISTER, new GLTile(6,16));
		tilemapper.put(Locations.EXIT, new GLTile(0, 27));
	}

	public GLTile getTile(Locations location){
		GLTile tile;	
		tile = new GLTile(0,27);

		switch(location){
			
			case EXIT:
				tile = tilemapper.get(location);
			case ENTRANCE:
				int wayX, wayY;
				double mod = Math.random();
				if(mod>0.5){
					wayX=0;
					wayY=27;
				}else{
					wayX=0;
					wayY=28;
				}
				tile = new GLTile(wayX, wayY);
			break;

			case MENSROOM:
				tile = tilemapper.get(location);
			break;

			//WOMENSROOM: Set the entity path to the women's room
			case WOMENSROOM:
				tile = tilemapper.get(location);
			break;

			//RANDOM: Sets the entity path to a random location
			case RANDOM:
				tile = getRandom();
			break;

			case REGISTER:
				tile = tilemapper.get(location);
			break;
		}  

		return tile;
	}

	public Locations getLocation(int tilex, int tiley){
		//Not finished or needed
		GLTile tempTile = new GLTile(tilex, tiley);
		for( Locations location : tilemapper.keySet() ){
			
		}
		return Locations.REGISTER;
	}

	public GLTile getRandom(){
		
	    int wayX= 0 + (int)(Math.random()*map.getWidthInTiles()-1); 
        int wayY= 0 + (int)(Math.random()*map.getHeightInTiles()-1); 
        
        while(map.blocked(null, wayX, wayY) || (wayX==0 && wayY==27)){ //0 and 27 are the origin of the customer
          wayX= 0 + (int)(Math.random()*map.getWidthInTiles()-1); 
          wayY= 0 + (int)(Math.random()*map.getHeightInTiles()-1); 
        }
       
        return new GLTile(wayX, wayY);

       // newpath = astar.findPath(null, 0, 27, wayX,wayY);
        //setPath(newpath);



	//	this.destination = point;




        	/*
            Path newpath;
           // boolean set_success;
            int wayX=20;
            int wayY=20;
            */
           
	}


}