package com.hyd.jopper.book;

import com.hyd.jopper.meta.NamedKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yiding.he
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Category extends NamedKey {

    public Category() {
    }

    public Category(String name, String key) {
        super(name, key);
    }
}
