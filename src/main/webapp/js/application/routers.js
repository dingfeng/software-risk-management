define(["backbone", "application/model/team"], function(Backbone, Team) {
   var Routers = Backbone.Router.extend({
    	initialize: function () {
			console.log("Route initialize");
			Backbone.history.start({pushState : true});
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
		   var team = new Team({
			   name : "name1",
			   id: '11',
		   });
		   team.set({
			   name : "name2"
		   });
		   team.fetch();
	   },
	   
	   getTeams: function() {
		   // List all teams 
		   alert("getTeams");
	   },
	   getTeamsCountry: function(country) {
		   // Get list of teams for specific country
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
