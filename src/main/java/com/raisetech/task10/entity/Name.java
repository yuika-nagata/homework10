package com.raisetech.task10.entity;

public class Name {
    private final String id;
    private final String name;
    
    public Name(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
