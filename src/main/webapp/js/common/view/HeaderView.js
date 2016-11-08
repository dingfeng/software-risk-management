/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
define(["../util", "../view/BaseView", "../model/HeaderModel", "text!../template/header.tpl", "text!../css/header.css"],
    function (Util, BaseView, HeaderModel, HeaderTpl, HeaderCss) {
        var HeaderView = BaseView.extend({
            initialize: function () {
                HeaderView.__super__.initialize.call(this);
            },
            sync: true,
            title: '',
            el: 'header#header',
            events: {
                "click #logout": "logout"
            },
            model: new HeaderModel,
            tpl: HeaderTpl,
            css: HeaderCss,
            render: function () {
                HeaderView.__super__.render.call(this);
                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/login/username",
                    data: "sessionid=" + Util.getSessionId(),
                    // async: false,
                    error: function () {
                        window.location.href = "#login";
                    },
                    success: function (data) {
                        if (data) {
                            that.model.set({
                                username: data,
                            });
                        } else {
                            window.location.href = "#login";
                        }
                    }
                });
            },
            logout: function () {
                window.location.href = "#login";
            }
        });
        return HeaderView;
    });