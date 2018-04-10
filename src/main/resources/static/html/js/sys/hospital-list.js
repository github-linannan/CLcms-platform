/***
 * 医院列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#hospitalbtSearch").on("click",function(){
        if(hospital_datatable!=null){
            hospital_datatable.fnDraw();
        }
    });
    $("#hospitalbtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(hospital_datatable!=null){
            hospital_datatable.fnDraw();
        }
    });
    hospital_datatable = $('.table-sort').dataTable({
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
                url: web_url+"/hospital/findHospitalPage",
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
            {"data": "tHospitalCode"},
            {"data": "tHospitalName"},
            {"data": function(data){
                return data.tStatus==0?"启用":"禁用";
            }},
            {"data": "tHospitalAddress"},
            {"data": "tRemark"},
            {"data": "tCreateTime"},
            { "data": function(data){
                return '<a style="text-decoration:none"  href="javascript:hospital_shenhe('+data.tId+','+data.tStatus+',this);" title="'+(data.tStatus==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(data.tStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:hospital_edit(\'角色编辑\',\'hospital-add.html?id='+data.tId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ]
    });


});
/*医院- 添加 编辑*/
function hospital_edit(title,url,id,w,h){
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


/*医院-禁用，启用*/
function hospital_shenhe(id,status,obj){
    msg=status==0?"禁用":"启用";
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: web_url+'hospital/updateByStatus/'+(status==0?-1:0)+'/'+id,
                data: {"token":token},  //传入组装的参数
                dataType: 'json',
                success: function(data){
                    if(data.code==200)
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                    hospital_datatable.fnDraw();
                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
}
//上传图片

