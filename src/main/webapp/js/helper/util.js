define(["underscore"], function (_) {
    var Util = {
        extend: function (parent, protoProps) {
            var ctor = function () {
            };
            var child;
            if (protoProps && protoProps.hasOwnProperty('constructor')) {
                child = protoProps.constructor;
            } else {
                child = function () {
                    parent.apply(this, arguments);
                };
            }
            _.extend(child, parent);
            ctor.prototype = parent.prototype;
            child.prototype = new ctor();
            if (protoProps)
                _.extend(child.prototype, protoProps);
            child.prototype.constructor = child;
            child.__super__ = parent.prototype;
            return child;
        }
    }
    return Util;
});