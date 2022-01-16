package Frontend.UIelements.Classes;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;
import Frontend.Controller;
import Frontend.UIelements.Interfaces.ICheckButton;
import Frontend.UIelements.Interfaces.IGrabber;
import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.IMarker.Markers;

public class CheckButton extends JButton implements ICheckButton {

    Controller UI;
    int row;

    public CheckButton(Controller ui, int _row)
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

        //CHECK 
        for (IGrabber g : UI.grabbers)
        {   
            if(g.getRow() == row)
            {
                if (g.getMarker() == null)
                {
                    System.err.println("Empty grabber!");    
                    return;
                }

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
        Markers[] ret = new Markers[4];
        Markers[] goalPattern = UI.goalPattern;

        //DEBUG MESSAGE
        System.err.println("\nPattern");
        for (var p : goalPattern)
            System.err.print(p.name());
        System.err.println("\nGuess pattern");
        for (var p : guessPattern)
            System.err.print(p.name());
        
        int[] taken = {0,0,0,0};
        //CHECK
        for(int i =0; i<4; i++)
        {
            if(goalPattern[i].name() == guessPattern[i].name())
            {
                ret[i] = Markers.BlackMarker;
                taken[i] = 1;
            }
            else {
                for (int j=0; j<4;j++)
                    if (goalPattern[j].name() == guessPattern[i].name() && taken[j] == 0)
                    {
                        ret[i] = Markers.WhiteMarker;
                        taken[j] = 1;
                    }
            }    
        }

        //DEBUG MESSAGE
        System.err.println("\nRet pattern");
        for (var p : ret)
            if(p != null)
                System.err.print(p.name());
        
        return ret;
    }

    public void setBtnPosition(int x, int y) {
        this.setLocation(x, y);
    }
    
}
