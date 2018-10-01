$(function () {
    new Jopper().initJopper();
});

var Jopper = function () {
    this.initJopper = function () {
        $('[data-jopper]').each(function () {
            initJopperElement($(this));
        });
    };

    var log = function (msg) {
        if (window.console) {
            window.console.log(msg);
        }
    };

    var triggerOperation = function (resourcePath, resource, operation) {

    };

    var initJopperElement = function (element) {
        var text = element.attr('data-jopper');
        var definition = JSON.parse(text);
        var resourcePath = definition.resource;

        $.get(resourcePath + "/list", {}, function (resourceList) {
            renderResourceList(resourcePath, resourceList, element);
        });
    };

    var renderResourceList = function (resourcePath, resourceList, element) {
        var renderingType = resourceList.renderingInfo.renderingType;
        if (renderingType === "Table") {
            renderTableResourceList(resourcePath, resourceList, element);
        } else {
            log('Rendering type "' + renderingType + '" not implemented.')
        }
    };

    var renderTableResourceList = function (resourcePath, resourceList, element) {

        var theadRow = $('<tr>');
        var columns = resourceList.renderingInfo.columns;
        var operations = resourceList.renderingInfo.operations;

        $.each(columns, function (i, column) {
            theadRow.append($('<td>').html(column.name));
        });

        if (operations) {
            theadRow.append($('<td>').html("&nbsp;"));
        }

        var thead = $('<thead>').append(theadRow);
        var tbody = $('<tbody>');

        $.each(resourceList.list, function (i, resource) {
            var tbodyRow = $('<tr>').appendTo(tbody);

            $.each(columns, function (i, column) {
                tbodyRow.append($('<td>').addClass('data').html(resource[column.key]));
            });

            if (operations) {
                var operationsTd = $('<td>').addClass('operation').appendTo(tbodyRow);
                $.each(operations, function (i, operation) {
                    operationsTd.append($('<button>').html(operation.name)
                        .on('click', function () {
                            triggerOperation(resourcePath, resource, operation.key);
                        }));
                });
            }
        });

        var table = $('<table>').addClass('datatable').append(thead).append(tbody);

        $(element).append(table)
    };};

