/**
 * Created by zy118686 on 2016/11/12.
 */
define(["common/view/BaseView", "../model/SearchProjectCollection", "../model/DetailProjectModel", "text!../template/searchMyJoinProject.tpl", "text!../css/searchProject.css", "common/util"],
    function (BaseView, SearchProjectCollection, DetailProjectModel, SearchProjectTpl, SearchProjectCss, Util) {
        var SearchProjectView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                SearchProjectView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看我加入的项目',
            model: new SearchProjectCollection,
            tpl: SearchProjectTpl,
            css: SearchProjectCss,
            events: {
                "click .item": "detail"
            },
            render: function () {
                SearchProjectView.__super__.render.call(this);
                this.model.reset();

                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/user/getProjectJoin",
                    data: "sessionid=" + Util.getSessionId(),
                    // error: function () {
                    //     window.location.href = "#login";
                    // },



                    success: function (data) {

                        var obj =eval("("+data+")");
                        _.each(obj.data, function (value) {
                            that.model.add(new DetailProjectModel(value));
                        });
                    }
                });

                // this.model.add(new DetailProjectModel({
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
        return SearchProjectView;
    });