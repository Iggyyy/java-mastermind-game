package Frontend.Markers;

import javax.swing.JButton;
import javax.swing.JPanel;

public interface IMarker {
    public JPanel getJPanel();
    public void setPosition(int x, int y);
        
    public enum Markers{
        RedMarker,
        GreenMarker,
        BlueMarker,
        PinkMarker,
        OrangeMarker
    }
    
}
