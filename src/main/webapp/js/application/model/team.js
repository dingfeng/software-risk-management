/**
 * Created by 邹玉鑫 on 2016/10/31.
 */
define(["backbone"], function(Backbone) {
    var Team = Backbone.Model.extend({
        initialize : function(){
            this.on("change", this.changeEvent);
        },
        defaults : {
            urlRoot : '/specialTeams',
            name : 'unknown',
            url : '/specialTeams',
        },
        changeEvent: function(){
            alert("changed");
        }
    });
    return Team;
});