/**
 * Created by 邹玉鑫 on 2016/11/11.
 */
define(["common/view/BaseView", "../model/DetailProjectModel", "text!../template/detailProject.tpl", "text!../css/detailProject.css", "common/util"],
    function (BaseView, DetailProjectModel, DetailProjectTpl, DetailProjectCss, Util) {
        var DetailProjectView = BaseView.extend({
            initialize: function () {
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
                //     data: "sessionid=" + Util.getSessionId(),
                //     // error: function () {
                //     //     window.location.href = "#login";
                //     // },
                //     success: function (data) {
                //         var obj =eval("("+data+")");
                //         _.each(obj.data, function (value) {
                //             that.model.add(new SearchProjectModel(value));
                //         });
                //     }
                // });

                // this.model.add(new SearchProjectModel({
                //     id: '编号1',
                //     name: '项目名1',
                //     description: '描述1',
                //     createdBy: '创建人1',
                //     createdAt: '创建时间1',
                // }));
            },
            saveBtn: function () {

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