"use strict";
require.config({
    baseUrl: "./js",
    paths: {
        jquery: "lib/jquery-3.1.1.min",
        backbone: "lib/backbone-min",
        underscore: "lib/underscore-min",
    },
});
require(["application/routers"], function(Routers) {
	new Routers();
});

// require(["jquery", "helper/util", "app/routers", "lib/backbone"], function($, util, Routers, Backbone) {
// 	alert("aaa");
//     var router = new Routers();
//     alert(router);
//     Backbone.history.start({pushState : true});
// });