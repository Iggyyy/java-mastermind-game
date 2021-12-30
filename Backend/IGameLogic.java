package Backend;

import java.lang.reflect.Array;

public interface IGameLogic {
    
    public void resetGame();
    public void initializeGame();
    public void submitTurn(Array newRow);
    public Array getBoardBodyArray();
    public Array getBoardResultArray();
    public Array getBoardSchemeArray();

}
