package com.hyd.jopper.meta;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public abstract class RenderingInfo {

    private boolean autoQuery = true;

    public abstract RenderingType getRenderingType();

    public abstract List<Operation> getOperations();
}
