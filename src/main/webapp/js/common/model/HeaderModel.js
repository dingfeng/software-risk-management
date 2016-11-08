/**
 * Created by 邹玉鑫 on 2016/11/7.
 */
define(["../model/BaseModel"], function (BaseModel) {
    var HeaderModel = BaseModel.extend({
        initialize: function () {
            HeaderModel.__super__.initialize.call(this);
        },
        defaults: {
            username: 'zyxin13',
        },
    });
    return HeaderModel;
});