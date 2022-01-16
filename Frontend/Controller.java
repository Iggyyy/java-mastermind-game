package Frontend;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EventListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.TextUI;

import org.w3c.dom.events.MouseEvent;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Frontend.UIelements.Classes.CheckButton;
import Frontend.UIelements.Classes.Grabber;
import Frontend.UIelements.Classes.Marker;
import Frontend.UIelements.Classes.Spawner;
import Frontend.UIelements.Interfaces.ICheckButton;
import Frontend.UIelements.Interfaces.IGrabber;
import Frontend.UIelements.Interfaces.IMarker;
import Frontend.UIelements.Interfaces.ISpawner;
import Frontend.UIelements.Interfaces.IMarker.Markers;
import Global.Factory;
import Global.Logger;

public class Controller {

    //#region variables
    //Variables
    public int currentTurn = 0;
    public Boolean displayedPattern = false;

    //Classes
    //IGameLogic gameLogic;
    Factory factory;

    //Swing
    public ImageIcon img;
    public JLabel imgOverlay;
    public JFrame window;
    public JTextArea welcomeText;
    public JTextArea wonText;
    public JButton btn;
    public IMarker markerRed;
    public ArrayList<ISpawner> spawners; 
    public JPanel spawnersPanel;
    public ArrayList<JPanel> resultsCheckPanels;
    public ArrayList<JPanel> gamePanels;
    public IMarker floatingMarker;
    public ArrayList<IGrabber> grabbers;
    public ArrayList<IMarker> resultMarkers;
    public ArrayList<IMarker> goalPatternMarkersDisplay;
    public ArrayList<ICheckButton> checkButtons;
    public JButton revealButton;
    public IMarker mark;

    //MAKE JLAYEREDPANE
    public JLayeredPane layeredPane;
    public Markers[] goalPattern;

    //#endregion

    //#region public methods
    public void createFloatingMarker(IMarker m)
    {
        Logger.log("Creating marker" + this.floatingMarker);

        if (this.floatingMarker != null)
            removeDraggedMarker();

        this.floatingMarker = m;
        layeredPane.add((Marker)floatingMarker, JLayeredPane.POPUP_LAYER);
    }

    public Boolean isMarkerBeingDragged()
    {
        return floatingMarker != null ? true : false;
    }
    
    public void removeDraggedMarker()
    {
        layeredPane.remove((Marker)this.floatingMarker);
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
        Point p = MouseInfo.getPointerInfo().getLocation();

        for (IGrabber gr : grabbers)
        {
            System.err.println(gr.getRow());
            // System.err.println(p);
            // System.err.println( gr.checkIfContainsPoint(window,p));
            if ( gr.getRow() == currentTurn && gr.checkIfContainsPoint(window,p))
            {
                Logger.log("Placing marker inside grabber");
                gr.grabMarker(factory.createMarker(this.floatingMarker.getMarkerType()));
                layeredPane.add((Marker)gr.getMarker(), JLayeredPane.MODAL_LAYER);
                Logger.log("Placed marker");
                removeDraggedMarker();
                updateFrame();
                return true;
            }
        }
        return false;
    }

    public void updateDraggedMarkerCords()
    {
        Point p = MouseInfo.getPointerInfo().getLocation();
        this.floatingMarker.setPosition(p.x - 30, p.y - 50);
        updateFrame();
    }

    public void removeDisplayedGoalPattern()
    {
        for (var p: goalPatternMarkersDisplay)
        {   
            layeredPane.remove((Marker)p);
        }
        updateFrame();
    }

    public void displayResultsInRow(int row, Markers[] resultMarkersArr)
    {
        int blacks = 0;
        for (var m : resultMarkersArr)
        {
            if (m != null)
            {
                System.err.println(m.name());
                if (m.name() == Markers.BlackMarker.name())
                    blacks++;
            }
        }

        if (blacks == 4)
            won();
        
        if (blacks != 4 && currentTurn == 6)
            lost();

        int placed = 0;
        for (int i = 0; i<4; i++){
            if(resultMarkersArr[i] != null)
            {
                System.err.println("Created resuult marker");
                IMarker newM = factory.createMarker(resultMarkersArr[i]);
                newM.changeSize(25);
                if (placed >= 2)
                    newM.setPosition(40 + (placed-2)*45, 650 - row*100 + 5);
                else
                    newM.setPosition(40 + placed*45, 700 - row*100);
                resultMarkers.add(newM);
                layeredPane.add((Marker)newM, JLayeredPane.POPUP_LAYER);
                placed++;
            }
        }
        updateFrame();
    }

    public void nextTurn()
    {
        this.currentTurn+=1;
    }

    //#endregion

    //#region private methods
    private void won()
    {
        System.err.println("You WON!");
        wonText = new JTextArea("You WON!");
        wonText.setBounds(250, 75, 300, 30);
        wonText.setBackground(Color.black);
        wonText.setForeground(Color.white);
        wonText.setEditable(false);
        wonText.setFont(new Font("Arial", Font.PLAIN, 26));
        layeredPane.add(wonText, JLayeredPane.POPUP_LAYER);
        updateFrame();
    }

    private void createGoalPattern()
    {
        goalPattern = new Markers[4];
        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int k = 0; k<6; k++)
            set.add(k);
        Collections.shuffle(set);
        set.remove(0);

