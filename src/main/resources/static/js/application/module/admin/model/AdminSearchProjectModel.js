/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var AdminSearchProjectModel = BaseModel.extend({
        initialize: function () {
            AdminSearchProjectModel.__super__.initialize.call(this);
        },
        defaults: {
            id: '编号',
            name: '项目名',
            description: '描述',
            createdBy: '创建人',
            createdAt: '创建时间',
            updatedAt: '编辑时间',
        },
    });
    return AdminSearchProjectModel;
});