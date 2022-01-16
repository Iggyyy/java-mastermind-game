package Frontend.UIelements.Classes;

import javax.swing.JPanel;

import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.IMarker.Markers;

import java.awt.Color;

public class Marker extends JPanel implements IMarker{

    private Markers selfMarker;

    public Marker(Markers markerColor) {
        super();
        setColor(markerColor);
        this.setBounds(9000, 9000, 50, 50);
        selfMarker = markerColor;
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
            this.setBackground(Color.magenta);
        if (m == Markers.YellowMarker)
            this.setBackground(Color.yellow);
        if (m == Markers.BlackMarker)
            this.setBackground(Color.black);
        if (m == Markers.WhiteMarker)
            this.setBackground(Color.white);
    }

    @Override
    public Markers getMarkerType() {
        return selfMarker;
    }

    @Override
    public void changeSize(int newSize) {
        this.setSize(newSize, newSize);
    }
    
}
