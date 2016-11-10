/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["../model/BaseCollection", "../model/AsideModel"], function (BaseCollection, AsideModel) {
    var AsideCollection = BaseCollection.extend({
        model : AsideModel,
    });
    return AsideCollection;
});