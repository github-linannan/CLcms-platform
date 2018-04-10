/***
 * 添加、修改主任
 */
var departmentId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    var select_hospital=$(":input[name=tHospitalParentId]");
    var hospital_option="";
    $.ajax({
        type: "get",
        url: web_url+"hospital/findHospitalPage/",
        cache: false,  //禁用缓存
        data: {"token":token},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if(result.code==200) {
                var data = result.data;
                hospital_option="<option value=''>请选择医院</option>";
                $.each(data, function (key, value) {
                    if(value['tStatus']==0) {
                        hospital_option += "<option value='" + value['tId'] + "'>" + value['tHospitalName'] + "</option>";
                    }
                });
                select_hospital.html(hospital_option);
            }
            addDepartment();
        }
    });
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    function addDepartment() {
        if(departmentId!=null&&departmentId!=""){
            $("input[name='tId']").val(departmentId);
            $.ajax({
                type: "get",
                url: web_url+"department/findDepartment/"+departmentId,
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
            $("[name='tStatus']").val("0");
        }
    }
    $("#form-department-add").validate({
        rules:{
            tDepartmentCode:{
                required:true
            },
            tDepartmentName:{
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
            department_save();
        }
    });

});
function department_save(){
    var  param={};
    $(":input[name^=t]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    param['token']=token;//token值
    var url=departmentId>0?url="department/update":"department/insert";
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
function department_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}