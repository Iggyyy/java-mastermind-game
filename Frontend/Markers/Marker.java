package Frontend.Markers;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

public class Marker implements IMarker{

    private JPanel selfPanel;
    private Color selfColor;

    public Marker(Color color) {
        selfPanel = new JPanel();
        selfPanel.setBackground(color);
        selfPanel.setBounds(0, 0, 50, 50);
        
        selfColor = color;
    }

    private JPanel getSelfJPanel() {
        return selfPanel;
    }


    public JPanel getJPanel() {
        return getSelfJPanel();
    }

    public void setPosition(int x, int y) {
        selfPanel.setLocation(x, y);
    }
    
}
