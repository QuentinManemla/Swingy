package com.wethinkcode.swingy.Hero;

public class Hero {
    private Integer Id = 0;
    private String Name;
    private String HeroClass;
    private Integer Level = 0;
    private Integer Experience = 0;
    private Integer Attack = 0;
    private Integer Defense = 0;
    private Integer HitPoints = 0;

    public Hero() {}

    public Hero(String _name, String _class) {
        this.Name = _name;
        this.HeroClass = _class;
    }

    public Hero(String _name, String _class, int _level, int _experience, int _attack, int _defense, int _hitpoints) {
        this.Name = _name;
        this.HeroClass = _class;
        this.Level = _level;
        this.Experience = _experience;
        this.Attack = _attack;
        this.Defense = _defense;
        this.HitPoints = _hitpoints;
    }

    public Hero(Integer _Id, String _name, String _class, int _level, int _experience, int _attack, int _defense, int _hitpoints) {
        this.Id = _Id;
        this.Name = _name;
        this.HeroClass = _class;
        this.Level = _level;
        this.Experience = _experience;
        this.Attack = _attack;
        this.Defense = _defense;
        this.HitPoints = _hitpoints;
    }

    public Integer getId() { return Id; }
    public String getName() {
        return Name;
    }

    public String getHeroClass() {
        return HeroClass;
    }

    public Integer getLevel() {
        return  Level;
    }

    public Integer getExperience() { return Experience; }

    public Integer getDefense() { return Defense; }

    public Integer getAttack() {
        return Attack;
    }

    public Integer getHitPoints() {
        return HitPoints;
    }
}
