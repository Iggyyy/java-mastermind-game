package Frontend.Markers;

import Frontend.Markers.IMarker.Markers;
import java.awt.Point;

import javax.swing.JFrame;

public interface IGrabber{
    public void grabMarker(IMarker marker);
    public Markers getGrabbedMarkerType();
    public int getRow();
    public int getID();
    public Point getPosition();
    public Boolean checkIfContainsPoint(JFrame frame, Point p);
    public IMarker getMarker();
}
