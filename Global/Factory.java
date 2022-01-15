package Global;

import Backend.*;
import Frontend.*;
import Frontend.Markers.*;
import Frontend.Markers.IMarker.Markers;
import java.awt.Point;

public class Factory {

    public IMarker createMarker(Markers markerType)
    {
        return new Marker(markerType);
    }

    public IGameLogic getGameLogic()
    {
        return new GameLogic();
    }

    public ISpawner createSpawner(UIController ui, Factory fac, Markers markerType)
    {
        return new Spawner(ui, fac, markerType);
    }

    public IGrabber createGrabber(Point locationPoint, int row, int id)
    {
        return new Grabber(locationPoint, row, id);
    }

}
