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
            title: '标题',
            possibility: '可能性',
            status: '状态',
            influence: '影响',
            trigger: '触发器',
            description: '文本描述',
            author: '创建者',
            handler: '处理者',
            project: '所属项目名',
            projectId: '所属项目id',
            createdAt: '创建时间',
            updatedAt: '编辑时间',
        },
    });
    return DetailRiskModel;
});