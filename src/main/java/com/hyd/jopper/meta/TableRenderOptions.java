package com.hyd.jopper.meta;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Data
@EqualsAndHashCode(callSuper = true)
public class TableRenderOptions extends RenderOptions {

    private List<Column> columns = new ArrayList<>();

    private List<Operation> operations = new ArrayList<>();

    private Form queryForm;

    ////////////////////////////////////////////////////////////////////////////////

    public TableRenderOptions addColumn(String name, String key) {
        this.columns.add(new Column(name, key));
        return this;
    }

    public TableRenderOptions addOperation(String name, String key) {
        this.operations.add(new Operation(name, key));
        return this;
    }

    public TableRenderOptions addOperation(String name, String key, String icon) {
        this.operations.add(new Operation(name, key, icon));
        return this;
    }

    public TableRenderOptions addOperations(Operation... operations) {
        this.operations.addAll(Arrays.asList(operations));
        return this;
    }

    public TableRenderOptions enableForm(Consumer<Form> formConsumer) {
        this.queryForm = new Form();
        formConsumer.accept(this.queryForm);
        return this;
    }

    @Override
    public RenderingType getRenderingType() {
        return RenderingType.Table;
    }
}
