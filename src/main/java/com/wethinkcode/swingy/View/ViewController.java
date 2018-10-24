package com.wethinkcode.swingy.View;
import com.wethinkcode.swingy.Database.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController {
    public static Boolean _Console;
    private static View _View;
    public static JFrame MainFrame = new JFrame("Swingy");

    ActionListener _createHeroAL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Create New Hero!!");
        }
    };

    ActionListener _selectHeroAL = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Select Previously Selected Hero");
        }
    };

    public ViewController(Boolean console ) {
        _Console = console;
        _View = new View(_Console,MainFrame);
    }

    public void Menu(Integer heroCount) {
        if (_Console) {
            Integer _option = _View.ConsoleMenu(heroCount);
        } else {
            _View.GUIMenu(heroCount, MainFrame, _createHeroAL);
        }
        _View.updateGui(MainFrame);
    }
}
