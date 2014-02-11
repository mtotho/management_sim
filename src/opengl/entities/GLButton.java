package mftoth.entities;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GLButton extends GLWidget{

	protected String label;

	public GLButton(double x, double y, double width, double height, String label){
		super(x,y,width,height);

		this.label = label;
	}

	public void draw(){

		//GL11.glBegin();
		//GL11.glColor3f(0f, 0f, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f((int)x,(int)y);
			GL11.glVertex2f((int)(x+width),(int)y);
			GL11.glVertex2f((int)(x+width),(int)(y+height));
			GL11.glVertex2f((int)x,(int)(y+height));
		GL11.glEnd();

       // GL11.glRectf(x, y, this.getWidth(), height);
       // GL11.glEnd();
	}

	public boolean clicked(){
        if(Mouse.isButtonDown(0)){

			//System.out.println("btn Height: " + height + " ypos: " + y);
        	if(this.inBounds(Mouse.getX() ,Mouse.getY())){
        		return true;
        	}else{
        		return false;
        	}
        }else{
        	return false;
        }
	}
}