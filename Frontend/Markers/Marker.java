package Frontend.Markers;

import java.awt.Button;

public class Marker implements IMarker{
    
    private Button selfButton;

    public Button getSelfButton() {
        return selfButton;
    }

    public void setSelfButton(Button selfButton) {
        this.selfButton = selfButton;
    }


    public Button pickUp() {
        return null;
    }

    public void setPosition(int x, int y) {
        selfButton.setLocation(x, y);
    }
    
}
