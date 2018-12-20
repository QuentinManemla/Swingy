package com.wethinkcode.swingy;
import com.wethinkcode.swingy.Database.Database;
import com.wethinkcode.swingy.Game.Game;
import com.wethinkcode.swingy.Hero.Hero;
import com.wethinkcode.swingy.Hero.HeroController;
import com.wethinkcode.swingy.View.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private static Boolean _Console;
    private static Game _Game;
    private static Database _Database = new Database();
    private static HeroController _HeroController = new HeroController();
    private static Hero _ActiveHero = new Hero();
    private static View _View;
    /**
     * JTextField for the Hero Id used to get the Hero from the Database
     */
    private static JTextField _heroIdField;

    static {
        _heroIdField = new JTextField("");
    }

    private static JFrame MainFrame = new JFrame("Swingy");
    private Integer _heroCount;

    private ActionListener _createHeroAL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            System.out.println("Create New Hero!!");
        }
    };

    private ActionListener _selectHeroAL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            System.out.println("[Swingy Gui:] Select Previously Selected Hero");
            _View.removeMenuPanel(MainFrame);
            _HeroController.previousHero(_Database, _Console, MainFrame, _heroIdField);
        }
    };

     public Controller(Boolean console) {
        _Console = console;
        MainFrame.setSize(800, 600);
        _View = new View(_Console,MainFrame);
        _heroCount = _Database.countHeroes();
        ActionListener _heroIDAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Swingy Gui:] Hero ID " + _heroIdField.getText());
                try {
                    Integer HeroID = Integer.parseInt(_heroIdField.getText());
                    _ActiveHero = _Database.getHero(HeroID);
                    if (_ActiveHero != null)
                        _HeroController.removePanel(MainFrame);
                    set_Game(new Game(_Console, _ActiveHero, MainFrame));
                    _Game.GameLoop();
                } catch (NumberFormatException ex) {
                    _View.removeMenuPanel(MainFrame);
                    _HeroController.previousHero(_Database, _Console, MainFrame, _heroIdField);
                }
            }
        };
        _heroIdField.addActionListener(_heroIDAL);
    }

    private static void set_Game(Game _Game) {
        Controller._Game = _Game;
    }

    public void Start() {
        if (_Console) {
            Integer _option = _View.ConsoleMenu(_heroCount);
            if ( _option.equals(1) ) {
                _ActiveHero = _HeroController.createHero(_Console);
                set_Game(new Game(_Console, _ActiveHero, MainFrame));
                _Game.GameLoop();
                _Database.updateHero(_ActiveHero);
            } else if ( _option.equals(2) ) {
                _ActiveHero = _HeroController.previousHero(_Database, _Console, MainFrame, _heroIdField);
                set_Game(new Game(_Console, _ActiveHero, MainFrame));
                _Game.GameLoop();
                _Database.updateHero(_ActiveHero);
            } else {
                System.out.println("Selected Option is Invalid");
                Start();
            }
        } else {
            _View.GUIMenu(_heroCount, MainFrame, _createHeroAL, _selectHeroAL);
            _View.updateGui(MainFrame);
        }
    }
}
