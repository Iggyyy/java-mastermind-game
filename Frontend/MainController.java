package Frontend;

import Global.*;
import Backend.*;


public class MainController {

   
    

    public static void main(String[] args)
    {   
        //Initialize factory
        Factory classFactory = new Factory();

        //Create necessary class instances
        IGameLogic gameLogic = classFactory.getGameLogic();
        gameLogic.initializeGame();

        
        UIController uiController = new UIController(gameLogic, classFactory);

    }
}
