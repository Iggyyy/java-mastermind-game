package Frontend;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Backend.IGameLogic;
import Frontend.Markers.IMarker;
import Frontend.Markers.ISpawner;
import Global.Factory;
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

    public Btn b;


    UIController(IGameLogic gL, Factory fac)
    {
        this.gameLogic = gL;
        this.factory = fac;

        spawners = new ArrayList<ISpawner>();
        gamePanels = new ArrayList<JPanel>();
        resultsCheckPanels = new ArrayList<JPanel>();

        createMainField();
        
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

        for (int i = 0; i<spawners.size(); i++)
        {
            ISpawner s = spawners.get(i);
            s.setPosition(215 + 75*i, 815);
            window.add(s.getJButton());  
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
            JPanel p = new JPanel();
            p.setBounds(200, 150 + 100*i, 385, 80);
            p.setBackground(Color.CYAN);
            gamePanels.add(p);
            window.add(gamePanels.get(i));
        }

        b = new Btn();
        b.changeColor(Color.CYAN);
        b.setBounds(0, 0, 100, 100);
        window.add(b);

    }

    public void createFloatingMarker(IMarker m)
    {
        floatingMarker = m;
        window.add(m.getJPanel());
    }

}
