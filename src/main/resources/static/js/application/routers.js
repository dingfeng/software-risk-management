define(["backbone", "common/view/MainView", "./module/authority/view/LoginView", "./module/admin/view/AddUserView", "./module/admin/view/SearchProjectView", "./module/admin/view/SearchRiskView", "./module/admin/view/SearchMyJoinProjectView","./module/admin/view/SearchMyHandlerRiskView","./module/admin/view/DetailProjectView", "./module/admin/view/DetailRiskView", "./module/admin/view/DetailProjectAddPersonView","common/model/AsideModel", "common/util"],
    function (Backbone, MainView, LoginView, AdminAddUserView, SearchProjectView,SearchRiskView, SearchMyJoinProjectView,SearchMyHandlerRiskView,DetailProjectView, DetailRiskView, DetailProjectAddPersonView,AsideModel, Util) {
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


                "admin/searchMyJoinProjectView":"searchMyJoinProjectView",

                "admin/searchMyHandlerRiskView":"searchMyHandlerRiskView",

                "admin/searchRisk":"searchRisk",

                "admin/searchProject":"searchProject",

                "admin/detailProject/:id": "detailProject",

                "admin/detailProject/:projectId/addPerson": "projectAddPerson",

                "admin/detailProject/:projectId/addRisk": "projectAddRisk",

                "manager": "adminAddUser",


                "ordinary": "adminAddUser",

                "teams": "getTeams",
                "teams/:country": "getTeamsCountry",
                "teams/:country/:name": "getTeam",
                "*error": "fourOfour"
            },

            index: function () {
                // new AdminAddUserView();
                // this.navigate('login', {trigger: true, replace: true});
                this.navigate('admin/detailProject/1', {trigger: true, replace: true});
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

            searchMyHandlerRiskView:function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: true,
                }));

                mainView.contentView = new  SearchMyHandlerRiskView();
            },

            searchMyJoinProjectView:function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));
                mainView.contentView = new  SearchMyJoinProjectView();
            },

            searchRisk: function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));
                mainView.contentView = new  SearchRiskView();
            },

            searchProject: function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));

                mainView.contentView = new SearchProjectView();
            },

            detailProject: function (id) {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));

                mainView.contentView = new DetailProjectView();
                mainView.contentView.projectId = id;

            },

            adminAddUser: function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));
                mainView.contentView = new AdminAddUserView();
            },

            projectAddPerson: function (projectId) {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));
                mainView.contentView = new DetailProjectAddPersonView();
                mainView.contentView.projectId = projectId;
            },

            projectAddRisk: function (projectId) {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#admin/add',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '1',
                    name: '我创建的项目',
                    url: '#admin/searchProject',
                    active: true,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '2',
                    name: '我加入的项目',
                    url: '#admin/searchMyJoinProjectView',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '3',
                    name: '我创建的风险',
                    url: '#admin/searchRisk',
                    active: false,
                }));

                mainView.asideView.model.add(new AsideModel({
                    id: '4',
                    name: '我处理的风险',
                    url: '#admin/searchMyHandlerRiskView',
                    active: false,
                }));
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
