/**
 * Created by linannan on 2018/1/2.
 */

/*
 var userInfoId=getQueryString("id"),
 loginStatus = getQueryString("status");
 */

var request = new Object();
request = GetRequest();
var userId = request['id'];
/*var loginStatus = request['status'];*/
var token = MyLocalStorage.Cache.get('token');
var loginStatus2num={
    0: "未知",
    1: "正常",
    2: "注销",
    3: "锁定"
};
var param={};
param["token"] = token;
$(function(){

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var args = {};
    if(userId!=null&&userId!=""){
        $.ajax({
            type: "get",
            url: web_url+"mlogin/findLogin/"+userId,
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    var data=result.data;
                    var tUserType= (result.data).tUserType;
                    $(".text-d").each(function(index,item){
                        (result.data)[$(this).attr('name')]!=null?$(this).text((result.data)[$(this).attr('name')]):$(this).text();
                        if ($(this).attr('name') == 'tUserType'){
                            $(this).text(loginStatus2num[tUserType]);
                        }
                    });

                }
            }
        })
    }else{
        $(".text-d").each(function(){
            $(this).text("");
        });
    }

});
