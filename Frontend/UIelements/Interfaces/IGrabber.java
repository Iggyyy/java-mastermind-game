package Frontend.UIelements.Interfaces;

import java.awt.Point;

import javax.swing.JFrame;

import Frontend.UIelements.Interfaces.IMarker.Markers;

public interface IGrabber{
    public void grabMarker(IMarker marker);
    public Markers getGrabbedMarkerType();
    public int getRow();
    public int getID();
    public Point getPosition();
    public Boolean checkIfContainsPoint(JFrame frame, Point p);
    public IMarker getMarker();
}
