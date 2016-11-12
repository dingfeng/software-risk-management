define(["backbone", "common/view/MainView", "./module/authority/view/LoginView", "./module/admin/view/AddUserView", "./module/ordinary/view/SearchProjectView", "./module/ordinary/view/SearchRiskView", "./module/ordinary/view/SearchMyJoinProjectView", "./module/ordinary/view/SearchMyHandlerRiskView", "./module/ordinary/view/DetailProjectView", "./module/ordinary/view/DetailRiskView", "./module/ordinary/view/DetailProjectAddPersonView", "common/model/AsideModel", "common/util"],
    function (Backbone, MainView, LoginView, AdminAddUserView, SearchProjectView, SearchRiskView, SearchMyJoinProjectView, SearchMyHandlerRiskView, DetailProjectView, DetailRiskView, DetailProjectAddPersonView, AsideModel, Util) {
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

                "ordinary/addProject": "adminAddUser",
                "ordinary/searchMyJoinProjectView": "searchMyJoinProjectView",
                "ordinary/searchMyHandlerRiskView": "searchMyHandlerRiskView",
                "ordinary/searchRisk": "searchRisk",
                "ordinary/searchProject": "searchProject",
                "ordinary/detailProject/:id": "detailProject",
                "ordinary/detailProject/:projectId/addPerson": "projectAddPerson",
                "ordinary/detailProject/:projectId/addRisk": "projectAddRisk",

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
                        if (data === 'admin') {
                            that.navigate('admin/add', {trigger: true, replace: true});
                        } else if (data === 'ordinary') {
                            that.navigate('ordinary', {trigger: true, replace: true});
                        } else {
                            that.navigate('login', {trigger: true, replace: true});
                        }
                    }
                });
            },

            createOrdinaryAside: function (activeId, asideCollections) {
                asideCollections.reset();
                asideCollections.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#ordinary/addProject',
                    active: activeId == 0 ? true : false,
                }));

                asideCollections.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#ordinary/searchProject',
                    active: activeId == 1 ? true : false,
                }));

                asideCollections.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#ordinary/searchMyJoinProjectView',
                    active: activeId == 2 ? true : false,
                }));

                asideCollections.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#ordinary/searchRisk',
                    active: activeId == 3 ? true : false,
                }));

                asideCollections.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#ordinary/searchMyHandlerRiskView',
                    active: activeId == 4 ? true : false,
                }));
            },

            searchMyHandlerRiskView: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(4, mainView.asideView.model);
                mainView.contentView = new SearchMyHandlerRiskView();
            },

            searchMyJoinProjectView: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(2, mainView.asideView.model);
                mainView.contentView = new SearchMyJoinProjectView();
            },

            searchRisk: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(3, mainView.asideView.model);
                mainView.contentView = new SearchRiskView();
            },

            searchProject: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new SearchProjectView();
            },

            detailProject: function (id) {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new DetailProjectView();
                mainView.contentView.projectId = id;
            },

            adminAddUser: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(0, mainView.asideView.model);
                mainView.contentView = new AdminAddUserView();
            },

            projectAddPerson: function (projectId) {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new DetailProjectAddPersonView();
                mainView.contentView.projectId = projectId;
            },

            projectAddRisk: function (projectId) {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new DetailRiskView();
                mainView.contentView.projectId = projectId;
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
