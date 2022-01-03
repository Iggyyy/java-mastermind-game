package Frontend.Markers;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Point;

import Frontend.Markers.IMarker.Markers;

public class Grabber extends JPanel implements IGrabber {

    private IMarker storedMarker;
    private int row;
    private int id;

    public Grabber(Point p, int _row, int _id) {
        this.setLocation(p);
        this.setSize(50, 50);
        this.setBackground(Color.GRAY);
        this.row = _row;
        this.id = _id;
    }

    @Override
    public void grabMarker(IMarker marker) {
        storedMarker = marker;
    }

    @Override
    public Markers getGrabbedMarkerType() {
        return storedMarker.getMarkerType();
    }

    public int getRow()
    {
        return row;
    }
    
    public int getID()
    {
        return id;
    }
}
