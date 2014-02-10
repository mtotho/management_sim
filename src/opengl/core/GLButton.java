package mftoth;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class GLButton extends GLWidget{

	public GLButton(int width, int height, int xpos, int ypos, String label){
		super(width, height, xpos, ypos);


	}

	public void render(){

		//GL11.glBegin();
		//GL11.glColor3f(0f, 0f, 1.0f);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(this.getX(),this.getY());
			GL11.glVertex2f(this.getX()+this.getWidth(),this.getY());
			GL11.glVertex2f(this.getX()+this.getWidth(),this.getY()+this.getHeight());
			GL11.glVertex2f(this.getX(),this.getY()+this.getHeight());
		GL11.glEnd();

       // GL11.glRectf(this.getX(), this.getY(), this.getWidth(), this.getHeight());
       // GL11.glEnd();
	}

	public boolean clicked(){
        if(Mouse.isButtonDown(0)){

			//System.out.println("btn Height: " + this.getHeight() + " ypos: " + this.getY());
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