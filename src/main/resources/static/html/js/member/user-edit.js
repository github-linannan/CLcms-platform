/**
 * Created by linannan on 2018/1/2.
 */

/*
var userInfoId=getQueryString("id"),
    loginStatus = getQueryString("status");
*/

var request = new Object();
request = GetRequest();
var userInfoId = request['id'];
/*var loginStatus = request['status'];*/
var token = MyLocalStorage.Cache.get('token');
var loginStatus2num={
    0: "未知",
    1: "正常",
    2: "注销",
    3: "锁定"
};
$(function(){

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var args = {};
    if(userInfoId!=null&&userInfoId!=""){
        args = {
            loginId: userInfoId,
            token:token
        };
        $.ajaxByGet("appLogin/selectByParam", args, function (result) {
            if (result.code === 200) {
               var loginStatus= (result.data)[0].loginStatus;
                $(".text-d").each(function(index,item){
                    (result.data)[0][$(this).attr('name')]!=null?$(this).text((result.data)[0][$(this).attr('name')]):$(this).text();
                    if ($(this).attr('name') == 'loginStatus'){
                        $(this).text(loginStatus2num[loginStatus]);
                    }
                });
            }

        });
    }else{
        $(".text-d").each(function(){
            $(this).text("");
        });
    }

});
