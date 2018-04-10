/***
 * 添加、修改字典
 */
var dictionaryId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var $sel=$(":input[name=type]");//生成类型列表
    $.ajax({
        type: "get",
        url: web_url+"typeGroup/findAllTypeGroup/",
        cache: false,  //禁用缓存
        data: {"token":token},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            console.log(result.data);
            if(result.code==200) {
                var data = result.data;
                $.each(data, function (key, value) {
                    $sel.append("<option value='" + value + "'>" + value + "</option>");
                });
                $sel.append("<option value='其他类型'>其他类型</option>");
                loadInfo();//加载信息
                $('select').change(function() {
                    if($(this).val()=='其他类型'){
                        $(":input[name='type2']").show();
                        $(this).addClass('type-select');
                    }
                    else{
                        $(":input[name='type2']").hide();
                        $(this).removeClass('type-select');

                    }
                })
            }
        }
    });
    function loadInfo() {
        if(dictionaryId!=null&&dictionaryId!=""){
            $("input[name='id']").val(dictionaryId);
            $.ajax({
                type: "get",
                url: web_url+"typeGroup/findTypeGroup/"+dictionaryId,
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if(result.code==200){
                        $(":input[name^=t],:input[name=id]").each(function(){
                            (result.data)[this.name]!=null?$(this).val((result.data)[this.name]):this.value;
                        });
                    }
                }
            });
        }else{
            $(":input").each(function(){
                $(this).val("");
            });
        }
    }

    $("#form-dictionary-add").validate({
        rules:{
            typeName:{
                required:true
            },
            typeCode:{
                required:true
            },
            type:{
                required:true
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            if($(":input[name=type]").val()=='其他类型' && $(":input[name=type2]").val()==""){
                layer.msg('请输入其他类型');
            }
            else{
                dictionary_save();
            }
        }
    });

});
function dictionary_save(){//添加、修改信息
    var  param={};
    $(":input[name^=t],:input[name=id]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    if($(":input[name=type]").val()=='其他类型'){
        param['type']=$(":input[name=type2]").val();
    }
    param['token']=token;//token值
    var url=dictionaryId>0?url="typeGroup/update":"typeGroup/insert";
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
function dictionary_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}