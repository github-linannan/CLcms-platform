/**
 * Created by linannan on 2018/1/2.
 */

var request = new Object();
request = GetRequest();
var userId = request['id'];
var token = MyLocalStorage.Cache.get('token');
$(function(){

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var args = {};
    if(userId!=null&&userId!=""){
        args = {
            userId: userId,
            token: token
        };
        //ajax请求数据
        $.ajax({
            type: "get",
            url:web_url+ "appUser/selectByParam",
            cache: false,  //禁用缓存
            data: args,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code === 200) {
                    $(".text-d").each(function(index,item){
                        (result.data)[0][$(this).attr('name')]!=null?$(this).text((result.data)[0][$(this).attr('name')]):$(this).text();
                        if ($(this).attr('name') == "userBirthday"){
                            $(this).text((result.data)[0]["userBirthday"].substring(0,10));
                        }
                    });
                    if((result.data)[0]["userAddressInfo"] !=null && (result.data)[0]["userAddressInfo"] != ""){
                        $("#address").text((result.data)[0]["userAddress"]+(result.data)[0]["userAddressInfo"]);
                    }
                }
            }
        });
    }else{
        $(".text-d").each(function(){
            $(this).text("");
        });
    }
});



