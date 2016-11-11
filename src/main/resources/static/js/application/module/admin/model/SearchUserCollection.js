/**
 * Created by 邹玉鑫 on 2016/11/10.
 */
define(["common/model/BaseCollection", "../model/SearchUserModel"], function (BaseCollection, SearchUserModel) {
    var SearchUserCollection = BaseCollection.extend({
        model : SearchUserModel,
    });
    return SearchUserCollection;
});