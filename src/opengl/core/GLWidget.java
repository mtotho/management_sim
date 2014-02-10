package mftoth;

public abstract class GLWidget{

	private int width;
	private int height;
	private int xpos;
	private int ypos;

	public GLWidget(int width, int height, int xpos, int ypos){
		this.width=width;
		this.height=height;
		this.xpos=xpos;
		this.ypos=ypos;
	}	

	public float getWidth(){
		return width;
	}

	public float getHeight(){
		return height;
	}

	public float getX(){
		return xpos;
	}

	public float getY(){
		return ypos;
	}

	public boolean inBounds(int x, int y){
		if((x>=xpos && x<=xpos+width) && (y>=ypos && y<=ypos+height)){
			return true;
		}else{
			return false;
		}
	}

}