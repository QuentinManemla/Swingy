package com.wethinkcode.swingy.Game;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wethinkcode.swingy.Hero.Hero;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.security.tools.keytool.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    public static GameView _GameView = new GameView();
    public static BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static Integer _mapDimensions;
    public String[][] _Map;
    public static Integer _HeroX;
    public static Integer _HeroY;
    public boolean complete = false;
    public JTextField _directionTextField = new JTextField();

    // Direction Action Listiners
    ActionListener _DirectionAL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                Integer direction = Integer.parseInt(_directionTextField.getText());
                if (direction.equals(1))
                    _HeroY -= 1;
                else if (direction.equals(2))
                    _HeroY += 1;
                else if (direction.equals(3))
                    _HeroX += 1;
                else if (direction.equals(4))
                    _HeroX -= 1;
            } catch (NumberFormatException ex ) {
                System.out.println("Incorrect Number");
            }
        }
    };

    public Game(Boolean _console, Hero _Hero, JFrame _MainFrame) {
        _mapDimensions = (_Hero.getLevel() - 1) * 5 + 10 - (_Hero.getLevel() % 2);
        _Map = new String[_mapDimensions][_mapDimensions];
        _HeroX = Math.round(_mapDimensions / 2) + 1;
        _HeroY = Math.round(_mapDimensions / 2) + 1;

        System.out.println("Hero X: " + _HeroX);
        System.out.println("Hero Y: " + _HeroY);
        for (int y = 0; y < _Map.length; y++) {
            String[] Line = _Map[y];
            for (int x = 0; x < _Map.length; x++) {
                _Map[x][y] = " * ";
            }
        }
        _HeroX = Math.round(_mapDimensions / 2);
        _HeroY = Math.round(_mapDimensions / 2);
        _Map[_HeroY][_HeroX] = " P ";
        _directionTextField.addActionListener(_DirectionAL);
        GameLoop(_console, _Hero, _MainFrame);
    }

    public void ProcessMovement(Hero _Hero) {
        System.out.println("[Swingy: ] Select Movement Direction");
        System.out.print("1: North\n2: South\n3: East\n4: West\n");
        System.out.print("Direction: -> ");

        try {
            Integer direction = Integer.parseInt(_bufferedReader.readLine());
            _Map[_HeroX][_HeroY] = " * ";

            if (direction.equals(1))
                _HeroY -= 1;
            else if (direction.equals(2))
                _HeroY += 1;
            else if (direction.equals(3))
                _HeroX += 1;
            else if (direction.equals(4))
                _HeroX -= 1;
            else {
                System.out.println("[Swingy] Unknown Option");
                ProcessMovement(_Hero);
            }
        } catch (IOException ex ) {
            System.out.println("[Swingy] IO Exception [Game - Proccess Movement]");
            ProcessMovement(_Hero);
        } catch ( NumberFormatException ex ) {
            System.out.println("[Swingy] Number Format Exception [Game - Proccess Movement]");
            ProcessMovement(_Hero);
        }
    }
    public void GameLoop(Boolean _Console, Hero _Hero, JFrame _MainFrame) {
           _GameView.DisplayMap(_Console, _Map, _MainFrame);
           if (_Console) {
               ProcessMovement(_Hero);
               if (_HeroY > 0 && _HeroX > 0) {
                   _Map[_HeroX][_HeroY] = " P ";
               }
               if (!complete)
                   GameLoop(_Console, _Hero, _MainFrame);
           } else {
               _GameView.GuiMovement(_Hero, _directionTextField, _MainFrame);
           }
    }
}
