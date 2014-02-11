import org.newdawn.slick.*;

public class OGLGameContainer extends BasicGame{
	
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