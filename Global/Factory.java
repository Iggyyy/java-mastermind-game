package Global;

import java.awt.Color;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Dictionary;

import Backend.*;
import Frontend.*;
import Frontend.Markers.*;
import Frontend.Markers.IMarker.Markers;

//Dependency Injection
public class Factory {

    public IMarker createMarker(Markers markerType)
    {
        if (markerType == Markers.BlueMarker)
            return new Marker(Color.blue);
        if (markerType == Markers.GreenMarker)
            return new Marker(Color.blue);
        if (markerType == Markers.RedMarker)
            return new Marker(Color.red);
        if (markerType == Markers.PinkMarker)
            return new Marker(Color.pink);
        if (markerType == Markers.OrangeMarker)
            return new Marker(Color.orange);

        return null;
    }

    public IGameLogic getGameLogic()
    {
        return new GameLogic();
    }

    public ISpawner createSpawner(UIController ui, Factory fac, Markers markerType)
    {
        return new Spawner(ui, fac, markerType);
    }

}
