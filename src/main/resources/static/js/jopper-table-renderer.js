Jopper.renderers.Table = function (jopper) {

    this.dataTable = null;

    this.createFieldControl = function (field) {
        if (field.type === 'text') {
            return $('<input type="text" name="' + field.key + '">');
        } else if (field.type === 'list') {
            var select = $('<select name="' + field.key + '">');
            $.each(field.options, function (i, option) {
                select.append($('<option value="' + option.key + '">').html(option.name));
            });
            return select;
        } else {
            return $('<div>(未知类型)</div>');
        }
    };

    // @Override
    this.renderMeta = function () {
        var meta = jopper.getMeta();
        var element = jopper.getElement();
        var columns = meta.columns;
        var operations = meta.operations;

        var theadRow = $('<tr>');
        $.each(columns, function (i, column) {
            theadRow.append($('<td>').html(column.name));
        });

        if (operations) {
            theadRow.append($('<td>').html("&nbsp;"));
        }

        this.dataTable = $('<table>').addClass('datatable')
            .append($('<thead>').append(theadRow))
            .append($('<tbody>'));

        element.append(this.dataTable);

        ////////////////////////////////////////////////////////////////////////////////

        if (meta.queryForm) {
            var operation = meta.queryForm.operation;
            var method = meta.queryForm.method;
            var formAction = jopper.resource + '/' + operation.key;
            var form = $('<form onsubmit="return false;" action="' +
                formAction + '" method="' + method + '">');
            var renderer = this;

            if (meta.queryForm.fields) {
                $.each(meta.queryForm.fields, function (i, field) {
                    form.append($('<div class="datatable-form-field">')
                        .append($('<label class="datatable-field-label">').html(field.name))
                        .append(renderer.createFieldControl(field))
                    );
                });
                form.append($('<div class="datatable-form-buttons">')
                    .append($('<button type="submit">').html(operation.name)))
            }

            form.on('submit', function() {
                // get or post
                $[method](formAction, form.serialize(), function(result) {
                    renderer.renderData(result);
                });

                return false;
            });

            element.prepend($('<div class="datatable-form">').append(form));
        }
    };

    // @Override
    this.renderData = function (data) {
        var resourcePath = jopper.resource;
        var meta = jopper.getMeta();
        var columns = meta.columns;
        var operations = meta.operations;

        var tbody = this.dataTable.children('tbody');
        tbody.empty();

        $.each(data.list, function (i, resource) {
            var tbodyRow = $('<tr>').appendTo(tbody);

            $.each(columns, function (i, column) {
                tbodyRow.append($('<td>').addClass('data').html(resource[column.key]));
            });

            if (operations) {
                var operationsTd = $('<td>').addClass('operation').appendTo(tbodyRow);
                $.each(operations, function (i, operation) {
                    operationsTd.append($('<button>').html(operation.name)
                        .on('click', function () {
                            jopper.triggerOperation(resourcePath, resource, operation.key);
                        }));
                });
            }
        });
    };
};