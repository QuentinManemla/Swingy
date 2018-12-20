package com.wethinkcode.swingy.Artifact;

public class Artifact {
    private String Name;
    private Integer Value;

    Artifact(String _Name, Integer _Value) {
        Name = _Name;
        Value = _Value;
    }

    public String getName() { return Name; }
    public Integer getValue() { return Value; }

    public void setName(String name) {
        Name = name;
    }

    public void setValue(Integer value) {
        Value = value;
    }
}
