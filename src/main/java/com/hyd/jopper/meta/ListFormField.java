package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yiding.he
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListFormField<T extends NamedKey> extends FormField {

    public static <T extends NamedKey> ListFormField<T> create(String name, String key, T... options) {
        return new ListFormField<>(name, key, options);
    }

    public static <T extends NamedKey> ListFormField<T> create(String name, String key, List<T> options) {
        return new ListFormField<>(name, key, options);
    }

    public ListFormField() {
    }

    public ListFormField(String name, String key) {
        super(name, key, "list");
    }

    public ListFormField(String name, String key, List<T> options) {
        super(name, key, "list");
        this.options = options;
    }

    public ListFormField(String name, String key, T... options) {
        super(name, key, "list");
        this.options = new ArrayList<>(Arrays.asList(options));
    }

    private List<T> options;
}
