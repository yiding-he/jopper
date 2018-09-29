$(function(){
    initJopper();
});

var initJopper = function () {
    $('[data-jopper]').each(function() {
        initJopperElement($(this));
    });
};

var initJopperElement = function (element) {
    var text = element.attr('data-jopper');
    var definition = JSON.parse(text);
    var resource = definition.resource;

    var ul = $('<ul></ul>');
    $.get(resource + "/list", {}, function (data) {
        $.each(data, function(i, book) {
            ul.append($('<li></li>').html(book.name));
        });
    });

    element.append(ul);
};