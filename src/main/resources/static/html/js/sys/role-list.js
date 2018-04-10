/***
 * 角色列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#rolebtSearch").on("click",function(){
        if(role_datatable!=null){
            role_datatable.fnDraw();
        }
    });
    $("#rolebtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(role_datatable!=null){
            role_datatable.fnDraw();
        }
    });
    role_datatable = $('.table-sort').dataTable({
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
            }},
            {"data": "tCreateTime"},
            { "data": function(data){
                return '<a style="text-decoration:none"  href="javascript:role_shenhe('+data.tId+','+data.tStatus+',this);" title="'+(data.tStatus==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(data.tStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:role_edit(\'角色编辑\',\'role-add.html?id='+data.tId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ],
        initComplete:function(){
            //初始化单选操作
            checkbox("table-sort");

        }
    });


});
/*角色- 添加 编辑*/
function role_edit(title,url,id,w,h){
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


/*角色-禁用，启用*/
function role_shenhe(id,status,obj){
    msg=status==0?"禁用":"启用";
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: web_url+'role/updateByStatus/'+(status==0?-1:0)+'/'+id,
                data: {"token":token},  //传入组装的参数
                dataType: 'json',
                success: function(data){
                    if(data.code==200)
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                    role_datatable.fnDraw();
                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
}
function checkbox(tableClass){
    //每次加载时都先清理
    $('.'+tableClass+' tbody').off("click","tr");
    $('.'+tableClass+' tbody').on("click", "tr", function () {//只能选中一个
        $(this).siblings('tr').find("input").prop("checked",false);
        $(this).find("input").prop("checked",true);
    });
}
function role_menu(title,url,id,w,h) {
    var uuid=$('input[name="checkList"]:checked').eq(0).val();//选中的对象，只有一个
    if(uuid){
        var index = layer.open({
            maxmin: true,
            type: 2,
            title: title,
            content: 'role-menu-select.html?id='+uuid,
            shadeClose: true //点击遮罩关闭层
        });
        layer.full(index);
    }
    else{
        layer.confirm("请选择数据", {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        });
    }
}

