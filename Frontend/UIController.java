package Frontend;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Point;

import Backend.IGameLogic;
import Frontend.Markers.CheckButton;
import Frontend.Markers.Grabber;
import Frontend.Markers.ICheckButton;
import Frontend.Markers.IGrabber;
import Frontend.Markers.IMarker;
import Frontend.Markers.ISpawner;
import Frontend.Markers.Marker;
import Frontend.Markers.Spawner;
import Global.Factory;
import Global.Logger;
import Frontend.Markers.IMarker.Markers;

public class UIController {

    //Classes
    IGameLogic gameLogic;
    Factory factory;

    //Swing
    public JFrame window;
    public JTextArea welcomeText;
    public JButton btn;
    public IMarker markerRed;
    public ArrayList<ISpawner> spawners; 
    public JPanel spawnersPanel;
    public ArrayList<JPanel> resultsCheckPanels;
    public ArrayList<JPanel> gamePanels;
    public IMarker floatingMarker;
    public ArrayList<IGrabber> grabbers;
    public ArrayList<IMarker> resultMarkers;
    public ArrayList<ICheckButton> checkButtons;

    public Btn b;
    public IMarker mark;

    public Markers[] goalPattern;

    UIController(IGameLogic gL, Factory fac)
    {
        this.gameLogic = gL;
        this.factory = fac;

        spawners = new ArrayList<ISpawner>();
        gamePanels = new ArrayList<JPanel>();
        resultsCheckPanels = new ArrayList<JPanel>();
        grabbers = new ArrayList<IGrabber>();
        resultMarkers = new ArrayList<IMarker>();
        checkButtons = new ArrayList<ICheckButton>();

        createGoalPattern();
        createMainField();
        //createFloatingMarker(factory.createMarker(Markers.OrangeMarker));

        MouseEvents mEvents = new MouseEvents(this);
        window.addMouseListener(mEvents);
        window.addMouseMotionListener(mEvents);
        
        window.setVisible(true);
    }

    public void createMainField()
    {
        window = new JFrame();
        window.setSize(700, 1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        

        welcomeText = new JTextArea("MASTERMIND");
        welcomeText.setBounds(250, 10, 300, 100);
        welcomeText.setBackground(Color.black);
        welcomeText.setForeground(Color.white);
        welcomeText.setEditable(false);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 26));
        window.add(welcomeText);


        for (Markers m : Markers.values())
        {
            spawners.add(factory.createSpawner(this, factory, m));
        }

        spawnersPanel = new JPanel();
        spawnersPanel.setBounds(200, 800, 385, 80);
        spawnersPanel.setBackground(Color.lightGray);

        for (int i = 0; i<spawners.size()-2; i++)
        {
            ISpawner s = spawners.get(i);
            s.setPosition(215 + 75*i, 815);
            window.add((Spawner)s);  
        }

        window.add(spawnersPanel);  
        

        for (int i=0; i<6; i++)
        {
            JPanel p = new JPanel();
            p.setBounds(25, 150 + 100*i, 100, 80);
            p.setBackground(Color.DARK_GRAY);
            resultsCheckPanels.add(p);
            window.add(resultsCheckPanels.get(i));
        }

        for (int i=0; i<6; i++)
        {
            for (int j=0; j<4; j++)
            {
                IGrabber grabber = factory.createGrabber(new Point(235 + j*85,165 + 100*i), i, j);
                grabbers.add(grabber);
                window.add((Grabber)grabber);
            }

            JPanel p = new JPanel();
            p.setBounds(200, 150 + 100*i, 385, 80);
            p.setBackground(Color.CYAN);
            gamePanels.add(p);
            window.add(gamePanels.get(i));

            ICheckButton cb = new CheckButton(this, i);
            cb.setBtnPosition(610, 165 + 100 * i);
            checkButtons.add(cb);
            window.add((CheckButton)cb);
        }

    }

    public void createFloatingMarker(IMarker m)
    {
        Logger.log("Creating marker" + this.floatingMarker);

        if (this.floatingMarker != null)
            removeDraggedMarker();

        this.floatingMarker = m;
        window.add((Marker)floatingMarker);
    }

    public Boolean isMarkerBeingDragged()
    {
        return floatingMarker != null ? true : false;
    }
    
    public void removeDraggedMarker()
    {
        window.remove((Marker)this.floatingMarker);
        this.floatingMarker = null;
        Logger.log("Removed old floating marker" + this.floatingMarker);
        updateFrame();
    }

    public void updateFrame()
    {
        SwingUtilities.updateComponentTreeUI(this.window);
    }

    public Boolean tryToPlaceMarker()
    {
        Logger.log("Trying to place marker");
        //Place marker
        if(false)
        {
            Logger.log("Placed marker");
            removeDraggedMarker();
            return true;
        }
        
        return false;
    }

    public void updateDraggedMarkerCords()
    {
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.floatingMarker.setPosition(p.x - 30, p.y - 50);
        updateFrame();
    }

    private void createGoalPattern()
    {
        goalPattern = new Markers[4];
        for (int i =0 ; i<4; i++)
            goalPattern[i] = Markers.values()[new Random().nextInt(4)];
    }

    public void displayResultsInRow(int row, Markers[] resultMarkersArr){

        Arrays.sort(resultMarkersArr);
        for (int i = 0; i<4; i++){
            if(resultMarkersArr[i] != null)
            {
                IMarker newM = factory.createMarker(resultMarkersArr[i]);
                newM.changeSize(20);
                newM.setPosition(25 + i*25, 150 + 100*row + 25 * i%2);
                resultMarkers.add(newM);
                window.add((Marker)newM);
            }
        }
    }


}
