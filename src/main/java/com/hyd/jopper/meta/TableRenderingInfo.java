package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TableRenderingInfo extends RenderingInfo {

    private List<Column> columns = new ArrayList<>();

    private List<Operation> operations = new ArrayList<>();

    public TableRenderingInfo addColumn(String name, String key) {
        this.columns.add(new Column(name, key));
        return this;
    }

    @Override
    public RenderingType getRenderingType() {
        return RenderingType.Table;
    }
}
