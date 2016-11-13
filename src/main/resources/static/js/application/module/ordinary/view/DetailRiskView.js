define(["common/view/BaseView", "../model/DetailRiskModel", "text!../template/detailRisk.tpl", "text!../css/detailRisk.css", "common/util"],
    function (BaseView, DetailRiskModel, DetailRiskTpl, DetailRiskCss, Util) {
        var DetailRiskView = BaseView.extend({
            initialize: function (Id) {
                this.riskId = Id ;
                DetailRiskView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '创建新风险',
            model: new DetailRiskModel,
            tpl: DetailRiskTpl,
            css: DetailRiskCss,
            projectId: '0',
            riskId: ' ',
            events: {
                "click button.ok": "ok",
                "click button.cancel": "cancel",
            },
            render: function () {
                DetailRiskView.__super__.render.call(this);
                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/risk/getRiskById",
                    data: "riskId=" + this.riskId,
                     error: function () {
                         window.location.href = "#login";
                     },
                    success: function (data) {
                        var obj =eval("("+data+")");

                        alert(data);

                        if(obj.isSuccess) {
                            that.model.add(obj.data);
                        }else{
                            alert(obj.errMsg);
                        }

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
                $("#detailRisk form").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                this.model.set(data);
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: this.model.attributes,
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
            cancel: function () {
                window.location.href = "#ordinary/detailProject/" + this.projectId;
            },
        });
        return DetailRiskView;
    });