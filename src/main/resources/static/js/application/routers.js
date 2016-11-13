define(["backbone",
        "common/view/MainView",
        "./module/admin/view/AdminAddUserView",
        "./module/admin/view/AdminSearchProjectView",
        "./module/admin/view/AdminSearchUserView",
        "./module/authority/view/LoginView",
        "./module/ordinary/view/CreateProjectView",
        "./module/ordinary/view/SearchProjectView",
        "./module/ordinary/view/SearchRiskView",
        "./module/ordinary/view/SearchMyJoinProjectView",
        "./module/ordinary/view/SearchMyHandlerRiskView",
        "./module/ordinary/view/DetailProjectView",
        "./module/ordinary/view/DetailRiskView",
        "./module/ordinary/view/CreateRiskView",
        "./module/ordinary/view/DetailProjectAddPersonView",
        "common/model/AsideModel",
        "common/util"],
    function (Backbone, MainView, AdminAddUserView,AdminSearchProjectView,AdminSearchUserView,LoginView, CreateProjectView, SearchProjectView, SearchRiskView, SearchMyJoinProjectView, SearchMyHandlerRiskView, DetailProjectView, DetailRiskView,CreateRiskView, DetailProjectAddPersonView, AsideModel, Util) {
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
                "admin/searchProject":"adminSearchProject",
                "admin/searchUser":"adminSearchUser",
                "ordinary/createProject": "createProject",
                "ordinary/searchMyJoinProjectView": "searchMyJoinProjectView",
                "ordinary/searchMyHandlerRiskView": "searchMyHandlerRiskView",
                "ordinary/searchRisk": "searchRisk",
                "ordinary/searchProject": "searchProject",
                "ordinary/detailProject/:id": "detailProject",
                "ordinary/detailSingleRisk/:id": "detailSingleRisk",
                "ordinary/detailProject/:projectId/addPerson": "projectAddPerson",
                "ordinary/detailProject/:projectId/addRisk": "projectAddRisk",
                "ordinary/detailProject/:projectId/detailRisk/:riskId": "detailRisk",

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
                            that.navigate('ordinary/createProject', {trigger: true, replace: true});
                        } else {
                            that.navigate('login', {trigger: true, replace: true});
                        }
                    }
                });
            },

            detailSingleRisk:function (riskId) {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new DetailRiskView(riskId);
                mainView.contentView.riskId = riskId;
            },

            createOrdinaryAside: function (activeId, asideCollections) {
                asideCollections.reset();
                asideCollections.add(new AsideModel({
                    id: '0',
                    name: '创建项目',
                    url: '#ordinary/createProject',
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
                mainView.contentView = new DetailProjectView(id);
                mainView.contentView.projectId = id;
            },

            detailRisk: function(projectId, riskId) {
                var mainView = new MainView();
                this.createOrdinaryAside(1, mainView.asideView.model);
                mainView.contentView = new DetailRiskView(riskId);
                mainView.contentView.projectId = projectId;
                mainView.contentView.riskId = riskId;
            },

            adminAddUser: function () {
                var mainView = new MainView();

                mainView.asideView.model.reset();

                mainView.asideView.model.add(new AsideModel({
                             id: '0',
                             name: '创建用户',
                             url: '#admin/add',
                             active: true,
                }));
                mainView.asideView.model.add(new AsideModel({
                              id: '2',
                              name: '系统用户',
                              url: '#admin/searchUser',
                              active: false,
                }));
                mainView.asideView.model.add(new AsideModel({
                              id: '1',
                              name: '软件项目',
                              url: '#admin/searchProject',
                              active: false,
                }));
                mainView.contentView = new AdminAddUserView();
            },
            createProject: function () {
                var mainView = new MainView();
                this.createOrdinaryAside(0, mainView.asideView.model);
                mainView.contentView = new CreateProjectView();
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
                mainView.contentView = new CreateRiskView();
                mainView.contentView.projectId = projectId;
            },


            adminSearchUser: function()
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
                            active: false,
                 }));
                 mainView.contentView = new AdminSearchUserView();
            },

            adminSearchProject: function ()
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
                        active: false,
                 }));
                  mainView.asideView.model.add(new AsideModel({
                       id: '1',
                       name: '软件项目',
                       url: '#admin/searchProject',
                       active: true,
                  }));
                   mainView.contentView = new  AdminSearchProjectView();
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
