/**
 * Created by linannan on 2018/1/3.
 */

var request = new Object();
request = GetRequest();
var userInfoId = request['id'],
    oldLoginPassword = request['pwd'];
var token = MyLocalStorage.Cache.get('token');
$(function(){


    $("#form-change-password").validate({
        rules:{
            newPwd:{
                required:true,
                minlength:8,
                maxlength:16
            },
            confirmPwd:{
                required:true,
                minlength:8,
                maxlength:16,
                equalTo: "#confirmPwd"
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            var newpassword = $('#newPwd').val();
            var isValidate = true;
            $("#form-change-password :input[type='password']").each(function(){
                var $input = $(this);
                isValidate = $.validate($input);
                return isValidate;
            });
            if (isValidate){
                var args={
                    loginId: userInfoId,
                    LoginPassword: newpassword,
                    token: token
                }
                $.ajaxByPost("appLogin/updateAppLogin", args, function (result) {
                    if (result.code === 200) {
                        layer.alert('新密码修改成功',function(index){
                            layer.close(index);
                            parent.window.$('.table-sort').dataTable().fnDraw();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }else {
                        layer.msg(result.detailMessage);
                    }

                });
            }

        }
    });
});