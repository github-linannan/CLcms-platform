/***
 * 添加物流信息
 */
var sampleReportId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    if(sampleReportId!=null&&sampleReportId!=""){
        $("input[name='tId']").val(sampleReportId);
        $.ajax({
            type: "get",
            url: web_url+"sampleReport/selectByParam",
            cache: false,  //禁用缓存
            data: {"token":token,tId:sampleReportId},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    $(":input[name^=t]").each(function(){
                        (result.data[0])[this.name]!=""?$(this).val((result.data[0])[this.name]):this.value;
                    });
                }
            }
        });
    }
    $("#form-sampleReport-add").validate({
        rules:{
            tLogistics:{
                required:true
            },
            tLogisticsNumber:{
                required:true
            },
            tReportStatus:{
                required:true
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            sampleReport_save();
        }
    });

});
function sampleReport_save(){
    var  param={};
    $(":input[name^=t]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    param['token']=token;//token值
    param['tReportStatus']=$("select[name='tReportStatus']").val();//报告状态
    $.ajax({
        type: "post",
        url: web_url+"sampleReport/updateSampleReportStatus",
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
function sampleReport_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}