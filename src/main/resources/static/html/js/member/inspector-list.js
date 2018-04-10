/**
 * Created by linannan on 2018/1/2.
 */
var token = MyLocalStorage.Cache.get('token');
var loginStatus2num={
    0: "未知",
    1: "正常",
    2: "注销",
    3: "锁定"
};
$(function () {

    $("#inspectorListbtSearch").on("click",function(){
        if(inspector_datatable!=null){
            inspector_datatable.fnDraw();
        }
    });
    $("#inspectorListbtCanner").on("click",function(){
        $(".search :input[name^=user]").each(function(){
            this.value="";
        });
        if(inspector_datatable!=null){
            inspector_datatable.fnDraw();
        }
    });
    inspector_datatable = $('.table-sort').dataTable({

        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["token"] = token;
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start/data.length)+1;//当前页码 */
            $(".search :input[name^=user]").each(function(){
                if(this.value!=""){
                    param[this.name]=this.value;
                }
            });
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"appUser/selectByParam",
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
            {"data": "userName"},
            {"data": "userSex"},
            {"data": "userTelephone"},
            {"data": function (data) {
            var userBirthday = data.userBirthday.substring(0,10);
                return userBirthday;
            }},
            {"data": function (data) {
                var address = data.userAddress + data.userAddressInfo;
                return address;
            }},
            {"data": "userRelationship"},
            { "data": function(data){
                return '<a style="text-decoration:none" class="ml-5"  href="javascript:member_show(\'查看\',\'inspector-show.html?id='+data.userId+'\','+data.userId+');" title="查看"><i class="Hui-iconfont">&#xe616;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:inspecto_add(\'编辑\',\'inspector-add.html?id='+data.userId+'\','+data.userId+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>';
            } }
        ]
    });


});


/*user- 修改*/
function user_edit(title,url){
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}

/*用户-查看*/
function member_show(title,url,id,w,h){
    layer_show(title,url,w,h);
}
function change_password(title,url,id,w,h){
    layer_show(title,url,w,h);
}
/*inspecto- 添加 编辑*/
function inspecto_add(title,url){
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}

