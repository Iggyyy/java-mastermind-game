package Frontend.UIelements.Interfaces;

import javax.swing.JButton;

import Frontend.UIelements.Interfaces.IMarker.Markers;

public interface ISpawner {
    public IMarker instantiateMarker();
    public JButton getJButton();
    public void setPosition(int x, int y);
}
