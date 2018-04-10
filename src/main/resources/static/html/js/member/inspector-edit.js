/**
 * Created by linannan on 2018/1/2.
 */

var request = new Object();
request = GetRequest();
var userId = request['id'];
var token = MyLocalStorage.Cache.get('token');
var errorFlag = false;

var chMobilePhoneFlag = true;
$(function(){

    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var args = {};
    if(userId!=null&&userId!=""){
        $("input[name='userId']").val(userId);
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
                    $(":input[name^=user]").each(function(index,item){

                        (result.data)[0][$(this).attr('name')]!=null?$(this).val((result.data)[0][$(this).attr('name')]):$(this).val();
                        if ($(this).attr('name') == "userBirthday"){
                            $(this).val((result.data)[0]["userBirthday"].substring(0,10));
                        }
                    });
                    /*if((result.data)[0]["userAddressInfo"] !=null && (result.data)[0]["userAddressInfo"] != ""){
                        $("#address-info").val((result.data)[0]["userAddressInfo"]);
                    }*/
                    if((result.data)[0]["userSex"] !=null && (result.data)[0]["userSex"] != ""){
                        $('.select').val((result.data)[0]["userSex"]);
                    }
                }
            }
        });




    }else{
        $("input[name^=user]").each(function(item,index){
            $(this).text("");
        });
    }

});


function chMobilePhone(){

    var userTelephoneHtml = $("#phone");
   var args = {
        token: token,
        userTelephone: userTelephoneHtml.val()
    };
   var oldPhone = $("#oldPhone").val();
   if ($.validate(userTelephoneHtml) == true){
       if (userTelephoneHtml.val() == oldPhone) {
           chMobilePhoneFlag = false;
           return false;
       }else {
           //ajax请求数据
           $.ajax({
               type: "get",
               url:web_url+ "appUser/selectByParam",
               cache: false,  //禁用缓存
               data: args,  //传入组装的参数
               dataType: "json",
               success: function (result) {
                   if (result.code === 200) {
                       var data = result.data;
                       if (data.length >= 1){
                           layer.msg("该手机号已存在，请重新输入！");
                           /*$('#phone').val("");*/
                           $('#phone').focus();
                           chMobilePhoneFlag = false;
                           return false;
                        }
                       chMobilePhoneFlag = true;
                   }
               }
           });
       }
   }else{
       chMobilePhoneFlag = false;
   }
    return chMobilePhoneFlag;
}

/*function checkNum(){
    var oval = document.getElementById('phone').value;
    if(oval.length == 11){
        chMobilePhone(oval);
    }
}*/

function inspector_save(){
    var  param={};
    param['token'] = token;

    $(":input[name^=user]").each(function(v,i){
        if(this.value!=""){
            param[this.name]=this.value;
        }
    });
    var userNameHtml = $("input[name=userName]");
    var userTelephoneHtml = $("input[name=userTelephone]");
    if(userNameHtml.val() != "" && userNameHtml.val() != null && userNameHtml.val() != undefined){/*验证用户名*/
        errorFlag = true;
        /*   return true;*/
    }else {
        layer.msg("请输入用户名！");
        return false;
    }
    if ($("#phone").val() != "" && $("#phone").val() != null){/*验证手机号码*/
        $.validate($("#phone"));
        if ($.validate($("#phone")) == false) {//校验失败
            /*return false;*/
            $("#phone").val("");
            $('#phone').focus();
            return false;
        }else {
            errorFlag = true;
        }
    }else {
        layer.msg("请输入手机号码！");
        return false;
    }
    /*验证地址信息*/
    if ($(":input[name=userAddress]").val() != "" && $("#address-info").val() != "") {
        param["userAddress"] = $(":input[name=userAddress]").val() ;
        param["userAddressInfo"] = $("#address-info").val();
        errorFlag = true;
    }else {
        layer.msg("请完善地址信息！");
        return false;
    }

    var url=userId>0?url="appUser/updateUser":"appUser/addUser";
    if (errorFlag == true && chMobilePhoneFlag == true){
        $.ajaxByPost(url, param, function (result) {
            if (result.code === 200) {
                layer.alert('保存成功',function(index){
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
