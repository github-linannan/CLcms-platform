/***
 * 添加、修改医院
 */
var hospitalId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    if(hospitalId!=null&&hospitalId!=""){
        $("input[name='tId']").val(hospitalId);
        $.ajax({
            type: "get",
            url: web_url+"hospital/findHospital/"+hospitalId,
            cache: false,  //禁用缓存
            /*data: {"tId":hospitalId},  //传入组装的参数*/
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
    $("#form-hospital-add").validate({
        rules:{
            tHospitalCode:{
                required:true
            },
            tHospitalName:{
                required:true
            },
            tRemark:{
                required:true
            },
            tStatus:{
                required:true
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            hospital_save();
        }
    });

});
function hospital_save(){
    var  param={};
    $(":input[name^=t]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    param['token']=token;//token值
    var url=hospitalId>0?url="hospital/update":"hospital/insert";
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
function hospital_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}