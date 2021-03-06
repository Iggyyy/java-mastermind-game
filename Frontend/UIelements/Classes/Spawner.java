package Frontend.UIelements.Classes;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;

import Frontend.Controller;
import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.ISpawner;
import Frontend.UIelements.Interfaces.IMarker.Markers;
import Global.Factory;

public class Spawner extends JButton implements ISpawner {
    
    private Factory factory;
    private Markers ownMarkerType;
    private JButton ownButton;
    private Controller UI;

    public Spawner(Controller ui, Factory _fac, Markers _ownMarkerType){
        super();
        factory = _fac;
        UI = ui;
        this.setBounds(this.getLocation().x, this.getLocation().y, 50, 50);
        ownMarkerType = _ownMarkerType;
        setColor(_ownMarkerType);


        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                IMarker m = _fac.createMarker(_ownMarkerType);
                m.setPosition(100, 15);
                ui.createFloatingMarker(m);

                System.err.print("Created marker!\n");
            }
        });
    }

    private Markers getMarkerType(){
        return ownMarkerType;
    }

    private void setColor(Markers m)
    {
        if (m == Markers.BlueMarker)
            this.setBackground(Color.blue);
        if (m == Markers.GreenMarker)
            this.setBackground(Color.green);
        if (m == Markers.RedMarker)
            this.setBackground(Color.red);
        if (m == Markers.PinkMarker)
            this.setBackground(Color.pink);
        if (m == Markers.OrangeMarker)
            this.setBackground(Color.magenta);
        if (m == Markers.YellowMarker)
            this.setBackground(Color.yellow);
    }
    
    public IMarker instantiateMarker() {
        return factory.createMarker(getMarkerType());
    }

    public JButton getJButton() {
        return ownButton;
    }

    public void setPosition(int x, int y) {
        this.setLocation(x, y);
    }

}
