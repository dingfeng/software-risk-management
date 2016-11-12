/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseCollection", "../model/AdminSearchProjectModel"], function (BaseCollection, AdminSearchProjectModel) {
    var AdminSearchProjectCollection = BaseCollection.extend({
        model : AdminSearchProjectModel,
    });
    return AdminSearchProjectCollection;
});