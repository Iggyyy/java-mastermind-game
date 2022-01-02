package Frontend.Markers;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;

public class Marker extends JPanel implements IMarker{

    private JPanel selfPanel;
    private Markers selfMarker;

    public Marker(Markers markerColor) {
        super();
        setColor(markerColor);
        this.setBounds(0, 0, 50, 50);
        selfMarker = markerColor;
    }

    private JPanel getSelfJPanel() {
        return this;
    }

    public JPanel getJPanel() {
        return getSelfJPanel();
    }

    public void setPosition(int x, int y) {
        this.setLocation(x, y);
    }

    private void setColor(Markers m)
    {
        if (m == Markers.BlueMarker)
            this.setBackground(Color.blue);
        if (m == Markers.GreenMarker)
            this.setBackground(Color.green);
        if (m == Markers.RedMarker)
            this.setBackground(Color.red);
        if (m == Markers.PinkMarker)
            this.setBackground(Color.pink);
        if (m == Markers.OrangeMarker)
            this.setBackground(Color.orange);
    }
    
}
