/**
 * Created by 邹玉鑫 on 2016/11/12.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var CreateProjectModel = BaseModel.extend({
        initialize: function () {
            CreateProjectModel.__super__.initialize.call(this);
        },
        defaults: {
            projectName: 'username',
            description: 'password',
        },
    });
    return CreateProjectModel;
});