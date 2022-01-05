package Frontend.Markers;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;
import Frontend.UIController;
import Frontend.Markers.IMarker.Markers;

public class CheckButton extends JButton implements ICheckButton {

    UIController UI;
    int row;

    public CheckButton(UIController ui, int _row)
    {
        super();
        UI = ui;
        row = _row;
        this.setSize(50, 50);
        this.setBackground(Color.LIGHT_GRAY);

        this.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                checkRow();
                System.err.print("Created marker!\n");
            }
        });
    }

    @Override
    public void checkRow() {
        System.err.print("Checking row!!\n");
        Markers[] pattern = new Markers[4];

        for (IGrabber g : UI.grabbers)
        {
            if(g.getRow() == row)
            {
                try {
                    pattern[g.getID()] = g.getGrabbedMarkerType();
                } catch (Exception e) {
                    System.err.print(e);
                }
                
            }
        }
        UI.nextTurn();
        Markers[] result = getCheckRowResultMarkers(pattern);
        UI.displayResultsInRow(row, result);
    }

    public Markers[] getCheckRowResultMarkers(Markers[] guessPattern)
    {
        //TODO NOT WORKING
        Markers[] ret = new Markers[4];
        Markers[] goalPattern = UI.goalPattern;

        for(int i =0; i<4; i++)
        {
            if(goalPattern[i] == guessPattern[i])
                ret[i] = Markers.BlackMarker;
            else {
                for (int j=0; j<4;j++)
                    if (goalPattern[j] == guessPattern[i])
                        ret[i] = Markers.WhiteMarker;
            }
            
        }

       
        

        return ret;
    }

    public void setBtnPosition(int x, int y) {
        this.setLocation(x, y);
    }
    
}
