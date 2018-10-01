$(function(){
    initJopper();
});

var log = function (msg) {
    if (window.console) {
        window.console.log(msg);
    }
};

var initJopper = function () {
    $('[data-jopper]').each(function() {
        initJopperElement($(this));
    });
};

var initJopperElement = function (element) {
    var text = element.attr('data-jopper');
    var definition = JSON.parse(text);
    var resource = definition.resource;

    $.get(resource + "/list", {}, function (resourceList) {
        renderResourceList(resourceList, element);
    });
};

var renderResourceList = function (resourceList, element) {
    var renderingType = resourceList.renderingInfo.renderingType;
    if (renderingType === "Table") {
        renderTableResourceList(resourceList, element);
    } else {
        log('Rendering type "' + renderingType + '" not implemented.')
    }
};

var renderTableResourceList = function (resourceList, element) {

    var theadRow = $('<tr>');
    var columns = resourceList.renderingInfo.columns;
    $.each(columns, function (i, column) {
        theadRow.append($('<td>').html(column.name));
    });

    var thead = $('<thead>').append(theadRow);
    var tbody = $('<tbody>');

    $.each(resourceList.list, function (i, book) {
        var tbodyRow = $('<tr>').appendTo(tbody);
        $.each(columns, function (i, column) {
            tbodyRow.append($('<td>').html(book[column.key]));
        });
    });

    var table = $('<table>').addClass('datatable').append(thead).append(tbody);

    $(element).append(table)
};