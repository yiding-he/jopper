package com.hyd.jopper.meta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiding.he
 */
@Data
public class Form {

    private String method = "get";

    private Operation operation;

    private List<FormField> fields = new ArrayList<>();

    public void addField(FormField formField) {
        this.fields.add(formField);
    }

    public void setOperation(String name, String key) {
        setOperation(new Operation(name, key));
    }
}
