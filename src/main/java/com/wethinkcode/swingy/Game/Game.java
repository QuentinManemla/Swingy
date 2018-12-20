package com.wethinkcode.swingy.Game;

import com.wethinkcode.swingy.Hero.Hero;
import com.wethinkcode.swingy.Hero.HeroController;
import sun.tools.jar.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

@SuppressWarnings("IntegerDivisionInFloatingPointContext")
public class Game {
    private static GameView _GameView = new GameView();
    private static HeroController _HeroController = new HeroController();
    private static BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static JFrame MainFrame;
    private static Boolean Console;
    private static Hero Hero;
    private String[][] _Map;
    public int[][] enemies;
    private static Integer _HeroX;
    private static Integer _HeroY;
    private boolean complete;
    private int _mapDimensions;
    private JTextField _directionTextField = new JTextField();

    public Game(Boolean _console, Hero _Hero, JFrame _MainFrame) {
        MainFrame = _MainFrame;
        Console = _console;
        Hero = _Hero;

        _mapDimensions = (Hero.getLevel() - 1) * 5 + 10 - (Hero.getLevel() % 2);
        _Map = new String[_mapDimensions][_mapDimensions];
        _HeroX = Math.round(_mapDimensions / 2) + 1;
        _HeroY = Math.round(_mapDimensions / 2) + 1;

        System.out.println("Map Dimensions: " + _mapDimensions);
        System.out.println("Enemies: " + Math.round((_mapDimensions * _mapDimensions) * 0.4));
        int enemiestemp = (int) Math.round((_mapDimensions * _mapDimensions) * 0.4);
        enemies = new int[enemiestemp][enemiestemp];

        for (int y = 0; y < _Map.length; y++) {
            for (int x = 0; x < _Map.length; x++) {
                _Map[x][y] = " * ";
            }
        }

        _HeroX = Math.round(_mapDimensions / 2);
        _HeroY = Math.round(_mapDimensions / 2);
        _Map[_HeroX][_HeroY] = " P ";

        Random rand = new Random();

        int limit = (_mapDimensions-1);
        for (int y = 0; y < enemies.length; y++) {
            int randx = 0 + rand.nextInt((limit - 0) + 1);
            int randy = 0 + rand.nextInt((limit - 0) + 1);

            if (randx != _HeroX && randy != _HeroY) {
                _Map[randx][randy] = " E ";
            }
        }

        // Direction Action Listiner
        ActionListener _DirectionAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    _Map[_HeroX][_HeroY] = " * ";
                    Integer direction = Integer.parseInt(_directionTextField.getText());
                    if (direction.equals(1))
                        _HeroY -= 1;
                    else if (direction.equals(2))
                        _HeroY += 1;
                    else if (direction.equals(3))
                        _HeroX += 1;
                    else if (direction.equals(4))
                        _HeroX -= 1;
                    _directionTextField.setText("");
                    _Map[_HeroX][_HeroY] = " P ";
                    _GameView.RemovePanel(MainFrame);
                    GameLoop();
                } catch (NumberFormatException ex) {
                    System.out.println("Incorrect Number");
                    _Map[_HeroX][_HeroY] = " P ";
                    _directionTextField.setText("");
                    _GameView.RemovePanel(MainFrame);
                    GameLoop();
                }
            }
        };
        _directionTextField.addActionListener(_DirectionAL);
        complete = false;
    }

    private void enemyCollision() {
        System.out.println("[Swingy :] You have Come in Contact with an Enemy, You have two options");
        System.out.print("1: Run\n2: Fight\n");
        System.out.print("Your Choice: -> ");

        try {
            Integer choice = Integer.parseInt(_bufferedReader.readLine());

            if (choice.equals(1)) {

            } else if (choice.equals(2))
                _HeroController.HeroFight(Hero);
            else if (!choice.equals(1) || !choice.equals(2)) {
                System.out.println("[Swingy] Unknown Option Given [Game - Enemy Collision]");
                enemyCollision();
            }
        } catch ( IOException ex ) {
            System.out.println("[Swingy] IO Exception [Game - Enemy Collision]");
            enemyCollision();
        } catch (NumberFormatException ex) {
            System.out.println("[Swingy] Number Format Exception [Game - Enemy Collision]");
            enemyCollision();
        }
    }

    private boolean ProcessMovement() {
        System.out.println("[Swingy: ] Select Movement Direction");
        System.out.print("1: North\n2: South\n3: East\n4: West\n5: Exit Game\n");
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
            else if (direction.equals(5))
                return false;
            else {
                System.out.println("[Swingy] Unknown Option");
                ProcessMovement();
            }
            return true;
        } catch (IOException ex ) {
            System.out.println("[Swingy] IO Exception [Game - Proccess Movement]");
            ProcessMovement();
        } catch ( NumberFormatException ex ) {
            System.out.println("[Swingy] Number Format Exception [Game - Proccess Movement]");
            ProcessMovement();
        }
        return false;
    }

    public void GameLoop() {
           _GameView.DisplayMap(Console, _Map, MainFrame);
           if (Console) {
               if(ProcessMovement()) {
                   if (_Map[_HeroX][_HeroY] == " E ") {
                       System.out.println("You come in contact with an Enemy");
                       _Map[_HeroX][_HeroY] = " P ";
                       enemyCollision();
                   } else {
                       System.out.println("Hero X: " + _HeroX + " Hero Y: " + _HeroY);
                       if (_HeroX.equals(_mapDimensions - 1) || _HeroY.equals(_mapDimensions - 1) || _HeroX.equals(0)
                               || _HeroY.equals(0))
                           System.out.println("[Swingy]: You have won the Game");
                       else
                           _Map[_HeroX][_HeroY] = " P ";
                   }
                   GameLoop();
               }
           } else {
               _GameView.GuiMovement(Hero, _directionTextField, MainFrame);
           }
    }
}
