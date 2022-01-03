package Frontend.Markers;

import Frontend.Markers.IMarker.Markers;

public interface IGrabber{
    public void grabMarker(IMarker marker);
    public Markers getGrabbedMarkerType();
    public int getRow();
    public int getID();
}
