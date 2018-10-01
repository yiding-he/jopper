var Jopper = function (element) {

    var log = function (msg) {
        if (window.console) {
            window.console.log(msg);
        }
    };

    this.meta = null;
    this.element = element;
    this.renderer = null;

    this.getMeta = function () {
        return this.meta;
    };

    this.getElement = function () {
        return this.element;
    };

    this.init = function () {
        this.initJopperElement();
    };

    ////////////////////////////////////////////////////////////////////////////////

    this.triggerOperation = function (resourcePath, resource, operation) {

    };

    this.initJopperElement = function () {
        var text = element.attr('data-jopper');
        var definition = JSON.parse(text);
        var resourcePath = definition.resource;
        var jopper = this;

        $.get(resourcePath + "/meta", {}, function (metaData) {
            jopper.meta = metaData;

            var renderingType = metaData.renderingType;
            if (!Jopper.renderers[renderingType]) {
                log('Rendering type "' + renderingType + '" not implemented.');
                return;
            }

            jopper.renderer = new Jopper.renderers[renderingType](jopper);

            jopper.renderer.renderMeta();
            if (jopper.meta.autoQuery) {
                $.get(resourcePath + "/list", {}, function (data) {
                    jopper.renderElementData(resourcePath, data);
                });
            }
        });
    };

    this.renderElementData = function (resourcePath, data) {
        if (!this.renderer) {
            return;
        }

        this.renderer.renderData(resourcePath, data);
    };
};

// each renderer should implement two
// methods: renderMeta() and renderData()
Jopper.renderers = {};

Jopper.getInstance = function (element) {
    var jqElement = $(element);

    var jopper = jqElement.data('jopper-instance');
    if (!jopper) {
        jopper = new Jopper(jqElement);
        jqElement.data('jopper-instance', jopper);
    }

    return jopper;
};

Jopper.initJopper = function () {
    $('[data-jopper]').each(function () {
        Jopper.getInstance(this).init();
    });
};