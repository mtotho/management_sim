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
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import java.util.Set;
import mftoth.restaurantsim.logic.*;
import org.newdawn.slick.UnicodeFont;
import org.lwjgl.input.Mouse;
import pjwelch.restaurantsim.database.*;

public class GLScrollablePanel<T> extends GLEntity{

	private TrueTypeFont ttfont;
	private Font font;
	private int fontSize = 15;
	private Restaurant restaurant;
	private ArrayList<T> items;
	private HashMap<T, String>  item_mapper;
	private int itemheight;
	private int itemindex; //index of the first item;
	private int content_height;
	private int padding;
	private int item_hovered;
	private int item_selected;
	private boolean hasFocus;

	private boolean scrollbar;
	private int scroll_bar_width;
	private float scroll_bar_height;
	private float scroll_bar_x;
	private float scroll_bar_y;
	private boolean scroll_bar_pressed;
	private float scroll_click_gap;

	public GLScrollablePanel(Restaurant restaurant, GameContainer gc, int x, int y, int width, int height){
		super(gc,x,y,width,height, false);
		this.restaurant=restaurant;
		

		padding=5;

		itemheight=24; //Height of each item in the panel
		itemindex=0;   //index of item to render first
		content_height=0; //height of the sum on the items
		items = new ArrayList<T>(); 
		item_mapper = new HashMap<T, String>();
		
		item_hovered=-	1;
		item_selected=-1;

 	 	font = new Font("Verdana", Font.BOLD, fontSize);
		ttfont = new TrueTypeFont(font, false);

		scroll_bar_width=25;
		scroll_bar_pressed=false;
		scrollbar=false;
		scroll_bar_y=y;

		hasFocus=false;
	}
	
	//add and item to the panel and increment the content_height accordingly
	public void add(String label, T item){
		item_mapper.put(item, label);
		items.add(item);
		content_height+=itemheight;
	}

	public void remove(int i){
		if(i<items.size())
			items.remove(i);
	}

	public void remove(T item){
		if(item_mapper.containsKey(item)){

			//deselect item if it is removed
			if(items.indexOf(item)==item_selected){
				item_selected=-1;
			}

			items.remove(item);
			item_mapper.remove(item);

			//reduce the content height accordingly
			content_height-=itemheight;

		}

	}

	public boolean contains(T item){
		if(item_mapper.containsKey(item)){
			return true;
		}else{
			return false;
		}
	}

	public int size(){
		return items.size();
	}

	public void clear(){
		items.clear();
		content_height=0;
		itemindex=0;
	}

	public void updateLabel(T item, String label){
		if(item_mapper.containsKey(item)){
			item_mapper.put(item, label);
		}
	}

	public void render(GUIContext gc, Graphics g){

		if(content_height>height){
			scrollbar=true;
		}else{
			scrollbar=false;
		}

		//int content_height;
		g.setColor(Color.black);
		g.drawRect(x,y,width,height);
		g.setColor(Color.white);
		g.fillRect(x+1,y+1,width-2,height-2);


		g.setFont(ttfont);
		
		double initx=x+padding;
		double inity=y+padding;

		int location = 0; //Location to keep track of which viewable slot we are in

		//Make it so we dont scroll past the last items
		int items_on_screen=(int)(height/itemheight);
		if(itemindex>(items.size()-items_on_screen) && scrollbar){
		
			itemindex=items.size()-items_on_screen;
		}

		//System.out.println("itemindex: " + itemindex);
		//loop through each item, starting at the current itemindex
		for(int i=itemindex; i<items.size(); i++){

			if(location<(int)((height-2*padding)/itemheight)){

				T item = items.get(i);
				String content = item_mapper.get(item);

				double rely = inity + location*itemheight;


				//Change the color to yellow when hovered over an item
				if(item_hovered!=-1 && location==item_hovered && item_selected!=i && !scroll_bar_pressed){
					g.setColor(Color.yellow);
				
				//Change color to blue when item selected
				}else if(item_selected==i){
					g.setColor(Color.blue);

				//Change color to white normally
				}else{
					g.setColor(Color.white);
				}

				if(scrollbar){
					g.fillRect((float)(x+padding),(float)rely,width-1*padding-scroll_bar_width-2,itemheight);
				}else{
					g.fillRect((float)(x+padding),(float)rely,width-2*padding,itemheight);
				}
				
		

				//Change text color to white when item selected so we can see over blue back
				if(item_selected==i){
					g.setColor(Color.white);
				}else{
					g.setColor(Color.black);
				}
				g.drawString(content,3+(int)(initx), (int)(rely));


				location++;	
			}
		}


		//Render Scroll bar if content over flows
		if(scrollbar){

			scroll_bar_x = (x+width)-scroll_bar_width;
			//scroll_bar_y = y + (itemindex*(float)itemheight/content_height)*(float)(height);

			//Set the height of the scroll bar to be some factor of the content_height
			scroll_bar_height=(float)(height * ((float)height/content_height))+(float)padding/2;
			
			//The left border of the backgroudn
			g.setColor(new Color(133,133,133));
			g.fillRect(scroll_bar_x-1, y+1, 1, height-1);


			//scroll bar back
			g.setColor(new Color(220,220,220));
			g.fillRect(scroll_bar_x, y+1, scroll_bar_width, height-2);

			//scroll bar
			//g.setColor(Color.black);
			g.setColor(new Color(166,166,166));	
			g.fillRect(scroll_bar_x,(float)scroll_bar_y+1,scroll_bar_width,scroll_bar_height);




		}

 	 }//end: render


