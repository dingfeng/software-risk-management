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
            username: 'zhouyao',
            password: '123456',
            errorMsg: '',
        },
        changeEvent: function () {
            alert("changed");
        }
    });
    return LoginModel;
});