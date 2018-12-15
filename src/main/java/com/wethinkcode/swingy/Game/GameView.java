package com.wethinkcode.swingy.Game;

import com.wethinkcode.swingy.Hero.Hero;

import javax.swing.*;

public class GameView {
    public static JPanel _MapPanel = null;
    int panel_y = 10;
    int panel_x = 100;

    public void GameView() {}
    public void DisplayMap(Boolean _Console, String[][] _Map, JFrame _MainFrame) {
        if (_Console) {
            for (int y = 0; y < _Map.length; y++) {
                String[] Line = _Map[y];
                for (int x = 0; x < _Map.length; x++) {
                    System.out.print(_Map[x][y]);
                }
                System.out.print("\n");
            }
        } else {
            if (_MapPanel == null) {
                _MapPanel = new JPanel();
                _MapPanel.setBounds(200, 10, 300, 600);
                _MapPanel.setBackground(java.awt.Color.gray);
                _MainFrame.add(_MapPanel);
            }

            panel_y = 10;
            panel_x = 100;
            for(int y = 0; y < _Map.length; y++) {
                String [] Line = _Map[y];
                for (int x = 0; x < _Map.length; x++) {
                    JLabel _test = new JLabel(_Map[x][y]);
                    _test.setBounds(panel_x, panel_y, 20, 10);
                    _MapPanel.add(_test);
                    panel_x += 15;
                }
                panel_y += 15;
                panel_x = 100;
            }
            _MainFrame.repaint();
        }
    }

    public void GuiMovement(Hero _Hero, JTextField _directionTextField, JFrame _MainFrame) {
        JLabel _DirectionsLabel = new JLabel("Enter your Desired Direction");
        JLabel Directions = new JLabel("1 - North | 2 - South | 3 - East | 4 - West");
        _DirectionsLabel.setBounds(50, panel_y += 50, 300, 10);
        Directions.setBounds(30, panel_y += 15, 400, 10);

        _directionTextField.setBounds(70, panel_y += 15, 200, 20);

        _MapPanel.add(_directionTextField);
        _MapPanel.add(Directions);
        _MapPanel.add(_DirectionsLabel);

        panel_y = 10;
        _MainFrame.repaint();
    }

    public void RemovePanel(JFrame _MainFrame) {
        _MapPanel.removeAll();
        _MapPanel.repaint();
    }
}
