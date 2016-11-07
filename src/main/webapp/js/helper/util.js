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
        },
        getSessionId: function () {
            var c_name = 'JSESSIONID';
            if (document.cookie.length > 0) {
                var c_start = document.cookie.indexOf(c_name + "=");
                if (c_start != -1) {
                    c_start = c_start + c_name.length + 1;
                    var c_end = document.cookie.indexOf(";", c_start);
                    if (c_end == -1) c_end = document.cookie.length;
                    return unescape(document.cookie.substring(c_start, c_end));
                }
            }
            return '';
        },
    }
    return Util;
});