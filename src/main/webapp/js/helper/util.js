define(["underscore"], function (_) {
    var Util = {
        extend: function (superclass, destination) {
            var child = {};
            child.superclass = superclass;
            _.extend(child, superclass);
            _.extend(child, destination || {});

            // var child = destination || {};
            // child.superclass = superclass;
            // _.extend(superclass, child);
            return child;
        }
    }
    return Util;
});