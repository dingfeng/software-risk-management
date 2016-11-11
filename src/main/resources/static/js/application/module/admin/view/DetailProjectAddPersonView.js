/**
 * Created by 邹玉鑫 on 2016/11/11.
 */
define(["common/view/BaseView", "text!../template/detailProjectAddPerson.tpl", "text!../css/detailProjectAddPerson.css", "common/util"],
    function (BaseView, DetailProjectAddPersonTpl, DetailProjectAddPersonCss, Util) {
        var DetailProjectAddPersonView = BaseView.extend({
            initialize: function () {
                DetailProjectAddPersonView.__super__.initialize.call(this);
            },
            el: '#content',
            title: '邀请人员',
            tpl: DetailProjectAddPersonTpl,
            css: DetailProjectAddPersonCss,
            projectId: '',
            events: {
                "click button.ok": "ok",
                "click button.cancel": "cancel",
            },
            render: function () {
                DetailProjectAddPersonView.__super__.render.call(this);
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
            ok: function () {

            },
            cancel: function () {
                window.location.href = "#admin/detailProject/" + this.projectId;
            }
        });
        return DetailProjectAddPersonView;
    });