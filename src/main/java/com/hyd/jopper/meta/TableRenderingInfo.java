package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableRenderingInfo extends RenderingInfo {

    private List<Column> columns = new ArrayList<>();

    private List<Operation> operations = new ArrayList<>();

    public TableRenderingInfo addColumn(String name, String key) {
        this.columns.add(new Column(name, key));
        return this;
    }

    public TableRenderingInfo addOperation(String name, String key) {
        this.operations.add(new Operation(name, key));
        return this;
    }

    public TableRenderingInfo addOperations(Operation... operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    @Override
    public RenderingType getRenderingType() {
        return RenderingType.Table;
    }
}
