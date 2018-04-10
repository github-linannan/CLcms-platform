/**
 * Created by linannan on 2018/1/4.
 */
$(function () {
    $('.btn-login').click(function() {
        var tLoginName = $('#username').val();
        var tPassword = $('#pwd').val();
        var param={
            tLoginName: tLoginName,
            tPassword: tPassword,
            date:new Date()
        }
        if (tLoginName.length !="" || tPassword.length !=""){
            $.ajax({
                type: "get",
                url: web_url+"mlogin/login",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    if(result.code == 200){
                        MyLocalStorage.Cache.put('token', result.data);
                        window.location.href='index.html';
                    }else {
                        $('.error-info').css("color","red").text("*"+result.detailMessage);
                    }
                },
                erro:function () {
                    console.log('请求出错');
                }
            });
        }else {
            $('.error-info').css("color","red").text("*请输入正确的用户名或密码");
        }

    });
    document.onkeydown=function(e){
        var e = e || event;
        var currKey=e.keyCode||e.which||e.charCode;
        if(currKey==13){
            $('.btn-login').click();
        }
    }
});
