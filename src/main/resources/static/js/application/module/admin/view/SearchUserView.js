/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/view/BaseView", "../model/SearchUserCollection", "../model/SearchUserModel", "text!../template/searchUser.tpl", "text!../css/searchUser.css", "common/util"],
    function (BaseView, SearchProjectCollection, SearchProjectModel, SearchProjectTpl, SearchProjectCss, Util) {
        var SearchProjectView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                SearchProjectView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看人员',
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
                    url: "/user/allUser",
                    data: "sessionid=" + Util.getSessionId(),
                    // error: function () {
                    //     window.location.href = "#login";
                    // },



                    success: function (data) {

                        var obj =eval("("+data+")");
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
            detail: function() {

            }
        });
        return SearchProjectView;
    });