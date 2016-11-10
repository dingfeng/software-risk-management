/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["../util", "../view/BaseView", "../model/AsideCollection", "text!../template/aside.tpl", "text!../css/aside.css"],
    function (Util, BaseView, AsideCollection, AsideTpl, AsideCss) {
        var AsideView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                AsideView.__super__.initialize.call(this);
                this.model.on("add", this.setGotoEvent, this);
            },
            title: '',
            el: 'aside#aside',
            // events: {
            //     "click #inaside .item": "goto"
            // },
            model: new AsideCollection,
            tpl: AsideTpl,
            css: AsideCss,
            setGotoEvent: function () {
                var that = this;
                $("#inaside .item").off("click");
                $("#inaside .item").on("click", function () {
                    for (var i = 0; i < that.model.length; i++) {
                        var value = that.model.get(i);
                        if (value.get("id") == $(this).attr("name")) {
                            window.location.href = value.get("url");
                        }
                    }
                });
            }
        });
        return AsideView;
    });