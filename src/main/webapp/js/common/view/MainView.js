/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
define(["../view/BaseView", "../view/HeaderView", "../view/FooterView", "../view/AsideView", "text!../template/main.tpl", "text!../css/main.css"],
    function (BaseView, HeaderView, FooterView, AsideView, MainTpl, MainCss) {
        var MainView = BaseView.extend({
            initialize: function () {
                MainView.__super__.initialize.call(this);
            },
            sync: true,
            title: '',
            el: 'body',
            tpl: MainTpl,
            css: MainCss,
            headerView: null,
            footerView: null,
            asideView: null,
            render: function () {
                MainView.__super__.render.call(this);
                this.headerView = new HeaderView();
                this.footerView = new FooterView();
                this.asideView = new AsideView();
            },
        });
        return MainView;
    });