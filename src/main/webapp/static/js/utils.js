var app = $.extend({}, app);  /* 全局对象 */

var art;

app.alert = function(msg, opt){
	app.closeDialog();
	var _opt = $.extend({
	    title: '提示',
	    width:"200",
	    content: msg,
	    cancel: false,
	    ok: function () {}
	}, opt||{});
	art = dialog(_opt);
	art.show();
};

app.closeDialog = function(){
	if(art){
		art.close().remove();
		art = undefined;
	}
};