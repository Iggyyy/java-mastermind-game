package Frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import Backend.IGameLogic;

public class UIController {

    //Classes
    IGameLogic gameLogic;

    //Swing
    JFrame window;
    public JTextArea welcomeText;
    public JButton btn;
    
    UIController(IGameLogic gL)
    {
        this.gameLogic = gL;

        createMainField();

        window.setVisible(true);
    }

    public void createMainField()
    {
        window = new JFrame();
        window.setSize(600, 1000);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        

        welcomeText = new JTextArea("HELLO WORLD");
        welcomeText.setBounds(50, 50, 300, 100);
        welcomeText.setBackground(Color.black);
        welcomeText.setForeground(Color.white);
        welcomeText.setEditable(false);
        welcomeText.setFont(new Font("Arial", Font.PLAIN, 26));
        window.add(welcomeText);


        btn = new JButton("");
        btn.setBackground(Color.red);
        //btn.setForeground(Color.green);
        btn.setSize(150, 150);
        btn.setBounds(100, 100, 150, 150);
        //btn.setVisible(true);
        window.add(btn);
    }

}
