"use strict";
require.config({
    baseUrl: "./js",
    paths: {
        jquery: "lib/jquery-3.1.1",
        backbone: "lib/backbone",
        underscore: "lib/underscore",
    },
});
require(["application/routers"], function(Routers) {
	new Routers();
});