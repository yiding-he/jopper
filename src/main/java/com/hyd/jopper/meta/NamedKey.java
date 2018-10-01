package com.hyd.jopper.meta;

public abstract class NamedKey {

    private String name;

    private String key;

    public NamedKey() {
    }

    public NamedKey(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
