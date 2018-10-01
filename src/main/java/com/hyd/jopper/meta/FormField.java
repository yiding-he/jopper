package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yiding.he
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class FormField extends NamedKey {

    public FormField() {
    }

    public FormField(String name, String key) {
        super(name, key);
    }

    public FormField(String name, String key, String type) {
        super(name, key);
        this.type = type;
    }

    private String type;
}
