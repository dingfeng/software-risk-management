/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
define(["helper/view/BaseView", "text!../template/footer.tpl", "text!../css/footer.css"],
    function (BaseView, FooterTpl, FooterCss) {
        var FooterView = BaseView.extend({
            initialize: function () {
                FooterView.__super__.initialize.call(this);
            },
            sync: true,
            title: '',
            el: 'footer#footer',
            tpl: FooterTpl,
            css: FooterCss,
        });
        return FooterView;
    });