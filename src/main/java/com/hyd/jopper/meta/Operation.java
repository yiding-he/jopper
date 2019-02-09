package com.hyd.jopper.meta;

public class Operation extends NamedKey {

    private String icon;

    public Operation() {
    }

    public Operation(String name, String key) {
        super(name, key);
    }

    public Operation(String name, String key, String icon) {
        super(name, key);
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
