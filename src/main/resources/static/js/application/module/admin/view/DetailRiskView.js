define(["common/view/BaseView", "../model/DetailRiskModel", "text!../template/detailRisk.tpl", "text!../css/detailRisk.css", "common/util"],
    function (BaseView, DetailRiskModel, DetailRiskTpl, DetailRiskCss, Util) {
        var DetailRiskView = BaseView.extend({
            initialize: function () {
                DetailRiskView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '创建新风险',
            model: new DetailRiskModel,
            tpl: DetailRiskTpl,
            css: DetailRiskCss,
            projectId: '',
            events: {
                "click button.saveBtn": "saveBtn",
                "click button.addPersonBtn": "addPersonBtn",
                "click button.addRiskBtn": "addRiskBtn",
            },
            render: function () {
                DetailRiskView.__super__.render.call(this);
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
                window.location.href = "#admin/detailProject/" + this.projectId + "/addPerson";
            },
            addRiskBtn: function () {

            },
        });
        return DetailRiskView;
    });