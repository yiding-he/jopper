package com.hyd.jopper.meta;

/**
 * @author yiding.he
 */
public class TextFormField extends FormField {

    public static TextFormField create(String name, String key) {
        return new TextFormField(name, key);
    }

    public TextFormField() {
    }

    public TextFormField(String name, String key) {
        super(name, key, "text");
    }
}
