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
            title: '创建用户',
            model: new AddUserModel,
            tpl: AddUserTpl,
            css: AddUserCss,
            events: {
                "click .submit": "create"
            },
            create: function () {
                var data = {};
                $("form.addUser").serializeArray().map(function (x) {
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
                    alert("创建成功！");
                });
            },
            verify: function (data, callback) {
                if (!data.username || !data.password) {
                    callback('用户名或密码不能为空');
                    return;
                }
                $.ajax({
                    type: "POST",
                    url: "/admin/add",
                    data: _.pick(data, 'username', 'password', 'role'),
                    // async: false,
                    error: function () {
                        callback('服务器验证错误');
                    },
                    success: function (data) {
                        if (data) {
                            callback('');
                        } else {
                            callback('创建失败！');
                        }
                    }
                });
            }
        });
        return AddUserView;
    });