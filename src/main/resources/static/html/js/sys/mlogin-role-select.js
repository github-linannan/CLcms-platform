/***
 * 选择用户角色
 */
var role_select_Id=getQueryString("id");
var role_select_datatable;
var token=MyLocalStorage.Cache.get('token');
$(function(){
    role_select_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start/data.length)+1;//当前页码 */
            $(".search :input[name^=t]").each(function(){
                if(this.value!=""){
                    param[this.name]=this.value;
                }
            });
            param['token']=token;//token值
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"role/findRolePage",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    var returnData = {};
                    var data=[];
                    $.each(result.list,function (index,object) {
                        if(object['tStatus']==0){
                            data.push(object);
                        }
                    });
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;//返回数据全部记录
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = data;//返回的数据列表
                    callback(returnData);
                }
            });
        },
        serverSide: true,
        columns: [
            { "data": "tId",
                'targets': 0,
                'searchable':false,
                'orderable':false,
                "createdCell": function (nTd, sData, oData, iRow, iCol) {
                    $(nTd).html("<input type='checkbox' name='checkList' value='" + sData + "'>");
                }
            },
            {"data": "tRoleName"},
            {"data": "tRemark"},
            {"data": function(data){
                return data.tStatus==0?"启用":"禁用";
            }}
        ],
        initComplete:function(){
            $.ajax({//分配好的角色
                type: "get",
                url: web_url+'loginRole/findLoginRole/'+role_select_Id,
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    var data=result.data;
                    if(result.code==200){
                        $.each(data,function (index,item) {
                            $("input[name=checkList][value='"+item.tRoleId+"']").attr("checked", 'checked');
                        });

                    }
                }
            });
        }
    });

});
//获取所有选中行的UUID
function role_select_save(){
    var text="";//选中的角色
    $('input[name="checkList"]:checked').each(function(){
        if(text==""){
            text+=$(this).val();
        }
        else{
            text+=','+$(this).val();
        }
    });
    if(text){
        var param = {};
        param["tLoginId"] = role_select_Id;//用户id
        param["tRoleIds"] = text;//选中的id
        param['token']=token;//token值
        $.ajax({//提交选中的角色
            type: "post",
            url: web_url+'loginRole/insert',
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){

                    layer.alert('提交成功',function(index){
                        layer.close(index);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                }
                else{
                    layer.msg(result.detailMessage,{icon:1,time:1000});
                }
            }
        });
    }
    else{
        layer.alert('请选择角色');
    }
}


function role_select_cancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}


