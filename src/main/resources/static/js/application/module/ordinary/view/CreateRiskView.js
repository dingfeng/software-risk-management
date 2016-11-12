/**
 * Created by 邹玉鑫 on 2016/11/12.
 */
define(["common/view/BaseView", "../model/DetailRiskModel", "text!../template/createRisk.tpl", "text!../css/createRisk.css", "common/util"],
    function (BaseView, DetailRiskModel, CreateRiskTpl, CreateRiskCss, Util) {
        var CreateRiskView = BaseView.extend({
            initialize: function () {
                CreateRiskView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '创建新风险',
            model: new DetailRiskModel,
            tpl: CreateRiskTpl,
            css: CreateRiskCss,
            projectId: '',
            events: {
                "click button.ok": "ok",
                "click button.cancel": "cancel",
            },
            render: function () {
                CreateRiskView.__super__.render.call(this);
                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/user/getProjectCreate",
                    data: "projectId=" + that.projectId,
                    // error: function () {
                    //     window.location.href = "#login";
                    // },
                    success: function (data) {
                        var obj = eval("(" + data + ")");
                        _.each(obj.data, function (value) {
                            that.model.add(new SearchProjectModel(value));
                        });
                    }
                });

                // this.model.add(new SearchProjectModel({
                //     id: '编号1',
                //     name: '项目名1',
                //     description: '描述1',
                //     createdBy: '创建人1',
                //     createdAt: '创建时间1',
                // }));
            },
            ok: function () {
                var data = {};
                $("form.createRisk").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                data.projectId = this.projectId;
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: data, //_.pick(data, 'projectName', 'description'),
                    // async: false,
                    error: function () {
                        alert("服务器错误");
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
            cancel: function () {
                window.location.href = "#ordinary/detailProject/" + this.projectId;
            },
        });
        return CreateRiskView;
    });