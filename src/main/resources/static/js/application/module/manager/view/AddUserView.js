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
            title: '创建项目',
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
                //if (!data.username || !data.password) {
                //    callback('用户名或密码不能为空');
                //    return;
                //}
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: _.pick(data, 'projectName', 'description'),
                    // async: false,
                    error: function () {
                        alert("error")
                        callback('服务器验证错误');
                    },
                    success: function (data) {

                        var obj =eval("("+data+")");

                        if (obj.isSuccess) {
                            callback('');
                        } else {
                            callback(obj.errMsg);
                        }
                    }
                });
            }
        });
        return AddUserView;
    });