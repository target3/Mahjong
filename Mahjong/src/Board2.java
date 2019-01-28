/*
Collaboration with IoKog 
 
Third stage

 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Board2 extends JFrame implements ActionListener{

    protected ArrayList allTiles = new ArrayList();
    protected ArrayList playlist = new ArrayList(); // A copy of our list so we can use the reshuffle ability
    protected Tiles[][] tiles = new Tiles[15][15];
    private JButton[][] buttons=new JButton[15][15];
    protected int endcounter = 0;
    protected Stack stack = new Stack();// Stack for undo help
    public int firstClick = 1;
    public String[] parts1;
    public String[] parts2;
    private JMenuBar mainBar;
    private JMenu menu1, menu2, menu3, menu4, menu5, newgame, menu6;
    private JMenuItem exit, undo, free, reshuffle, aboutus, icons, rename, insname, deltiles, pista1, pista2, pista3, pista4, vathm,deltileshelp, smartfree;
    public String name;
    public int vathmologia, levels, countfree,countreshuffle, countdelete, countsmartfree;
    
    public Board2() {
        super("Mahjong");//Creation of frame
        setSize(1700, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        name = JOptionPane.showInputDialog(null, "What's your name?");// Ask from user for their name just to enter at leadership txt file 
        levels = Integer.parseInt(JOptionPane.showInputDialog(null, "1.Easy 2.Medium 3.Hard"));// Also ask for a level (change the number of helps given)
        //Character Tiles
        for (int i = 1; i < 10; i++) {

            for (int j = 0; j < 4; j++) {
                allTiles.add(new Characters(i));

            }
        }
        //Circle Tiles
        for (int i = 1; i < 10; i++) {

            for (int j = 0; j < 4; j++) {
                allTiles.add(new Circle(i));

            }
        }
        //Bamboo Tiles
        for (int i = 1; i < 10; i++) {

            for (int j = 0; j < 4; j++) {
                allTiles.add(new Bamboo(i));

            }
        }
        for (int i = 1; i < 5; i++) {

            for (int j = 0; j < 2; j++) {
                allTiles.add(new Seasons(i));

            }
        }
        for (int i = 1; i < 5; i++) {

            for (int j = 0; j < 2; j++) {
                allTiles.add(new Flowers(i));
            }

        }

        Collections.shuffle(allTiles);
        playlist.addAll(allTiles);

        for (int i = 0; i < 15; i++) {

            for (int j = 0; j < 15; j++) {
                tiles[i][j] = new Circle(1);

            }
        }

        for (int i = 0; i < 15; i++) {
            tiles[6][i] = null;
            tiles[7][i] = null;
        }
        for (int j = 0; j < 15; j++) {
            tiles[j][6] = null;
            tiles[j][7] = null;
        }
        tiles[3][0] = null;
        tiles[4][0] = null;
        tiles[4][1] = null;
        tiles[1][1] = null;
        tiles[2][1] = null;
        tiles[1][2] = null;
        tiles[2][2] = null;
        tiles[3][3] = null;
        tiles[3][4] = null;
        tiles[4][4] = null;
        tiles[4][3] = null;
        tiles[3][9] = null;
        tiles[3][10] = null;
        tiles[4][9] = null;
        tiles[4][10] = null;
        tiles[0][10] = null;
        tiles[1][12] = null;
        tiles[1][13] = null;
        tiles[2][12] = null;
        tiles[2][13] = null;
        tiles[4][13] = null;
        tiles[4][14] = null;
        tiles[3][14] = null;
        tiles[9][0] = null;
        tiles[9][1] = null;
        tiles[10][0] = null;
        tiles[10][1] = null;
        tiles[12][1] = null;
        tiles[12][2] = null;
        tiles[13][1] = null;
        tiles[13][2] = null;
        tiles[9][3] = null;
        tiles[9][4] = null;
        tiles[10][3] = null;
        tiles[10][4] = null;
        tiles[9][9] = null;
        tiles[9][10] = null;
        tiles[10][9] = null;
        tiles[10][10] = null;
        tiles[13][13] = null;
        tiles[12][12] = null;
        tiles[13][12] = null;
        tiles[12][13] = null;
        tiles[9][13] = null;
        tiles[9][14] = null;
        tiles[10][14] = null;

        int count = 0;
        for (int i = 0; i < 15; i++) {

            for (int j = 0; j < 15; j++) {

                if (tiles[i][j] != null) {
                    tiles[i][j] = (Tiles) allTiles.get(count++);

                }

            }

        }
      Container pane=getContentPane();
        pane.setLayout(null);
        
        mainBar = new JMenuBar();
        menu1 = new JMenu("Game");
        menu2 = new JMenu("Profile");
        menu3 = new JMenu("Rules");
        menu4 = new JMenu("About");
        menu5 = new JMenu("Help ");
        menu6 = new JMenu("Score ");
        newgame = new JMenu("New Game");
        exit = new JMenuItem("Exit");
        undo = new JMenuItem("Undo");
        smartfree = new JMenuItem("Which tiles to delete");
        deltileshelp=new JMenuItem("Delete two tiles");
        free = new JMenuItem("Free Tiles");
        reshuffle = new JMenuItem("Reshuffle Board");
        icons = new JMenuItem("TIles");
        rename = new JMenuItem("Edit Name");
        insname = new JMenuItem("Insert Name");
        deltiles = new JMenuItem("How to delete Tiles");
        aboutus = new JMenuItem("About Us");
        pista1 = new JMenuItem("Board 1");
        pista2 = new JMenuItem("Board 2");
        pista3 = new JMenuItem("Board 3");
        pista4 = new JMenuItem("Board 4");
        vathm=new JMenuItem("My Score");
        setJMenuBar(mainBar);
        mainBar.add(menu1);
        mainBar.add(menu2);
        mainBar.add(menu3);
        mainBar.add(menu4);
        mainBar.add(menu5);
        mainBar.add(menu6);
        menu1.add(newgame);
        menu3.add(deltiles);
        menu3.add(icons);
        menu1.add(exit);
        menu5.add(undo);
        menu5.add(free);
        menu5.add(reshuffle);
        menu4.add(aboutus);
        menu2.add(insname);
        menu2.add(rename);
        menu6.add(vathm);
        newgame.add(pista1);
        newgame.add(pista2);
        newgame.add(pista3);
        newgame.add(pista4);
        menu5.add(deltileshelp);
        menu5.add(smartfree);
        smartfree.addActionListener(this);
        deltileshelp.addActionListener(this);
        pista1.addActionListener(this);
        pista2.addActionListener(this);
        pista3.addActionListener(this);
        pista4.addActionListener(this);
        deltiles.addActionListener(this);
        icons.addActionListener(this);
        undo.addActionListener(this);
        free.addActionListener(this);
        reshuffle.addActionListener(this);
        aboutus.addActionListener(this);
        insname.addActionListener(this);
        rename.addActionListener(this);
        exit.addActionListener(this);
        newgame.addActionListener(this);
        vathm.addActionListener(this);

        int i = 50;
        int j = 170;
        for (int k = 0; k < 15; k++) {

            for (int l = 0; l < 15; l++) {
                if (tiles[k][l] != null) {
                    if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 1) {
                        ImageIcon icon = new ImageIcon("src/char1.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 2) {
                        ImageIcon icon = new ImageIcon("src/char2.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 3) {
                        ImageIcon icon = new ImageIcon("src/char3.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 4) {
                        ImageIcon icon = new ImageIcon("src/char4.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 5) {
                        ImageIcon icon = new ImageIcon("src/char5.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 6) {
                        ImageIcon icon = new ImageIcon("src/char6.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 7) {
                        ImageIcon icon = new ImageIcon("src/char7.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 8) {
                        ImageIcon icon = new ImageIcon("src/char8.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Characters") && tiles[k][l].getId() == 9) {
                        ImageIcon icon = new ImageIcon("src/char9.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 1) {
                        ImageIcon icon = new ImageIcon("src/cir1.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 2) {
                        ImageIcon icon = new ImageIcon("src/cir2.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 3) {
                        ImageIcon icon = new ImageIcon("src/cir3.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 4) {
                        ImageIcon icon = new ImageIcon("src/cir4.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 5) {
                        ImageIcon icon = new ImageIcon("src/cir5.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 6) {
                        ImageIcon icon = new ImageIcon("src/cir6.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 7) {
                        ImageIcon icon = new ImageIcon("src/cir7.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 8) {
                        ImageIcon icon = new ImageIcon("src/cir8.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Circle") && tiles[k][l].getId() == 9) {
                        ImageIcon icon = new ImageIcon("src/cir9.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 1) {
                        ImageIcon icon = new ImageIcon("src/bab1.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 2) {
                        ImageIcon icon = new ImageIcon("src/bab2.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 3) {
                        ImageIcon icon = new ImageIcon("src/bab3.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 4) {
                        ImageIcon icon = new ImageIcon("src/bab4.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 5) {
                        ImageIcon icon = new ImageIcon("src/bab5.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 6) {
                        ImageIcon icon = new ImageIcon("src/bab6.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 7) {
                        ImageIcon icon = new ImageIcon("src/bab7.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 8) {
                        ImageIcon icon = new ImageIcon("src/bab8.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Bamboo") && tiles[k][l].getId() == 9) {
                        ImageIcon icon = new ImageIcon("src/bab9.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Flowers") && tiles[k][l].getId() == 1) {
                        ImageIcon icon = new ImageIcon("src/flo1.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Flowers") && tiles[k][l].getId() == 2) {
                        ImageIcon icon = new ImageIcon("src/flo2.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Flowers") && tiles[k][l].getId() == 3) {
                        ImageIcon icon = new ImageIcon("src/flo3.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Flowers") && tiles[k][l].getId() == 4) {
                        ImageIcon icon = new ImageIcon("src/flo4.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Seasons") && tiles[k][l].getId() == 1) {
                        ImageIcon icon = new ImageIcon("src/sea1.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Seasons") && tiles[k][l].getId() == 2) {
                        ImageIcon icon = new ImageIcon("src/sea2.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Seasons") && tiles[k][l].getId() == 3) {
                        ImageIcon icon = new ImageIcon("src/sea3.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                    } else if (tiles[k][l].getName().equals("Seasons") && tiles[k][l].getId() == 4) {
                        ImageIcon icon = new ImageIcon("src/sea4.png");
                        buttons[k][l] = new JButton(icon);
                        i += 63;
                        buttons[k][l].setBounds(i, j, icon.getIconWidth(), icon.getIconHeight());
                        pane.add(buttons[k][l]);
                        buttons[k][l].addActionListener(this);
                        buttons[k][l].setContentAreaFilled(false);
                        buttons[k][l].setActionCommand(k + " " + l);
                        
                    }
                        playlist.add(buttons[k][l]);
                } else {
                    buttons[k][l] = new JButton();
                    i += 63;
                    
                    buttons[k][l].setBounds(i, j, 100, 50);
                    pane.add(buttons[k][l]);
                    buttons[k][l].setActionCommand("null");
                    buttons[k][l].setVisible(false);

                }
            }
            j += 44;
            i = 50;

        }
        setContentPane(pane);
        pane.setBackground(new Color(225,204,51));
    }

   

    public void Undo() {
        if (stack.empty()) {
            JOptionPane.showMessageDialog(null,"Nothing to Undo");
        } else {
            int jj = (int) stack.pop();
            int ii = (int) stack.pop();
            Tiles poptile = (Tiles) stack.pop();
            int j = (int) stack.pop();
            int i = (int) stack.pop();
            Tiles poptile1 = (Tiles) stack.pop();
            tiles[ii][jj] = poptile;
            tiles[i][j] = poptile1;
            playlist.add(tiles[i][j]);
            playlist.add(tiles[ii][jj]);
            buttons[i][j].setVisible(true);
            buttons[ii][jj].setVisible(true);
            endcounter--;
            

        }

    }

    public void Reshuffle() {

        Collections.shuffle(playlist);
        int count = 0;
        for (int i = 0; i < 15; i++) {

            for (int j = 0; j < 15; j++) {

                if (tiles[i][j] != null) {
                    tiles[i][j] = (Tiles) playlist.get(count++);

                }

            }

        }
        
    }

     public void Free() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (j == 0 && tiles[i][j] != null) {
                    buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                } else if (j == 14 && tiles[i][j] != null) {
                    buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                } else if (tiles[i][j] != null && (tiles[i][j - 1] == null || tiles[i][j + 1] == null)) {
                    buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
                }
            }
        }
    }

     public void SmartDel() {
        int k = 0;
        int l = 0;
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 13; j++) {
                if ((k == 0 && l == 0) && tiles[i][j] != null && (tiles[i][j - 1] == null || tiles[i][j + 1] == null) && (!tiles[i][j].getName().equals("Flowers") && !tiles[i][j].getName().equals("Seasons"))) {
                    k = i;
                    l = j;
                    
                } else if ((k != 0 && l != 0)) {
                    if ((tiles[i][j] != null && tiles[k][l] != null) && (tiles[i][j].getId() == tiles[k][l].getId()) && (tiles[i][j].getName() == tiles[k][l].getName()) && (tiles[i][j - 1] == null || tiles[i][j + 1] == null)) {
                        buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        buttons[k][l].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }
                }

            }
        }

    }
     
       public void Deltiles(){
        int k=0;
        int l=0;
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                if ((k==0&&l==0)  && tiles[i][j] != null && (tiles[i][j - 1] != null || tiles[i][j + 1] != null) && (!tiles[i][j].getName().equals("Flowers") && !tiles[i][j].getName().equals("Seasons") )) {
                    k=i;
                    l=j;
                   
                }else if((k!=0 && l!=0)){
                   if((tiles[i][j] != null && tiles[k][l] != null)&&(tiles[i][j].getId() == tiles[k][l].getId())&& (tiles[i][j].getName() == tiles[k][l].getName())){
                        stack.push(tiles[i][j]); 
                       stack.push(i);
                        stack.push(j);
                        stack.push(tiles[k][l]);
                        stack.push(k);
                        stack.push(l);
                        tiles[i][j] = null;
                        tiles[k][l] = null;
                        buttons[i][j].setVisible(false);
                        buttons[k][l].setVisible(false);
                        
                        Win(endcounter++);
                    }
                }
                
            }
        }
        
        
    }
     
  
    public void Win(int endcounter) {
        if (endcounter == 61) {
            
            
            ImageIcon icon = new ImageIcon("src/win.jpg");
            JOptionPane.showOptionDialog(null,null , "YOU WON !!!! Your Score : "+vathmologia, JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null, null);
       
            Board1 b=new Board1();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       String p = e.getActionCommand();
        int i, ii, j, jj;
        Object source = e.getSource();

        if (source == exit) {
            BufferedWriter out;
            try {
                out = new BufferedWriter(new FileWriter("leadership.txt", true));
                out.write("Name : " + name + " Score : " + vathmologia + " at Board 3");
                out.newLine();
                out.flush(); 
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Output error");
            }
            System.exit(0);

        }else if(source==insname)
        {
            name = JOptionPane.showInputDialog(null, "What's your name?");
            JOptionPane.showMessageDialog(null,"Welcome to Board 3    !!!!!!!!!!!! \n   Good Luck " + name);
        }else if(source==rename)
        {
            name = JOptionPane.showInputDialog(null, "What's your new name?");
            JOptionPane.showMessageDialog(null,"Good Luck with your new name " + name);
        }
        else if (source == pista1) {
            Board b = new Board();
        }else if (source == smartfree) {
            if (levels == 3) {
                if (countsmartfree < 1) {
                    SmartDel();
                    countsmartfree++;
                    JOptionPane.showMessageDialog(null, "Helps left " + (1 - countsmartfree));
                } else {
                    JOptionPane.showMessageDialog(null, "No more Helps");
                }
            } else if (levels == 2) {
                if (countsmartfree < 2) {
                    SmartDel();
                    countsmartfree++;
                    JOptionPane.showMessageDialog(null, "Helps left " + (2 - countsmartfree));
                } else {
                    JOptionPane.showMessageDialog(null, "No more Helps");
                }
            } else {
                if (countsmartfree < 3) {
                    SmartDel();
                    countsmartfree++;
                    JOptionPane.showMessageDialog(null, "Helps left " + (3 - countsmartfree));
                } else {
                    JOptionPane.showMessageDialog(null, "No more Helps");
                }
            }
        }  else if (source == pista2) {
            Board3 b = new Board3();
        } else if (source == pista3) {
            Board2 b = new Board2();
        } else if (source == pista4) {
            Board1 b = new Board1();
        } else if (source == aboutus) {
            JOptionPane.showOptionDialog(null, "IoKog\ntarget3", "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        } else if (source == undo) {
            Undo();
        }  else if (source == free) {
            if(levels==3){
                if(countfree<1){
                    Free();
                    countfree++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (1-countfree));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else if(levels==2){
                if(countfree<2){
                    Free();
                    countfree++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (2-countfree));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else{
                if(countfree<3){
                    Free();
                    countfree++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (3-countfree));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }
        } 
        else if (source == reshuffle) {
            
            if(levels==3){
                if(countreshuffle<1){
                    Reshuffle();
                    countreshuffle++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (1-countreshuffle));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else if(levels==2){
                if(countreshuffle<2){
                    Reshuffle();
                    countreshuffle++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (2-countreshuffle));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else{
                if(countreshuffle<3){
                    Reshuffle();
                    countreshuffle++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (3-countreshuffle));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }
        }
        else if (source == deltileshelp) {
            
            if(levels==3){
                if(countdelete<1){
                    Deltiles();
                    countdelete++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (1-countdelete));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else if(levels==2){
                if(countdelete<2){
                    Deltiles();
                    countdelete++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (2-countdelete));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }else{
                if(countdelete<3){
                    Deltiles();
                    countdelete++;
                    JOptionPane.showMessageDialog(null,"Helps left " + (3-countdelete));
                }else{
                    JOptionPane.showMessageDialog(null,"No more Helps");
                }
            }
        } 
        else if (source==icons)
        {
            ImageIcon icon = new ImageIcon("src/tiles.jpg");
             JOptionPane.showOptionDialog(null, null, "About Tiles", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null, null);
        }else if (source==deltiles)
        {
            ImageIcon icon = new ImageIcon("src/del.jpg");
             JOptionPane.showOptionDialog(null, null, " How to Delete Tiles", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, null, null);
        }
       
        else if (source == vathm) {
            JOptionPane.showOptionDialog(null, "Your Score is : "+vathmologia, "Score", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        }else {
            if (firstClick == 1) {

                parts1 = p.split(" ");
                
                firstClick = 2;
            } else if (firstClick == 2) {

                parts2 = p.split(" ");
                

                i = Integer.parseInt(parts1[0]);
                j = Integer.parseInt(parts1[1]);
                ii = Integer.parseInt(parts2[0]);
                jj = Integer.parseInt(parts2[1]);
                if(i!=ii || j!=jj){
                
                if (j == 0 || j == 14) {
                    if ((jj == 0 || jj == 14) && (tiles[i][j].getName().equals("Flowers") && tiles[ii][jj].getName().equals("Flowers"))) {

                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);

                    } else if ((jj == 0 || jj == 14) && (tiles[i][j].getName().equals("Seasons") && tiles[ii][jj].getName().equals("Seasons"))) {

                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;

                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else if ((jj == 0 || jj == 14) && (tiles[i][j].getName() == tiles[ii][jj].getName()) && (tiles[i][j].getId() == tiles[ii][jj].getId())) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=20;
                        Win(endcounter++);

                    } else if ((tiles[i][j].getName() == tiles[ii][jj].getName()) && (tiles[i][j].getId() == tiles[ii][jj].getId()) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=20;
                        Win(endcounter++);
                    } else if ((tiles[i][j].getName().equals("Seasons") && tiles[ii][jj].getName().equals("Seasons")) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else if ((tiles[i][j].getName().equals("Flowers") && tiles[ii][jj].getName().equals("Flowers")) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else {
                        JOptionPane.showOptionDialog(null, "U Cant", "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }

                } else if (tiles[i][j - 1] == null || tiles[i][j + 1] == null) {
                    if ((jj == 0 || jj == 14) && (tiles[i][j].getName().equals("Flowers") && tiles[ii][jj].getName().equals("Flowers"))) {

                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);

                    } else if ((jj == 0 || jj == 14) && (tiles[i][j].getName().equals("Seasons") && tiles[ii][jj].getName().equals("Seasons"))) {

                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else if ((jj == 0 || jj == 14) && (tiles[i][j].getName() == tiles[ii][jj].getName()) && (tiles[i][j].getId() == tiles[ii][jj].getId())) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=20;
                        Win(endcounter++);

                    } else if ((tiles[i][j].getName() == tiles[ii][jj].getName()) && (tiles[i][j].getId() == tiles[ii][jj].getId()) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=20;
                        Win(endcounter++);
                    } else if ((tiles[i][j].getName().equals("Seasons") && tiles[ii][jj].getName().equals("Seasons")) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else if ((tiles[i][j].getName().equals("Flowers") && tiles[ii][jj].getName().equals("Flowers")) && (tiles[ii][jj - 1] == null || tiles[ii][jj + 1] == null)) {
                        //remove
                        stack.push(tiles[i][j]);
                        stack.push(i);
                        stack.push(j);
                        stack.push(tiles[ii][jj]);
                        stack.push(ii);
                        stack.push(jj);
                        tiles[i][j] = null;
                        tiles[ii][jj] = null;
                        buttons[i][j].setVisible(false);
                        buttons[ii][jj].setVisible(false);
                        vathmologia+=10;
                        Win(endcounter++);
                    } else {
                        JOptionPane.showOptionDialog(null, "U Cant", "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    }
                } else {
                    JOptionPane.showOptionDialog(null, "U Cant", "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                }
                firstClick = 1;

            }
            }else {
                firstClick=1;
                JOptionPane.showOptionDialog(null, "U Cant", "About", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            }
        }
        
    }

}
