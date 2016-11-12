/**
 * Created by zy118686 on 2016/11/12.
 */
define(["common/view/BaseView", "../model/SearchRiskCollection", "../model/SearchRiskModel", "text!../template/searchRisk.tpl", "text!../css/searchRisk.css", "common/util"],
    function (BaseView, SearchRiskCollection, SearchRiskModel, SearchRiskTpl, SearchRiskCss, Util) {
        var SearchRiskView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                SearchRiskView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看我创建的风险',
            model: new SearchRiskCollection,
            tpl: SearchRiskTpl,
            css: SearchRiskCss,
            events: {
                "click .item": "detail"
            },
            render: function () {
                SearchRiskView.__super__.render.call(this);
                this.model.reset();

                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/user/getRisksCreate",
                    data: "sessionid=" + Util.getSessionId(),
                    // error: function () {
                    //     window.location.href = "#login";
                    // },



                    success: function (data) {

                        var obj =eval("("+data+")");
                        _.each(obj.data, function (value) {
                            that.model.add(new SearchRiskModel(value));
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
            detail: function() {

            }
        });
        return SearchRiskView;
    });