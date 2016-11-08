/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["common/view/BaseView", "../model/AddUserModel", "text!../template/addUser.tpl", "text!../css/addUser.css"],
    function (BaseView, AddUserModel, AddUserTpl, AddUserCss) {
        var AddUserView = BaseView.extend({
            initialize: function () {
                AddUserView.__super__.initialize.call(this);
            },
            el: '#content',
            sync: false,
            title: '创建用户',
            model: new AddUserModel,
            tpl: AddUserTpl,
            css: AddUserCss,
            events: {},
        });
        return AddUserView;
    });