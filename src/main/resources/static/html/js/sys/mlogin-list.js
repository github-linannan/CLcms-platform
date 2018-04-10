/***
 * 后台用户列表
 */
var mlogin_datatable;
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#mloginbtSearch").on("click",function(){
        if(mlogin_datatable!=null){
            mlogin_datatable.fnDraw();
        }
    });
    $("#mloginbtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(mlogin_datatable!=null){
            mlogin_datatable.fnDraw();
        }
    });
    mlogin_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        orderMulti: false,  //启用多列排序
        order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
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
            param["token"] = token;
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"mlogin/findLoginPage",
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
            {"data": "tLoginName"},
            {"data": "tUserName"},
            {"data": "tTelephone"},
            {"data": "tEmail"},
            {"data": function(data){
                switch (data.tUserType) {
                    case 0:
                        return "系统用户";
                        break;
                    case 1:
                        return "销售人员";
                        break;
                    case 2:
                        return "医生";
                        break;
                    case 3:
                        return "医院";
                        break;
                }
            }
            },
            {"data": "tInvite"},
            {"data": function(data){
                return data.tStatus==0?"启用":"禁用";
            }},
            {"data": "tCreateTime"},
            {"data": "tCount"},
            { "data": function(data){
                return '<a style="text-decoration:none"  href="javascript:mlogin_shenhe('+data.tId+','+data.tStatus+',this);" title="'+(data.tStatus==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(data.tStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:mlogin_edit(\'用户编辑\',\'mlogin-add.html?id='+data.tId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ],
        initComplete:function(){
            //初始化单选操作
            checkbox("table-sort");

        }
    });


});

function checkbox(tableClass){
    //每次加载时都先清理
    $('.'+tableClass+' tbody').off("click","tr");
    $('.'+tableClass+' tbody').on("click", "tr", function () {
        $(this).siblings('tr').find("input").prop("checked",false)
        $(this).find("input").prop("checked",true);
    });
}


/*后台用户- 添加 编辑*/
function mlogin_edit(title,url,id,w,h){
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


/*后台用户-禁用，启用*/
function mlogin_shenhe(id,status,obj){
    msg=status==0?"禁用":"启用";
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: web_url+'mlogin/updateByStatus/'+(status==0?-1:0)+'/'+id,
                /*data:{"tId":id,"tStatus":(status==0?-1:0)},*/
                data: {"token":token},  //传入组装的参数
                dataType: 'json',
                success: function(data){
                    if(data.code==200)
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                    mlogin_datatable.fnDraw();
                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
}
//用户角色
function mlogin_role(title,url,id,w,h) {
    var uuid=$('input[name="checkList"]:checked').eq(0).val();
    if(uuid){
        var index = layer.open({
            maxmin: true,
            type: 2,
            title: title,
            content: 'mlogin-role-select.html?id='+uuid,
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

