/**
 * Created by 邹玉鑫 on 2016/11/6.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    console.log("Load application/module/authority/model/Login.js");
    var LoginModel = BaseModel.extend({
        initialize: function () {
            LoginModel.__super__.initialize.call(this);
        },
        defaults: {
            // urlRoot : '/specialTeams',
            // url : '/specialTeams',
            username: '',
            password: '',
            errorMsg: '',
        },
        changeEvent: function () {
            alert("changed");
        }
    });
    return LoginModel;
});