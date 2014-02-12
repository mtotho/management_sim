import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.*;
import mftoth.states.*;

public class OGLGameContainer extends StateBasedGame{

	public OGLGameContainer(String title){
		super(title);

	}

	 @Override
    public void initStatesList(GameContainer container) throws SlickException {

    	//Initialize State List
 		addState(new StateIntro());
 	 	addState(new StateMainMenu());
 	 	addState(new StateGame());
 
    }
 
}