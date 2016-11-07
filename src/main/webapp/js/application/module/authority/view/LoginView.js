define(["helper/util", "helper/view/BaseView", "../model/LoginModel", "text!../template/login.tpl", "text!../css/login.css"],
    function (Util, BaseView, LoginModel, LoginTpl, LoginCss) {
        var LoginView = Util.extend(BaseView, {
            initialize: function () {
                this.render();
                this.model.on("change", this.render, this);
                LoginView.superclass.initialize.call(this);
            },
            title: '登录',
            el: 'body',
            model: new LoginModel,
            events: {
                "click #login": "login"
            },
            tpl: LoginTpl,
            css: LoginCss,
            render: function () {
                $('title').html(this.title);
                $('head').append($("<style>" + this.css + "</style>"))
                $(this.el).html(_.template(this.tpl)(this.model.toJSON()));
            },
            login: function (e) {
                var data = {};
                $("form.login").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                var errorMsg = this.verify(data);
                if (errorMsg) {
                    this.model.set({
                        errorMsg: errorMsg,
                    });
                    return;
                } else {
                    this.model.set({
                        errorMsg: '',
                    });
                }
                // $.ajax({
                //     cache: true,
                //     type: "POST",
                //     url: ajaxCallUrl,
                //     data: $('#yourformid').serialize(),// 你的formid
                //     async: false,
                //     error: function (request) {
                //         alert("Connection error");
                //     },
                //     success: function (data) {
                //         $("#commonLayout_appcreshi").parent().html(data);
                //     }
                // });
                window.location.href = '#teams';
            },
            verify: function (data) {
                if (!data.username || !data.password) {
                    return '用户名或密码不能为空';
                }
                return "";
            }
        });

        // var LoginView = BaseView.extend({
        //     initialize: function () {
        //         this.render();
        //         this.model.on("change", this.render, this);
        //         // LoginView.prototype.initialize.call(this);
        //     },
        //     title: '登录',
        //     el: 'body',
        //     model: new LoginModel,
        //     events: {
        //         "click #login": "login"
        //     },
        //     tpl: LoginTpl,
        //     css: LoginCss,
        //     render: function () {
        //         $('title').html(this.title);
        //         $('head').append($("<style>" + this.css + "</style>"))
        //         $(this.el).html(_.template(this.tpl)(this.model.toJSON()));
        //     },
        //     login: function (e) {
        //         var data = {};
        //         $("form.login").serializeArray().map(function (x) {
        //             data[x.name] = x.value;
        //         });
        //         this.model.set(data);
        //         var errorMsg = this.verify(data);
        //         if (errorMsg) {
        //             this.model.set({
        //                 errorMsg: errorMsg,
        //             });
        //             return;
        //         } else {
        //             this.model.set({
        //                 errorMsg: '',
        //             });
        //         }
        //         // $.ajax({
        //         //     cache: true,
        //         //     type: "POST",
        //         //     url: ajaxCallUrl,
        //         //     data: $('#yourformid').serialize(),// 你的formid
        //         //     async: false,
        //         //     error: function (request) {
        //         //         alert("Connection error");
        //         //     },
        //         //     success: function (data) {
        //         //         $("#commonLayout_appcreshi").parent().html(data);
        //         //     }
        //         // });
        //         window.location.href = '#teams';
        //     },
        //     verify: function (data) {
        //         if (!data.username || !data.password) {
        //             return '用户名或密码不能为空';
        //         }
        //         return "";
        //     }
        // });
        return LoginView;
    });