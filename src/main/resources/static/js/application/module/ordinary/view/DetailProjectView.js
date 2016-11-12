/**
 * Created by 邹玉鑫 on 2016/11/11.
 */
define(["common/view/BaseView", "../model/DetailProjectModel", "../model/DetailRiskModel", "../model/SearchRiskCollection", "text!../template/detailProject.tpl", "text!../css/detailProject.css", "common/util"],
    function (BaseView, DetailProjectModel, DetailRiskModel, SearchRiskCollection, DetailProjectTpl, DetailProjectCss, Util) {
        var DetailProjectView = BaseView.extend({
            initialize: function () {
                this.model.get("riskList").on("add", this.setTpl, this);
                DetailProjectView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看项目详细',
            model: new DetailProjectModel,
            tpl: DetailProjectTpl,
            css: DetailProjectCss,
            projectId: '',
            events: {
                "click button.saveBtn": "saveBtn",
                "click button.addPersonBtn": "addPersonBtn",
                "click button.addRiskBtn": "addRiskBtn",
            },
            render: function () {
                DetailProjectView.__super__.render.call(this);
                var that = this;
                // $.ajax({
                //     type: "POST",
                //     url: "/user/getProjectCreate",
                //     data: "projectId=" + this.projectId,
                //     // error: function () {
                //     //     window.location.href = "#login";
                //     // },
                //     success: function (data) {
                //         var obj = eval("(" + data + ")");
                //         that.model.set(obj.data);
                //
                //     }
                // });

                this.model.set({
                    id: '编号',
                    name: '项目名',
                    description: '描述',
                    joinedNames: 'asd',
                    createdBy: '创建人',
                    createdAt: '创建时间',
                    updatedAt: '编辑时间',
                });
                this.model.get("riskList").add(new DetailRiskModel({
                    id: '编号',
                    title: '标题',
                    possibility: '可能性',
                    status: '状态',
                    influence: '影响',
                    trigger: '触发器',
                    description: '文本描述',
                    author: '创建者',
                    handler: '处理者',
                    project: '所属项目',
                    createdAt: '创建时间',
                    updatedAt: '编辑时间',
                }));
            },
            saveBtn: function () {
                var data = {};
                $("#detailProject form").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: _.pick(this.model.attributes, 'id', 'name', 'description'),
                    // async: false,
                    error: function () {
                        alert("服务器错误");
                    },
                    success: function (data) {
                        var obj = eval("(" + data + ")");
                        if (obj.isSuccess) {
                            alert("保存成功！");
                        } else {
                            alert(obj.errMsg);
                        }
                    }
                });
            },
            addPersonBtn: function () {
               window.location.href = "#ordinary/detailProject/" + this.projectId + "/addPerson";
            },
            addRiskBtn: function () {
                window.location.href = "#ordinary/detailProject/" + this.projectId + "/addRisk";
            },
        });
        return DetailProjectView;
    });