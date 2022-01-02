package Frontend.Markers;

import javax.swing.JButton;

import Frontend.Markers.IMarker.Markers;

public interface ISpawner {
    public IMarker instantiateMarker();
    public JButton getJButton();
    public void setPosition(int x, int y);
}
