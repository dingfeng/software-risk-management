/**
 * Created by 邹玉鑫 on 2016/11/12.
 */
define(["common/view/BaseView", "../model/CreateProjectModel", "text!../template/createProject.tpl", "text!../css/createProject.css"],
    function (BaseView, CreateProjectModel, CreateProjectTpl, CreateProjectCss) {
        var CreateProjectView = BaseView.extend({
            initialize: function () {
                CreateProjectView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '创建项目',
            model: new CreateProjectModel,
            tpl: CreateProjectTpl,
            css: CreateProjectCss,
            events: {
                "click .submit": "create"
            },
            create: function () {
                var data = {};
                $("form.createProject").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: data, //_.pick(data, 'projectName', 'description'),
                    // async: false,
                    error: function () {
                        alert("服务器验证错误");
                    },
                    success: function (data) {
                        var obj = eval("(" + data + ")");
                        if (obj.isSuccess) {
                            alert("创建成功！");
                        } else {
                            alert(obj.errMsg);
                        }
                    }
                });
            },
        });
        return CreateProjectView;
    });