package com.wethinkcode.swingy.Hero;

public class Hero {
    private String Name;
    private String HeroClass;
    private int Level = 0;
    private int Experience = 0;
    private int Attack = 0;
    private int HitPoints = 0;

    Hero(String _name, String _class) {
        this.Name = _name;
        this.HeroClass = _class;
    }

    public String getName() {
        return Name;
    }

    public String getHeroClass() {
        return HeroClass;
    }

    public int getLevel() {
        return  Level;
    }

    public int getExperience() {
        return Level;
    }

    public int getAttack() {
        return Level;
    }

    public int getHitPoints() {
        return HitPoints;
    }
}
