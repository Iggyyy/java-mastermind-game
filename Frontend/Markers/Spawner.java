package Frontend.Markers;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;

import Frontend.UIController;
import Frontend.Markers.IMarker.Markers;
import Global.Factory;

public class Spawner implements ISpawner, ActionListener {
    
    private Factory factory;
    private Markers ownMarkerType;
    private JButton ownButton;
    private UIController UI;

    public Spawner(UIController ui, Factory _fac, Markers _ownMarkerType){
        factory = _fac;
        UI = ui;
        ownButton = new JButton();
        ownButton.setBounds(0, 0, 50, 50);
        setColor(_ownMarkerType);
    }

    private Markers getMarkerType(){
        return ownMarkerType;
    }

    private void setColor(Markers m)
    {
        if (m == Markers.BlueMarker)
            ownButton.setBackground(Color.blue);
        if (m == Markers.GreenMarker)
            ownButton.setBackground(Color.green);
        if (m == Markers.RedMarker)
            ownButton.setBackground(Color.red);
        if (m == Markers.PinkMarker)
            ownButton.setBackground(Color.pink);
        if (m == Markers.OrangeMarker)
            ownButton.setBackground(Color.orange);
    }
    
    public IMarker instantiateMarker() {
        return factory.createMarker(getMarkerType());
    }

    public JButton getJButton() {
        return ownButton;
    }

    public void setPosition(int x, int y) {
        ownButton.setLocation(x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        IMarker m = instantiateMarker();
        m.setPosition(50, 50);
        UI.createFloatingMarker(m);

        System.err.print("Clicked");
        
    }
}
