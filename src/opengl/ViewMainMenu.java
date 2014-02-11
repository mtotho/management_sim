
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import mftoth.entities.*;

public class ViewMainMenu extends SimView{

	private GLButton btnNewGame; 

	public ViewMainMenu(OGLDisplay screen){
		super(screen);

		//Width: 60% of display, height: 20% of display, X: 20% from left, Ystart: 40% from top
		
		btnNewGame= new GLButton(Display.getWidth()*0.2, Display.getHeight()-Display.getHeight()*0.4, Display.getWidth()*0.6,Display.getHeight()*0.2, "New Game");
		//btnNewGame= new GLButton(100f,40f, 20f, 20f, "New Game");
		
	}

	public void render(){
		GL11.glColor3f(1f, 1f, 1f);
		RenderTool.renderText("Main Menu", 50,480);

		btnNewGame.draw();

		if(btnNewGame.clicked()){
			screen.setState(State.GAME);
		}
	}
}