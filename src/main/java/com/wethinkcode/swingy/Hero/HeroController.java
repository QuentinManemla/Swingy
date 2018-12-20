package com.wethinkcode.swingy.Hero;

import com.wethinkcode.swingy.Database.Database;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

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

    public boolean upgradeHero(Hero _Hero) {
        int neededXp = _Hero.getLevel() * 1000 + (int)Math.pow(_Hero.getLevel()  - 1, 2) * 450;

        if (_Hero.getExperience() >= neededXp) {
            _Hero.setLevel(_Hero.getLevel() + 1);
            return true;
        }
        return false;
    }

    public boolean HeroFight(Hero _Hero) {
        Random rand = new Random();

        int enemyAttack = _Hero.getLevel() + 2;

        if (_Hero.getDefense() > 1)
            enemyAttack += (_Hero.getDefense() / 2);
        if (_Hero.getAttack() > 1)
            enemyAttack += (_Hero.getAttack() / 2);

        if (rand.nextInt(100) < 75) {
            _Hero.setExperience(_Hero.getExperience() + (enemyAttack * 100));
            upgradeHero(_Hero);
            return true;
        } else {
            return false;
        }
    }

    public void removePanel(JFrame MainFrame) {
        _HeroView.removePanel(MainFrame);
    }
}
