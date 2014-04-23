package mftoth.restaurantsim.ogl;

public class GLTile{
	private int x;
	private int y;
	private boolean isBlocked;

	public GLTile(int x, int y){
		isBlocked=false;
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setBlocked(boolean flag){
		isBlocked=flag;
	}

	public boolean isBlocked(){
		return isBlocked;
	}

	public String toString(){
		return "GLTile - X: " + x + " | Y: " + y;
	}
}