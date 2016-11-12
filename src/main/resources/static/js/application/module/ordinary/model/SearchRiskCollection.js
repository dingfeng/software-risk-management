/**
 * Created by zy118686 on 2016/11/12.
 */
define(["common/model/BaseCollection", "./SearchRiskModel"], function (BaseCollection, SearchRiskModel) {
    var SearchRiskCollect = BaseCollection.extend({
        model : SearchRiskModel,
    });
    return SearchRiskCollect;
});