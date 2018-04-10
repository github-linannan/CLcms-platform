/***
 * 添加、修改角色
 */
var roleId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
        if(roleId!=null&&roleId!=""){
            $("input[name='tId']").val(roleId);
            $.ajax({
                type: "get",
                url: web_url+"role/findRole/"+roleId,
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if(result.code==200){
                        $(":input[name^=t]").each(function(){
                            (result.data)[this.name]!=null?$(this).val((result.data)[this.name]):this.value;
                        });
                    }
                }
            });
        }else{
            $(":input").each(function(){
                    $(this).val("");
            });
            $("select[name='tStatus']").val("0");
        }
    $("#form-role-add").validate({
        rules:{
            tRoleName:{
                required:true
            },
            tRemark:{
                required:true
            },
            tStatus:{
                required:true
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            role_save();
        }
    });
});
function role_save(){
        var  param={};
        $(":input[name^=t]").each(function(v,i){
            if(this.value!="")
                param[this.name]=this.value;
        });
        param['token']=token;//token值
        var url=roleId>0?url="role/update":"role/insert";
        $.ajax({
            type: "post",
            url: web_url+url,
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){

                    layer.alert('保存成功',function(index){
                        layer.close(index);
                        parent.window.$('.table-sort').dataTable().fnDraw();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                }
                else{
                    layer.msg(result.detailMessage,{icon:1,time:1000});
                }
            },
            error:function (err) {
                console.log("错误");
            }
        });
}
function role_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

