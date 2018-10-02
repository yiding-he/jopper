package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Column extends NamedKey {

    private String format;

    public Column() {
    }

    public Column(String name, String key) {
        super(name, key);
    }
}
