import org.newdawn.slick.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.*;

import java.util.*;

enum State{
	INTRO,MAIN_MENU, GAME
}

public class OGLGameContainer extends BasicGame{
	
	private State state;
	private Map<String, SimView> views;

	private int display_width;
	private int display_height;

	private long last_frame;

	public OGLGameContainer(String title){
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.drawString("Hello World", 100, 100);
	}
 
}