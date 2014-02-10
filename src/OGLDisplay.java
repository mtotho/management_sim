
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class OGLDisplay{

	public OGLDisplay(){

		run();
	}

	public void run(){


		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.setTitle("Restuarant Simulator");
            Display.create();

            init();

	        while(!Display.isCloseRequested()){




	        	//Animation goes here
	            render();
	   			Display.update();
	        }//end while

	        close();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }



        Display.destroy();
	}

	public void init(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 512,0,512,1,-1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

	}

	public void render(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();

		GL11.glColor3f(1,0,0);

		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(100,100);
		GL11.glVertex2f(100+64,100);
		GL11.glVertex2f(100+64,100+64);
		GL11.glVertex2f(100,100+64);
		GL11.glEnd();

		GL11.glColor3f(1, 1,1);
		SimpleText.drawString("Restuarant Simulator", 50,480);
		SimpleText.drawString("Gordon Dragoon", 50,420);
		SimpleText.drawString("Patrick Welch", 50,400);
		SimpleText.drawString("Mike Toth", 50,380);
	}

	public void close(){
		Display.destroy();
		System.exit(0);
	}

}