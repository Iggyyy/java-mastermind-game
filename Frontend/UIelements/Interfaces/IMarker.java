package Frontend.UIelements.Interfaces;

public interface IMarker {
    public void setPosition(int x, int y);
    public Markers getMarkerType();
    public void changeSize(int newSize);
        
    public enum Markers{
        RedMarker,
        GreenMarker,
        BlueMarker,
        PinkMarker,
        OrangeMarker,
        YellowMarker,
        BlackMarker,
        WhiteMarker
    }
    
}
