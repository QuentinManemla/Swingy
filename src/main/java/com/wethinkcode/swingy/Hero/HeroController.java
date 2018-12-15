package com.wethinkcode.swingy.Hero;

import com.wethinkcode.swingy.Database.Database;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class HeroController {
    public static HeroView _HeroView = new HeroView();
    public static BufferedReader _bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public HeroController() {

    }

    public Hero createHero(boolean _console) {
        try {
            if (_console) {
                System.out.println("[Swingy:] Create a new Hero");
                System.out.println("===========================");

                System.out.print("[Swingy:] Hero Name -> ");
                String Heroname = _bufferedReader.readLine();

                System.out.println("[Swingy: ] Hero Class -> \n1 -> Human\n2-> Orc\n3-> Undead\n");
                System.out.println("[Swingy: ] Pick a number -> ");
                String HeroClass = _bufferedReader.readLine();

                Integer SelectedClass = Integer.parseInt(HeroClass);
                if (SelectedClass > 3 || SelectedClass < 1) {
                    System.out.println("[Swingy]: Selected Hero Class is invalid please try again [Hero Controller - Create Hero]");
                    createHero(_console);
                }

                System.out.println("===========================");
                System.out.println("[Swingy:] Hero Created Successfully");
                return (new Hero(Heroname, HeroClass));
            } else {
                return null;
            }
        } catch(IOException ex) {
            System.out.println("[Swingy]: IO Exception [Hero Controller - Create Hero]: " + ex);
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("[Swingy]: Number Format Exception [Hero Controller - Create Hero]: " + ex);
            System.exit(1);
        }
        return null;
    }

    public Hero previousHero(Database _database, boolean _console, JFrame _MainFrame, JTextField _heroIdField) {
        try {
            List<Hero> _heroes = _database.getHeroes();
            System.out.println("[Swingy]: List of Available Heroes");
            if (_console) {
                for (Hero hero : _heroes) {
                    _HeroView.displayHero(hero);
                }
                System.out.println("[Swingy: ] Pick a Hero (ID:) -> ");
                Integer HeroId = Integer.parseInt(_bufferedReader.readLine());
                Hero _hero = _database.getHero(HeroId);
                if (_hero != null)
                    return _hero;
                else
                    System.out.println("NULL!!3");
            } else {
                _HeroView.displayHeroes(_heroes, _MainFrame, _heroIdField);
            }
        } catch (IOException ex) {
            System.out.println("[Swingy]: IO Exception [Hero Controller - Previous Hero]: " + ex);
            System.exit(1);
        } catch (NumberFormatException ex) {
            System.out.println("[Swingy]: Number Format Exception [Hero Controller - Previous Hero]: " + ex);
            previousHero(_database, _console, _MainFrame, _heroIdField);
        }
        return null;
    }

    public void removePanel(JFrame MainFrame) {
        _HeroView.removePanel(MainFrame);
    }
}
