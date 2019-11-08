package com.sampleapp.book;

import com.hyd.jopper.meta.NamedKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yiding.he
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends NamedKey {

    public static final Category ALL = new Category("（全部）", "");

    private int id;

    public Category() {
    }

    public Category(String name, String key) {
        super(name, key);
    }
}
