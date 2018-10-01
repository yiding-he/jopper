Jopper.renderers.Table = function (jopper) {

    this.dataTable = null;

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

        }
    };

    // @Override
    this.renderData = function (resourcePath, data) {
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