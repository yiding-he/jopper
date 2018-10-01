package com.hyd.jopper.meta;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResourceInfo<T> {

    private T data;

    private RenderingInfo renderingInfo;
}
