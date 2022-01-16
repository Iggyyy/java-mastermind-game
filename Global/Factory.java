package Global;

import Frontend.*;
import Frontend.UIelements.*;
import Frontend.UIelements.Classes.Grabber;
import Frontend.UIelements.Classes.Marker;
import Frontend.UIelements.Classes.Spawner;
import Frontend.UIelements.Interfaces.IGrabber;
import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.ISpawner;
import Frontend.UIelements.Interfaces.IMarker.Markers;

import java.awt.Point;

public class Factory {

    public IMarker createMarker(Markers markerType)
    {
        return new Marker(markerType);
    }

    public ISpawner createSpawner(Controller ui, Factory fac, Markers markerType)
    {
        return new Spawner(ui, fac, markerType);
    }

    public IGrabber createGrabber(Point locationPoint, int row, int id)
    {
        return new Grabber(locationPoint, row, id);
    }

}
