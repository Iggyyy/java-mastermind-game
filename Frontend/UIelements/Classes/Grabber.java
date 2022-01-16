package Frontend.UIelements.Classes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Frontend.UIelements.Interfaces.IGrabber;
import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.IMarker.Markers;
import Global.Logger;

import java.awt.Color;
import java.awt.Point;

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
        if (storedMarker != null)
        {
            Logger.log("Grabber already has marker assigned!");
            return;
        }
        storedMarker = marker;
        storedMarker.setPosition(this.getPosition().x, this.getPosition().y);
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

    @Override
    public Point getPosition() 
    {
        return this.getLocation();
    }

    @Override
    public Boolean checkIfContainsPoint(JFrame frame, Point p) 
    { 
        Point pp = SwingUtilities.convertPoint(frame, p, this);
        return this.contains(pp);
    }

    @Override
    public IMarker getMarker() {
        return storedMarker;
    }


}
