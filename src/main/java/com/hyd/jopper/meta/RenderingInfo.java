package com.hyd.jopper.meta;

import java.util.List;

public abstract class RenderingInfo {

    public abstract RenderingType getRenderingType();

    public abstract List<Operation> getOperations();
}
