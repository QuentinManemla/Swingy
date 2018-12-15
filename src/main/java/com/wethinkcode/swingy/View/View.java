package com.wethinkcode.swingy.View;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
    public static BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static JPanel _MenuPanel = new JPanel();

    public View(Boolean console, JFrame _Frame) {
        _MenuPanel.setBounds(250, 0, 300, 200);
        _MenuPanel.setBackground(java.awt.Color.gray);
        if (console) {
            System.out.println("[ Swingy ]: Welcome to Swingy!");
        } else {
//            JLabel _welcomeLabel = new JLabel("Welcome to Swingy");
//            _welcomeLabel.setBounds(300, 10, 300, 30);
//
//            _MenuPanel.add(_welcomeLabel);
        }
        _Frame.add(_MenuPanel);
    }

    public int ConsoleMenu(Integer heroCount) {
        try {
                System.out.println("\n[ Swingy ]: Previously Created Heroes: " + heroCount);
                System.out.println("[ Swingy ]: 1. Create a New Hero");
                System.out.println("[ Swingy ]: 2. Select a previously Created Hero\n");
                System.out.print("Select Option: ");

                String opt = _bufferedReader.readLine();
                Integer _ret = Integer.parseInt(opt);

                return (_ret);
        } catch (IOException ex) {
            System.out.println("[Swingy]: IO Exception [Console Menu]: " + ex);
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("[Swingy]: Number Format Exception [Console Menu]: " + ex);
            System.exit(1);
        }
        return (1);
    }

    public void GUIMenu(Integer heroCount, JFrame _Frame, ActionListener _createHeroAL, ActionListener _selectHeroAL) {
        JLabel _heroCount = new JLabel("Previously Created Heroes: " + heroCount);
        _heroCount.setBounds(0, 0, 200, 30);
        _MenuPanel.add(_heroCount);

        JButton _createHero = new JButton("Create a New Hero");
        JButton _selectHero = new JButton("Select a Previously Created Hero");

        _createHero.setBounds(280, 90, 250, 30);
        _createHero.setBorderPainted(false);

        _selectHero.setBounds(280, 120, 250, 30);
        _selectHero.setBorderPainted(false);

        _createHero.addActionListener(_createHeroAL);
        _selectHero.addActionListener(_selectHeroAL);
        _MenuPanel.add(_createHero);
        _MenuPanel.add(_selectHero);
    }

    public void updateGui(JFrame _Frame) {
        _Frame.setLayout(null);
        _Frame.setVisible(true);
    }

    public void removeMenuPanel(JFrame _Frame) {
        _Frame.remove(_MenuPanel);
        _Frame.repaint();
    }
}
