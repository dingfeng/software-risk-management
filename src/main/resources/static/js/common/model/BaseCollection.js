/**
 * Created by 邹玉鑫 on 2016/11/8.
 */
define(["backbone", "../model/BaseModel"], function (Backbone, BaseModel) {
    var BaseCollection = Backbone.Collection.extend({
        model : BaseModel,
    });
    return BaseCollection;
});