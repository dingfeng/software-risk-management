/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseCollection", "./DetailProjectModel"], function (BaseCollection, DetailProjectModel) {
    var SearchProjectCollection = BaseCollection.extend({
        model : DetailProjectModel,
    });
    return SearchProjectCollection;
});