/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var SearchUserModel = BaseModel.extend({
        initialize: function () {
            SearchUserModel.__super__.initialize.call(this);
        },
        defaults: {
            id: '编号',
            account: '账号',
            email : '邮箱',
            role : '角色',
        },
    });
    return SearchUserModel;
});