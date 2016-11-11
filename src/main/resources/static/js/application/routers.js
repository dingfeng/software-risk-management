define(["backbone", "common/view/MainView", "./module/authority/view/LoginView", "./module/admin/view/AddUserView", "./module/admin/view/SearchProjectView", "common/model/AsideModel", "common/util"],
    function (Backbone, MainView, LoginView, AdminAddUserView, SearchProjectView, AsideModel, Util) {
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
                "main": "main",
                "admin/add": "adminAddUser",

                "admin/searchProject":"searchProject",

                "admin/searchUser":"searchUser",

                "manager": "adminAddUser",


                "ordinary": "adminAddUser",

                "teams": "getTeams",
                "teams/:country": "getTeamsCountry",
                "teams/:country/:name": "getTeam",
                "*error": "fourOfour"
            },

            index: function () {
                // new AdminAddUserView();
                this.navigate('login', {trigger: true, replace: true});
            },

            login: function () {
                new LoginView();
            },

            main: function () {
                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/login/role",
                    data: "sessionid=" + Util.getSessionId(),
                    // async: false,
                    error: function () {
                        that.navigate('login', {trigger: true, replace: true});
                    },
                    success: function (data) {
                        that.navigate('admin/add', {trigger: true, replace: true});
                    }
                });
            },

            searchProject: function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建用户',
                    url: '#admin/add',
                    active: false,
                }));
                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '系统用户',
                    url: '#admin/searchUser',
                    active: true,
                }));
                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '软件项目',
                    url: '#admin/searchProject',
                    active: true,
                }));
                mainView.contentView = new  SearchProjectView();
            },

            adminAddUser: function () {
                 var mainView = new MainView();

                 mainView.asideView.model.reset();

                 mainView.asideView.model.add(new AsideModel({
                         id: '0',
                         name: '创建用户',
                         url: '#admin/add',
                         active: false,
                 }));
                 mainView.asideView.model.add(new AsideModel({
                           id: '2',
                           name: '系统用户',
                           url: '#admin/searchUser',
                           active: true,
                 }));
                 mainView.asideView.model.add(new AsideModel({
                           id: '1',
                           name: '软件项目',
                           url: '#admin/searchProject',
                           active: true,
                 }));
                mainView.contentView = new AddUserView();
            },
            searchUser : function()
            {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                           id: '0',
                           name: '创建用户',
                           url: '#admin/add',
                           active: false,
                }));
                mainView.asideView.model.add(new AsideModel({
                             id: '2',
                             name: '系统用户',
                             url: '#admin/searchUser',
                             active: true,
                }));
                 mainView.asideView.model.add(new AsideModel({
                           id: '1',
                           name: '软件项目',
                           url: '#admin/searchProject',
                           active: true,
                 }));
                mainView.contentView = new SearchUserView();
            }
            ,
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
