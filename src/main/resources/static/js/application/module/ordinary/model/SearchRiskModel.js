/**
 * Created by zy118686 on 2016/11/12.
 */
define(["common/model/BaseModel"], function (BaseModel) {
    var SearchRiskModel = BaseModel.extend({
        initialize: function () {
            SearchProjectModel.__super__.initialize.call(this);
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
            project: '所属项目',
            createdAt: '创建时间',
            updatedAt: '编辑时间',
        },
    });
    return SearchRiskModel;
});