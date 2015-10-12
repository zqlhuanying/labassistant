$(function(){
	$("#findPassword").submit(function(){
		$(this).ajaxSubmit({
            url : $("#domain").val() + "/getLost",
	        dataType : 'json', 
	        beforeSubmit : validForm,
	        success : function(res){
	        	if(res && "1" == res.code){
                    app.alert("密码修改成功");
				}else{
                    app.alert(res.msg ? res.msg : "密码修改失败");
				}
	        } 
	    });
	    return false;
	});
});

function validForm(){
	var pwd = $("#pwd").val();
	if(!pwd || pwd == ''){
		app.alert("请输入密码");
		$("#pwd").focus();
		return false;
	}
    if(pwd.length < 6){
        app.alert("密码至少6个字符");
        $("#pwd").focus();
        return false;
    }
	var confirm = $("#confirm").val();
	if(!confirm || confirm == ''){
        app.alert("请再次输入密码");
		$("#confirm").focus();
		return false;
	}
    if(confirm != pwd){
        app.alert("密码和确认密码不一致");
        $("#confirm").focus();
        return false;
    }
	return true;
}

