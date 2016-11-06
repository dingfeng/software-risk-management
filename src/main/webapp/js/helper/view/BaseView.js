/**
 * Created by 邹玉鑫 on 2016/11/6.
 */
define(["jquery", "underscore", "backbone", "helper/model/BaseModel"], function ($, _, Backbone, BaseModel) {
    var BaseView = Backbone.View.extend({
        // constructor: function (config) {
        //     var config = config || {};
        //     BaseView.prototype.constructor.apply(this, config);
        // },
        // initialize: function () {
        //     this.render();
        //     // LoginView.prototype.initialize.call(this);
        // },
        // title: '',
        // model: new BaseModel(),
        // tpl: '',
        // render: function () {
        //     $('title').html(this.title);
        //     $(this.el).html(_.template(this.tpl)(this.model.toJSON()));
        // },
    });
    return BaseView;
});