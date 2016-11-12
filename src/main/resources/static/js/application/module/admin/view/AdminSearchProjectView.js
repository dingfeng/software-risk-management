/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/view/BaseView", "../model/SearchProjectCollection", "../model/SearchProjectModel", "text!../template/searchProject.tpl", "text!../css/searchProject.css", "common/util"],
    function (BaseView, SearchProjectCollection, SearchProjectModel, SearchProjectTpl, SearchProjectCss, Util) {
        var AdminSearchProjectView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                AdminSearchProjectView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看项目',
            model: new SearchProjectCollection,
            tpl: SearchProjectTpl,
            css: SearchProjectCss,
            events: {
                "click .item": "detail",
                "click .deleteProject" : "deleteProject"
            },
            deleteProject :function(e)
            {
                   var target = e.target;
                   var projectId = $(target).attr("id");
                   $.ajax({
                         type: "POST",
                          url: "/project/delete",
                          data: "projectId="+projectId,
                          success: function (data) {
                                console.log("success");
                                window.location.reload();
                          }
                    });
                   console.log("deleteProject");
            }
            ,
            render: function () {
                AdminSearchProjectView.__super__.render.call(this);
                this.model.reset();

                var that = this;
                $.ajax({
                    type: "POST",
                    url: "/project/allProject",
                    data: "sessionid=" + Util.getSessionId(),
                    // error: function () {
                    //     window.location.href = "#login";
                    // },



                    success: function (data) {

                        var obj =eval("("+data+")");
                        _.each(obj.data, function (value) {
                            that.model.add(new AdminSearchProjectView(value));
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
        return AdminSearchProjectView;
    });