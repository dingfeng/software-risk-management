define(["backbone"], function(Backbone) {
   var Routers = Backbone.Router.extend({
    	initialize: function () {
			Backbone.history.start();
            console.log("Route initialize");
        },
	   // Hash maps for routes
	   routes: {
		  "" : "index",
		  "teams" : "getTeams",
		  "teams/:country" : "getTeamsCountry",
		  "teams/:country/:name" : "getTeam",
		  "*error" : "fourOfour"
	   },
	   
	   index: function(){
		   // Homepage
		   alert("hh");
	   },
	   
	   getTeams: function() {
		   // List all teams 
		   alert("getTeams");
	   },
	   getTeamsCountry: function(country) {
		   // Get list of teams for specific country
		   alert(country);
	   },
	   getTeam: function(country, name) {
		   // Get the teams for a specific country and with a specific name
		   alert(country + "," + name);
	   },
	   fourOfour: function(error) {
		   // 404 page
		   alert("404");
	   }
	});
	return Routers;
});
