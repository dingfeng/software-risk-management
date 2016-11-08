/**
 * Created by 邹玉鑫 on 2016/11/6.
 */
define(["jquery", "underscore", "backbone", "helper/model/BaseModel"],
    function ($, _, Backbone, BaseModel) {
        var BaseView = Backbone.View.extend({
            // constructor: function (config) {
            //     var config = config || {};
            //     BaseView.prototype.constructor.apply(this, config);
            // },
            initialize: function () {
                if (this.sync) this.model.on("change", this.render, this);
                this.render();
            },
            sync: false,
            title: '',
            model: new BaseModel(),
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
                $(this.el).html(_.template(this.tpl)(this.model.toJSON()));
            },
        });
        return BaseView;
    });