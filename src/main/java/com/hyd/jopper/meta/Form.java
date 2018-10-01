package com.hyd.jopper.meta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yiding.he
 */
@Data
public class Form {

    private String resource;

    private String operation;

    private List<FormField> fields = new ArrayList<>();
}