        for (int i =0 ; i<4; i++)
        {
            goalPattern[i] = Markers.values()[set.get(i)];
            IMarker m = factory.createMarker(goalPattern[i]);
            m.setPosition(235 + i*85, 75);
            goalPatternMarkersDisplay.add(m);
        }
    }

    private void displayGoalPattern()
    {
        for (var p: goalPatternMarkersDisplay)
        {   
            layeredPane.add((Marker)p, JLayeredPane.POPUP_LAYER);
        }
        updateFrame();
    }

    Controller(Factory fac)
    {
        this.factory = fac;

        spawners = new ArrayList<ISpawner>();
        gamePanels = new ArrayList<JPanel>();
        resultsCheckPanels = new ArrayList<JPanel>();
        grabbers = new ArrayList<IGrabber>();
        resultMarkers = new ArrayList<IMarker>();
        goalPatternMarkersDisplay = new ArrayList<IMarker>();
        checkButtons = new ArrayList<ICheckButton>();

        layeredPane = new JLayeredPane();
        layeredPane.setSize(700, 1000);
        createGoalPattern();
        createMainField();
        //createFloatingMarker(factory.createMarker(Markers.OrangeMarker));

        MouseEvents mEvents = new MouseEvents(this);
        window.addMouseListener(mEvents);
        window.addMouseMotionListener(mEvents);

        window.setVisible(true);
    }

    private void createMainField()
    {
        //MAIN FRAME
        window = new JFrame();
        window.setSize(700, 1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setTitle("MASTERMIND 90s");

        img = new ImageIcon("./images/logo.png");
        window.setIconImage(img.getImage());
        
        imgOverlay = new JLabel();
        imgOverlay.setIcon(img);
        imgOverlay.setBounds(25,20, 100, 100);
        imgOverlay.setVisible(true);
        layeredPane.add(imgOverlay, JLayeredPane.FRAME_CONTENT_LAYER);
        
        //TEXT
        // welcomeText = new JTextArea("MASTERMIND");
        // welcomeText.setBounds(0, 0, 300, 25);
        // welcomeText.setBackground(Color.black);
        // welcomeText.setForeground(Color.white);
        // welcomeText.setEditable(false);
        // welcomeText.setFont(new Font("Arial", Font.PLAIN, 26));
        // layeredPane.add(welcomeText, JLayeredPane.PALETTE_LAYER);

        //SPAWNERS
        for (Markers m : Markers.values())
        {
            spawners.add(factory.createSpawner(this, factory, m));
        }
        spawnersPanel = new JPanel();
        spawnersPanel.setBounds(150, 800, 475, 80);
        spawnersPanel.setBackground(Color.lightGray);
        for (int i = 0; i<spawners.size()-2; i++)
        {
            ISpawner s = spawners.get(i);
            s.setPosition(175 + 75*i, 815);
            window.add((Spawner)s);  
        }
        layeredPane.add(spawnersPanel, JLayeredPane.PALETTE_LAYER);  
        
        //RESULT PANELS
        for (int i=0; i<6; i++)
        {
            JPanel p = new JPanel();
            p.setBounds(25, 150 + 100*i, 100, 80);
            p.setBackground(Color.DARK_GRAY);
            resultsCheckPanels.add(p);
            //window.add(resultsCheckPanels.get(i));
            layeredPane.add(resultsCheckPanels.get(i), JLayeredPane.PALETTE_LAYER);
        }

        //GRABBERS, GAME PANELS, CHECK BUTTONS
        for (int i=0; i<6; i++)
        {
            for (int j=0; j<4; j++)
            {
                IGrabber grabber = factory.createGrabber(new Point(235 + j*85,165 + 100*i), 5-i, j);
                grabbers.add(grabber);
                layeredPane.add((Grabber)grabber, JLayeredPane.PALETTE_LAYER);
            }

            JPanel p = new JPanel();
            p.setBounds(200, 150 + 100*i, 385, 80);
            p.setBackground(Color.CYAN);
            gamePanels.add(p);
            layeredPane.add(gamePanels.get(i), JLayeredPane.FRAME_CONTENT_LAYER);

            ICheckButton cb = new CheckButton(this, 5-i);
            cb.setBtnPosition(610, 165 + 100 * i);
            checkButtons.add(cb);
            layeredPane.add((CheckButton)cb, JLayeredPane.FRAME_CONTENT_LAYER);
            
            window.add(layeredPane);
        }

        //REVEAL BUTTON
        revealButton = new JButton();
        revealButton.setLocation(665, 5);
        revealButton.setSize(15, 15);
        revealButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(displayedPattern == false){
                    displayGoalPattern();
                    displayedPattern = true;
                }
                else{
                    removeDisplayedGoalPattern();
                    displayedPattern = false;
                }

            }

        });
        revealButton.setFont(new Font("Arial", Font.PLAIN, 8));
        layeredPane.add(revealButton, JLayeredPane.PALETTE_LAYER);
    }

    private void lost()
    {
        System.err.println("You LOST!");
        wonText = new JTextArea("You LOST!");
        wonText.setBounds(250, 75, 300, 30);
        wonText.setBackground(Color.black);
        wonText.setForeground(Color.white);
        wonText.setEditable(false);
        wonText.setFont(new Font("Arial", Font.PLAIN, 26));
        layeredPane.add(wonText, JLayeredPane.POPUP_LAYER);
        displayGoalPattern();
        updateFrame();
    }
    //#endregion

}
