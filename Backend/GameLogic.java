package Backend;

import java.lang.reflect.Array;
import Global.Logger;

public class GameLogic implements IGameLogic{
    
    private Array[][] boardBody = new Array[6][4]; 

    public GameLogic() {
        super();
    }

    public Array[][] getBoardBody() {
        return boardBody;
    }

    public void setBoardBody(Array[][] boardBody) {
        this.boardBody = boardBody;
    }

    public void resetGame() {
        // TODO Auto-generated method stub
        
    }

    public void initializeGame() {
        Logger.log("Game initialization");
    }

    public void submitTurn(Array newRow) {
        // TODO Auto-generated method stub
        
    }

    public Array getBoardBodyArray() {
        // TODO Auto-generated method stub
        return null;
    }

    public Array getBoardResultArray() {
        // TODO Auto-generated method stub
        return null;
    }

    public Array getBoardSchemeArray() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
