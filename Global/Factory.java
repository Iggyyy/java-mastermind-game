package Global;

import java.lang.ProcessBuilder.Redirect.Type;

import Backend.*;
import Frontend.*;

//Dependency Injection
public class Factory {

    public IGameLogic getGameLogicClass()
    {
        return new GameLogic();
    }

}
