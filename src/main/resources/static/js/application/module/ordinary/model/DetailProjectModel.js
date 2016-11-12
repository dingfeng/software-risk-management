define(["common/model/BaseModel", "../model/SearchRiskCollection"], function (BaseModel, SearchRiskCollection) {
    var DetailProjectModel = BaseModel.extend({
        initialize: function () {
            DetailProjectModel.__super__.initialize.call(this);
        },
        defaults: {
            id: '编号',
            name: '项目名',
            description: '描述',
            joinedNames: '',
            createdBy: '创建人',
            createdAt: '创建时间',
            updatedAt: '编辑时间',
            riskList: new SearchRiskCollection,
        },
    });
    return DetailProjectModel;
});