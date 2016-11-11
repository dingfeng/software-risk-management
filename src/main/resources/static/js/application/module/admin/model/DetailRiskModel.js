/**
 * Created by 邹玉鑫 on 2016/11/11.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var DetailRiskModel = BaseModel.extend({
        initialize: function () {
            DetailRiskModel.__super__.initialize.call(this);
        },
        defaults: {
            id: '编号',
            description: '风险描述',
            projectName: '',
            createdBy: '创建人',
            createdAt: '创建时间',
            updatedAt: '编辑时间',
        },
    });
    return DetailRiskModel;
});