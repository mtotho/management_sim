package mftoth.entities;

public abstract class GLWidget extends AbstractMoveableEntity{

	public GLWidget(double x, double y, double width, double height){
		super(x,y,width,height);
	}	

	public boolean inBounds(double x, double y){
		if((x>=this.x && x<=this.x+this.width) && (y>=this.y && y<=this.y+this.height)){
			return true;
		}else{
			return false;
		}
	}

}