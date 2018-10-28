package com.hyd.jopper.meta;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ResourceList {

    private List<List<String>> list;
}
