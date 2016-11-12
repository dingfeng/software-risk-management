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
            ok: function () {
                var data = {};
                $("#detailProjectAddPerson form").serializeArray().map(function (x) {
                    data[x.name] = x.value;
                });
                data.projectId = this.projectId;
                $.ajax({
                    type: "POST",
                    url: "/project/createProject",
                    data: data,
                    // async: false,
                    error: function () {
                        alert("服务器错误");
                    },
                    success: function (data) {
                        var obj = eval("(" + data + ")");
                        if (obj.isSuccess) {
                            alert("邀请成功！");
                        } else {
                            alert(obj.errMsg);
                        }
                    }
                });
            },
            cancel: function () {
                window.location.href = "#ordinary/detailProject/" + this.projectId;
            }
        });
        return DetailProjectAddPersonView;
    });