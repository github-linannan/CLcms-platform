/***
 * 字典列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#dictionarybtSearch").on("click",function(){
        if(dictionary_datatable!=null){
            dictionary_datatable.fnDraw();
        }
    });
    $("#dictionarybtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(dictionary_datatable!=null){
            dictionary_datatable.fnDraw();
        }
    });
    dictionary_datatable = $('.table-sort').dataTable({
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
                url: web_url+"/typeGroup/findTypeGroupPage",
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
            {"data": "type"},
            {"data": "typeName"},
            {"data": "typeCode"},
            {"data": "typeDesc"},
            { "data": function(data){
                return '<a style="text-decoration:none" class="ml-5 btn btn-primary radius"  href="javascript:dictionary_edit(\'字典编辑\',\'dictionary-add.html?id='+data.id+'\','+data.id+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>编辑</a><a href="javascript:dictionary_del('+data.id+',this);" class="btn btn-danger radius ml-5"><i class="Hui-iconfont">&#xe6e2;</i>删除</a>';
            } }
        ]
    });


});
///字典删除
function  dictionary_del(id,obj){
    layer.confirm('确定删除？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function () {
            $.ajax({
                type: "get",
                url: web_url + "typeGroup/delete/"+id,
                cache: false,  //禁用缓存
                data: {"token":token,"id":id},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if (result.code == 200) {
                        $(obj).parent().parent().addClass('dis-none');
                        layer.msg('删除成功!',{icon:1,time:1000});
                        dictionary_datatable.fnDraw();
                    }
                    else {
                        layer.msg(result.detailMessage);
                    }
                },
                error:function(data) {
                    console.log(data.detailMessage);
                }
            });
        });
}

/*字典-  编辑*/
function dictionary_edit(title,url,id){
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}

