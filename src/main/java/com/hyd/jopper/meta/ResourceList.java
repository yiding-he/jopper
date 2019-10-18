package com.hyd.jopper.meta;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResourceList<T> {

    private List<T> list;
}