 	public boolean itemSelected(){
 		if(item_selected==-1){
 			
 			return false;
 		}else{
 			
 			return true;
 		}
 	}

  	public int getSelectedIndex(){
  		return item_selected;
  	}

  	public T getSelected(){
  		if(item_selected!=-1 && item_selected<=items.size()){
  			return items.get(item_selected);
  		}else{
  			return null;
  		}
  	}

  	public String getSelectedString(){
  		if(item_selected!=-1 && item_selected<=items.size()){
  			T item = items.get(item_selected);

  			return item_mapper.get(item);
  		}else{
  			return null;
  		}
  	}


 	public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {
       
        //Capture mouse wheel move
        scroll();
  
    	//Input input = gc.getInput();
      	
       // int mousex = input.getMouseX();
        //int mousey = input.getMouseY();
 	
    }

    public void scroll() {

    	//Check mouse wheel movement if there is content overflow
		if(content_height>height){
		    int dWheel = Mouse.getDWheel();
		    if (dWheel < 0) {
		        //scroll down
		    	if(itemindex>0) //decrement itemindex
		    		itemindex--;

		    	 //Set the y position of the scoll bar to match where we scrolled to
		    	 scroll_bar_y = y + (itemindex*(float)itemheight/content_height)*(float)(height);
		    } else if (dWheel > 0){
		        //up
		        int max_scroll = (int)((content_height-height-2*padding)/itemheight);
		        if(itemindex<max_scroll){
		        	itemindex++;
		        }

		         scroll_bar_y = y + (itemindex*(float)itemheight/content_height)*(float)(height);
		    }



		}
	 
	}

	private void increment(){
		if(itemindex<items.size()-1){
        	itemindex++;
        }
	}

	private void decrement(){
		if(itemindex>0) //decrement itemindex
    		itemindex--;
	}


    public void keyReleased(int key, char c) {

	    switch(key) {
		    case Input.KEY_ESCAPE:
		        item_selected=-1;
		        break;
		    case Input.KEY_2:
		        // TODO: Implement later
		        break;
		    case Input.KEY_3:
		        // TODO: Implement later
		        break;
	        case Input.KEY_RIGHT:
		        // TODO: Implement later
		        break;
		    case Input.KEY_LEFT:
		        // TODO: Implement later
		        break;
	        case Input.KEY_DOWN:
		        if(hasFocus){
		        	if(item_selected==-1){
		        		item_selected=0;
		        	}else{
		        		if(item_selected<(items.size()-1)){
		        			item_selected++;
		        		}
		        		
		        	}

		        	
		        }
		        break;

	        case Input.KEY_UP:
	        	if(hasFocus){
	        		if(item_selected==-1){
		        		item_selected=0;
		        	}else{
		        		if(item_selected>0){
		        			item_selected--;
		        		}
		        		
		        	}
	        	}
		        
		        break;
		    default:
		        break;
	    }
	}


  	public void mouseReleased(int button, int x, int y){
  		
  		scroll_bar_pressed=false;
		scroll_click_gap = 0;

		if(inBounds(x,y)){
			hasFocus=true;
		}else{
			hasFocus=false;
		}
  		//mouseDown=false; //change flag back to false after click is done
  	} 

  	public boolean hasFocus(){
  		return hasFocus;
  	}


  	public void mousePressed(int button,
                  int x,
                  int y){


  		if(inBounds(x,y)){

	  		if(content_height>height){
	  			//System.out.println("contentheight: " + content_height);
	  		//Flag if we are pressing the scroll bar
		  		if(x >= scroll_bar_x && x<=scroll_bar_x+scroll_bar_width && y>=scroll_bar_y && y<=scroll_bar_y+scroll_bar_height){
		  			scroll_bar_pressed=true;
		  		//	scroll_click_gap=y-scroll_bar_y;
		  		}else{
		  			scroll_bar_pressed=false;
		  		//	scroll_click_gap = 0;

			  		
		  		}
	  		}

	  		if(item_hovered!=-1){

	  			item_selected = item_hovered+itemindex;

	  			if(item_selected>=items.size())
	  				item_selected=-1;
	  		}
	  	}


  	}

  	public void mouseDragged (int oldx,int oldy,
                  int newx,
                  int newy){


  		//move the scroll bar and itemindex if we drag the scroll bar
  		if(scroll_bar_pressed){

  			//get direction of the drag
  			int delta = newy-oldy;

  			//ensure that dragging the scroll bar keeps inside the bounds of the panel
  			if(scroll_bar_y+delta>y && scroll_bar_y+delta<(y+height+(float)padding/2-scroll_bar_height)){
  				scroll_bar_y+=delta;

  				//Try to determine how far into the items we are 
  				float percent = (scroll_bar_y-y)/(height-scroll_bar_height);


  				//This is off but it works well enough
  				itemindex=(int)(percent*items.size());

  				//System.out.println(itemindex);
  			}

  		}


  	}

  	public void mouseMoved(int oldx, int oldy, int newx, int newy){

  		//Get the currently hovered item
  		if(inBounds(newx, newy)){

  			int rely = newy-y;

  			item_hovered = (int)(rely / itemheight);



  		}else{
  			item_hovered=-1;
  		}
  	}
  

}
