/**
 * Created by 邹玉鑫 on 2016/11/6.
 */
define(["jquery", "underscore", "backbone", "../model/BaseModel"],
    function ($, _, Backbone, BaseModel) {
        var BaseView = Backbone.View.extend({
            // constructor: function (config) {
            //     var config = config || {};
            //     BaseView.prototype.constructor.apply(this, config);
            // },
            initialize: function () {
                if (this.sync) this.model.on("change", this.setTpl, this);
                this.render();
            },
            sync: true,
            title: '',
            model: new BaseModel,
            tpl: '',
            css: '',
            events: {},
            render: function () {
                if ($(this.el).get(0).tagName.toLowerCase() === 'body') {
                    _.each($('style'), function (value) {
                        value.remove();
                    });
                }
                if (this.title) $('title').html(this.title);
                if (this.css) $('head').append($("<style>" + this.css + "</style>"))
                this.setTpl();
            },
            setTpl: function () {
                $(this.el).html(_.template(this.tpl, {variable: 'data'})(this.model.toJSON()));
            }
        });
        return BaseView;
    });