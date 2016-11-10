/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["../model/BaseModel"], function (BaseModel) {
    var AsideModel = BaseModel.extend({
        initialize: function () {
            AsideModel.__super__.initialize.call(this);
        },
        defaults: {
            id: '',
            name: '创建项目',
            url: '#admin/addUser',
            active: false,
        },
    });
    return AsideModel;
});