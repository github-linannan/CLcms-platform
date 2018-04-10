/***
 * 添加、修改后台登陆用户
 */
var mloginId=getQueryString("id");
var hospital_id="";//选中或默认的医院id
var department_id="";//选中或默认的科室id
var director_id="";//主任id
var user_type="";//用户类型
var token=MyLocalStorage.Cache.get('token');
var login_had=false;//用户是否存在
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
        if(mloginId!=null&&mloginId!=""){
            $("#login-passwd").hide();
            $("input[name='tId']").val(mloginId);
            $.ajax({
                type: "get",
                url: web_url+"mlogin/findLogin/"+mloginId,
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if(result.code==200){
                        var data=result.data;
                        director_id=data['tDirectorId'];
                        user_type=data['tUserType'];
                        $(":input[name^=t]").each(function(){
                            if($(this).attr('name')!='tDirector') {//不能改变radio值
                                data[this.name] != null ? $(this).val(data[this.name]) : this.value;
                            }
                        });
                        if(data['tUserType']==2){//医生
                            hospitalList(data['tHospitalId']);//显示默认医院信息
                            departmentList(data['tHospitalId'],data['tDepartmentId']);//显示默认科室信息
                            isDirector(data['tHospitalId'],data['tDepartmentId']);//显示主任默认信息
                            $("div[name^=sel]").show();//显示医院、科室、主任div
                            hospital_id=data['tHospitalId'];
                            department_id=data['tDepartmentId'];
                        }
                        else if(data['tUserType']==3) {//医院
                            hospitalList(data['tHospitalId']);//显示默认医院信息
                            $("div[name=selHospital]").show();
                        }
                        else if(data['tUserType']==1) {//经销商
                            $(".sale-display").show();
                        }
                    }
                }
            });
        }
        else{//新增
            $(":input").each(function(){
                if($(this).attr('name')!='tDirector'){//radio不能默认为空
                    $(this).val("");
                }
            });
            $("#login-passwd").show();
            $("select[name='tStatus']").val("0");
            $("#tLoginName").blur(function(){//失去焦点事件
                var  param={};
                param['token']=token;//token值
                param['tLoginName']=$("input[name='tLoginName']").val();//token值
                $.ajax({
                    type: "get",
                    url: web_url+"mlogin/findLoginName",
                    cache: false,  //禁用缓存
                    data:param,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {
                        if(result.code==200){
                            $(".validate-wrong").html("此已存在账号");
                            login_had=true;
                        }
                        else{
                            $(".validate-wrong").html("");
                            login_had=false;
                        }
                    }

                });
            });
        }
    $("#form-mlogin-add").validate({
        rules:{
            tLoginName:{
                required:true
            },
            tPassword:{
                required:true
            },
            tUserName:{
                required:true
            },
            tTelephone:{
                required:true,
                isMobile:true
            },
            tEmail:{
                required:true,
                email:true
            },
            tUserType:{
                required:true
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            if(login_had==false){
            mlogin_save();
            }
        }
    });

});
jQuery.validator.addMethod("isMobile", function(value, element) {//自定义手机验证方法
    var length = value.length;
    var mobile = /^(((13[0-9]{1})|(15[0-35-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");
function mlogin_save(){//提交信息
    var  param={};
    var directorNumber=$("input[name='tDirector']:checked").val();
    $(":input[name^=t]").each(function(v,i){
        if(this.value!=""){
            param[this.name]=this.value;
        }
    });
    param['token']=token;
    if(user_type==2){//医生
        param['tDirector']=directorNumber;//radio是否是主任值
        param['tDirectorId']=director_id;//主任id
    }
    var url=mloginId>0?url="mlogin/update":"mlogin/insert";
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

$(document).on("change",'select#userType',function(){//选择类型checkbox改变事件
    var obj=$(this).val();
    switch (obj){
        case '0'://系统用户
            $("div[name^=sel]").hide();//隐藏医院名称、科室名称、主任
            $(".sale-display").hide();//隐藏销售上级填写框
            break;
        case '1'://销售
            $("div[name^=sel]").hide();//隐藏医院名称、科室名称、主任
            $(".sale-display").show();//显示销售上级填写框
            break;
        case '2'://医生
            $("div[name^=sel]").show();//显示医院名称、科室名称、主任
            hospitalList();//获取医院列表
            $(".sale-display").hide();//隐藏销售上级填写框
            break;
        case '3'://医院
            $("div[name^=sel]").hide();//隐藏医院名称、科室名称、主任
            $("div[name=selHospital]").show();//显示医院名称
            hospitalList();//获取医院列表
            $(".sale-display").hide();
            break;
        default:
            break;
    }
    user_type=$(this).val();
});
$(document).on("change",'select#hospitalId',function(){//选择医院checkbox改变事件
    hospital_id=$(this).val();
    departmentList(hospital_id);//科室列表
});
$(document).on("change",'select#departmentId',function(){//选择科室checkbox改变事件
    department_id=$(this).val();
    isDirector(hospital_id,department_id);//主任信息
});
function hospitalList(obj) {//生成医院列表
    var select_hospital=$(":input[name=tHospitalId]");
    var hospital_option="";
    $.ajax({
        type: "get",
        url: web_url+"hospital/findHospitalPage/",
        cache: false,  //禁用缓存
        data: {"token":token},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if(result.code==200) {
                hospital_option="<option value=''>请选择医院</option>";
                var data = result.data;
                $.each(data, function (key, value) {
                    if(value['tStatus']==0){
                        hospital_option+="<option value='" + value['tId'] + "'>" + value['tHospitalName'] + "</option>";
                    }
                });
                select_hospital.html(hospital_option);
                if(obj){
                    select_hospital.val(obj);
                }
            }
        }
    });
}
function departmentList(hId,obj) {//生成科室列表
    var select_department=$(":input[name=tDepartmentId]");
    var department_option="";
    $.ajax({
        type: "get",
        url: web_url+"/department/findDepartmentPage/",
        cache: false,  //禁用缓存
        data: {'tHospitalParentId':hId,"token":token},  //传入组装的参数},
        dataType: "json",
        success: function (result) {
            if(result.code==200) {
                department_option="<option value=''>请选择科室</option>";
                var data = result.data;
                $.each(data, function (key, value) {
                    if(value['tStatus']==0) {
                        department_option += "<option value='" + value['tId'] + "'>" + value['tDepartmentName'] + "</option>";
                    }
                });
                select_department.html(department_option);
                if(obj){
                    select_department.val(obj);
                }
            }
        }
    });
}
function isDirector(hId,dDd) {//主任信息 hId医院id  dDd科室id
    var param = {};
    param['tHospitalId']=hId;
    param['tDepartmentId']=dDd;
    param['token']=token;
    $.ajax({
        type: "get",
        url: web_url+"mlogin/findLoginDirector/",
        cache: false,  //禁用缓存
        data:param,
        dataType: "json",
        success: function (result) {
            var data=result.data;
            if(result.code==200) {
                if(data.length==0){//没有科室主任
                    if(mloginId){//普通医生编辑，默认为否
                        directorEdit('0',false);
                    }

                        $("#director-name").val('无');
                        $("input[name='tDirector']").attr('disabled',false);//可选择
                }
                else{//有科室主任
                    if(mloginId){//有用户id(编辑)
                        if(mloginId!=result.data.tId){//普通医生编辑
                            directorEdit('0',true);
                            $("#director-name").val(result.data.tUserName);//主任名字
                        }
                        else{//主任编辑
                            directorEdit('1',false);
                            $("#director-name").val("");//主任名字
                        }
                    }
                    else{//有科室主任，普通医生（新增）
                        directorEdit('0',true);
                        $("#director-name").val(result.data.tUserName);//主任名字
                    }


                }
                director_id=result.data.tId;
            }
        }
    });
}
function mlogin_cancel(){//取消
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function directorEdit(val,tf) {//是否是主任(0为否，1为是),radio能否选择（false为可选，true为不可选）
    $("input[name='tDirector']").prop("checked",false).parent().removeClass("checked");//初始化checkbox
    $("input[name='tDirector'][value='"+val+"']").prop("checked",true).parent().addClass("checked");
    $("input[name='tDirector']").attr('disabled',tf);
    if(tf==true){//不可选
        $("input[name='tDirector']").parent().addClass("disabled");
    }
    else if(tf==false){//可选
        $("input[name='tDirector']").parent().removeClass("disabled");
    }
}
