package Frontend;

import Global.*;
import Backend.*;
import java.awt.*;

public class MainController {
 
    MainController(){
        Frame fm = new Frame();
        fm.setSize(400, 800);
        fm.setVisible(true);


        Label lb = new Label("Gelllo");
        fm.add(lb);

        Canvas c1 = new Canvas();
        c1.setBackground(Color.BLACK);
        c1.setSize(300, 100);
        c1.setBounds(50, 50, 300, 100);
        
        c1.setVisible(true);
        fm.add(c1);

        
    }
     
    public static void main(String[] args)
    {   
        //Initialize factory
        Factory classFactory = new Factory();

        //Create necessary class instances
        IGameLogic gameLogic = classFactory.getGameLogicClass();
        gameLogic.initializeGame();

        MainController g = new MainController();
        
    }
}
