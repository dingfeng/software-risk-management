/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/view/BaseView", "../model/SearchUserCollection", "../model/SearchUserModel", "text!../template/searchUser.tpl", "text!../css/searchUser.css", "common/util"],
    function (BaseView, SearchUserCollection, SearchUserModel, SearchUserTpl, SearchUserCss, Util) {
        var AdminSearchUserView = BaseView.extend({
            initialize: function () {
                if (this.sync) this.model.on("add", this.setTpl, this);
                AdminSearchUserView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '查看人员',
            model: new SearchUserCollection,
            tpl: SearchUserTpl,
            css: SearchUserCss,
            events: {
                "click .item": "detail",
                 "click .modify" : "modify",
                 "click .deleteUser" : "deleteUser"
            },
            modify : function (e)
            {
              console.log(e);
              console.log("modify clicked");
            },

            deleteUser : function (e)
            {
               var target = e.target;
               var userId = $(target).attr("id");
               $.ajax({
                     type: "POST",
                      url: "/user/delete",
                      data: "userId="+userId,
                      success: function (data) {
                              console.log("success");
                              window.location.reload();
                      }
                     });
            }
            ,
            render: function () {
                AdminSearchUserView.__super__.render.call(this);
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
                            that.model.add(new SearchUserModel(value));
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
        return AdminSearchUserView;
    });