/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseCollection", "./SearchProjectModel"], function (BaseCollection, SearchProjectModel) {
    var SearchProjectCollection = BaseCollection.extend({
        model : SearchProjectModel,
    });
    return SearchProjectCollection;
});