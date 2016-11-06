define(["backbone", "application/model/team", "./module/authority/view/LoginView"], function (Backbone, Team, LoginView) {
    var Routers = Backbone.Router.extend({
        initialize: function () {
            console.log("Route initialize");
            // Backbone.history.start({pushState: true});
            Backbone.history.start();
        },
        // Hash maps for routes
        routes: {
            "": "index",
            "login": "login",
            "teams": "getTeams",
            "teams/:country": "getTeamsCountry",
            "teams/:country/:name": "getTeam",
            "*error": "fourOfour"
        },

        index: function () {
            this.navigate('login', {trigger: true, replace: true});
        },

        login: function () {
            new LoginView();
        },

        getTeams: function () {
            // List all teams
            alert("getTeams");
        },
        getTeamsCountry: function (country) {
            // Get list of teams for specific country
        },
        getTeam: function (country, name) {
            // Get the teams for a specific country and with a specific name
            alert(country + "," + name);
        },
        fourOfour: function (error) {
            // 404 page
            alert("404");
        }
    });
    return Routers;
});
