define(["common/view/BaseView", "../model/LoginModel", "text!../template/login.tpl", "text!../css/login.css"],
    function (BaseView, LoginModel, LoginTpl, LoginCss) {
        var LoginView = BaseView.extend({
            initialize: function () {
                LoginView.__super__.initialize.call(this);
            },
            title: '登录',
            el: 'body',
            model: new LoginModel,
            events: {
                "click #login": "login"
            },
            tpl: LoginTpl,
            css: LoginCss,
            login: function (e) {
                var data = {};
                $("form.login").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                var that = this;
                this.verify(data, function (errorMsg) {
                    if (errorMsg) {
                        that.model.set({
                            errorMsg: errorMsg,
                        });
                        return;
                    } else {
                        that.model.set({
                            errorMsg: '',
                        });
                    }

                    window.location.href = '#main';
                });
            },
            verify: function (data, callback) {
                if (!data.username || !data.password) {
                    callback('用户名或密码不能为空');
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/login/verify",
                    data: _.pick(data, 'username', 'password'),
                    // async: false,
                    error: function () {
                        callback('服务器验证错误');
                    },
                    success: function (data) {
                        if (data) {
                            callback('');
                        } else {
                            callback('用户名或密码错误');
                        }
                    }
                });
            }
        });
        return LoginView;
    });