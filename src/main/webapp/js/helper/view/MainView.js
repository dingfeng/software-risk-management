/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
define(["helper/view/BaseView", "helper/view/HeaderView", "helper/view/FooterView", "text!../template/main.tpl", "text!../css/main.css"],
    function (BaseView, HeaderView, FooterView, MainTpl, MainCss) {
        var MainView = BaseView.extend({
            initialize: function () {
                MainView.__super__.initialize.call(this);
            },
            sync: true,
            title: '',
            el: 'body',
            tpl: MainTpl,
            css: MainCss,
            render: function () {
                MainView.__super__.render.call(this);
                new HeaderView();
                new FooterView();
            },
        });
        return MainView;
    });