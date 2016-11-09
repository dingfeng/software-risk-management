/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["../util", "../view/BaseView", "../model/AsideCollection", "text!../template/aside.tpl", "text!../css/aside.css"],
    function (Util, BaseView, AsideCollection, AsideTpl, AsideCss) {
        var AsideView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                AsideView.__super__.initialize.call(this);
            },
            title: '',
            el: 'aside#aside',
            events: {
                "click .item": "goto"
            },
            model: new AsideCollection,
            tpl: AsideTpl,
            css: AsideCss,
            render: function () {
                AsideView.__super__.render.call(this);
                var that = this;
                // $.ajax({
                //     type: "POST",
                //     url: "/login/username",
                //     data: "sessionid=" + Util.getSessionId(),
                //     // async: false,
                //     error: function () {
                //         window.location.href = "#login";
                //     },
                //     success: function (data) {
                //         if (data) {
                //             that.model.set({
                //                 username: data,
                //             });
                //         } else {
                //             window.location.href = "#login";
                //         }
                //     }
                // });
            },
            goto: function (event) {
                alert(JSON.stringify(event));
                // window.location.href = "#login";
            }
        });
        return AsideView;
    });