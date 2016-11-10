/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var AddUserModel = BaseModel.extend({
        initialize: function () {
            AddUserModel.__super__.initialize.call(this);
        },
        defaults: {
            username: 'username',
            password: 'password',
            errorMsg: '',
            role: '',
        },
    });
    return AddUserModel;
});