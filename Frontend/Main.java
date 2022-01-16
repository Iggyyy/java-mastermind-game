package Frontend;

import Global.*;

public class Main {

    public static void main(String[] args)
    {   
        //Initialize factory
        Factory classFactory = new Factory();
        
        //Create UI and run game
        Controller uiController = new Controller(classFactory);
    }
}
