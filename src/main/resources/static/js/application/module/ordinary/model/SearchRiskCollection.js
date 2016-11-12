/**
 * Created by zy118686 on 2016/11/12.
 */
define(["common/model/BaseCollection", "./DetailRiskModel"], function (BaseCollection, DetailRiskModel) {
    var SearchRiskCollection = BaseCollection.extend({
        model: DetailRiskModel,
    });
    return SearchRiskCollection;
});