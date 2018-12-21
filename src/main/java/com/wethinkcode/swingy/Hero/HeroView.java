package com.wethinkcode.swingy.Hero;

import sun.tools.jar.Main;

import javax.swing.*;
import java.util.List;

public class HeroView {
    public static JPanel _HeroPanel = new JPanel();
    public HeroView() {
    }

    public void displayHero(Hero _hero) {
        System.out.println("ID| Name | Class | Level | Xp | Attack | Defense | HitPoints");
        System.out.println(_hero.getId() + " | " + _hero.getName() + " | " + _hero.getHeroClass() +
                " | " + _hero.getLevel() + " | " + _hero.getExperience() + " | " + _hero.getAttack() +
                " | " + _hero.getDefense() + " | " + _hero.getHitPoints());
    }

    public void displayHeroes(List<Hero> _heroes, JFrame _MainFrame, JTextField _HeroIdField) {
        _HeroPanel.setBounds(200, 10, 300, 600);
        _HeroPanel.setBackground(java.awt.Color.gray);
        _MainFrame.add(_HeroPanel);

        JLabel _title = new JLabel("Select a previously Created Hero");
        _title.setBounds(0,0, 300, 30);

        int y = 30;
        for (Hero hero: _heroes) {
            JLabel heroBut = new JLabel(hero.getId() + ":" + hero.getName());
            heroBut.setBounds(50, y, 100, 30);
            _HeroPanel.add(heroBut);
            y += 50;
        }

        _HeroPanel.add(_title);
        _HeroIdField.setBounds(50, y, 100, 30);
        _HeroPanel.add(_HeroIdField);

//        JButton SelectHero = new JButton("Select Hero");
//        SelectHero.setBounds(50, y += 50, 100, 30);
//        _HeroPanel.add(SelectHero);

        _MainFrame.repaint();
    }

    public void removePanel(JFrame MainFrame) {
        MainFrame.remove(_HeroPanel);
        MainFrame.repaint();
    }
}
