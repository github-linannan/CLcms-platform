/***
 * 科室列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#departmentbtSearch").on("click",function(){
        if(department_datatable!=null){
            department_datatable.fnDraw();
        }
    });
    $("#departmentbtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(department_datatable!=null){
            department_datatable.fnDraw();
        }
    });
    var $sel=$(":input[name=tHospitalParentId]");//生成医院列表
    $.ajax({
        type: "get",
        url: web_url+"hospital/findHospitalPage/",
        cache: false,  //禁用缓存
        data: {"token":token},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if(result.code==200) {
                var data = result.data;
                $.each(data, function (key, value) {
                        $sel.append("<option value='" + value['tId'] + "'>" + value['tHospitalName'] + "</option>");

                });
            }
        }
    });
    department_datatable = $('.table-sort').dataTable({
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
                url: web_url+"/department/findDepartmentPage",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;//返回数据全部记录
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = result.list;//返回的数据列表
                    callback(returnData);
                }
            });
        },
        serverSide: true,
        columns: [
            {"data": "tDepartmentCode"},
            {"data": "tDepartmentName"},
            {"data": "tHospitalName"},
            {"data": "tRemark"},
            {"data": function(data){
                return data.tStatus==0?"启用":"禁用";
            }},
            {"data": "tCreateTime"},
            { "data": function(data){
                return '<a style="text-decoration:none"  href="javascript:department_shenhe('+data.tId+','+data.tStatus+',this);" title="'+(data.tStatus==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(data.tStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:department_edit(\'科室编辑\',\'department-add.html?id='+data.tId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ]
    });


});
/*科室- 添加 编辑*/
function department_edit(title,url,id,w,h){
    /*layer_show(title,url,w,h);*/
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}


/*科室-禁用，启用*/
function department_shenhe(id,status,obj){
    msg=status==0?"禁用":"启用";
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: web_url+'/department/updateByStatus/'+(status==0?-1:0)+'/'+id,
                /*data:{"tId":id,"tStatus":(status==0?-1:0)},*/
                data: {"token":token},  //传入组装的参数
                dataType: 'json',
                success: function(data){
                    if(data.code==200){
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                        department_datatable.fnDraw();
                    }
                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
}


