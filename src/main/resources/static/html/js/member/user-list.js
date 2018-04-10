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

    $("#userListbtSearch").on("click",function(){
        if(user_datatable!=null){
            user_datatable.fnDraw();
        }
    });
    $("#userListbtCanner").on("click",function(){
        $(".search :input[name^=login]").each(function(){
            this.value="";
        });
        if(user_datatable!=null){
            user_datatable.fnDraw();
        }
    });
    user_datatable = $('.table-sort').dataTable({

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
            $(".search :input[name^=login]").each(function(){
                if(this.value!=""){
                    param[this.name]=this.value;
                }
            });
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"appLogin/selectByParam",
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
            {"data": "loginTelephone"},
            {"data": "loginRegisterTime"},
            {"data": function(data){
                return loginStatus2num[data.loginStatus]; //1正常， 2注销,3锁定
            }},
            { "data": function(data){
                var htmlStatus;
                if (data.loginStatus==1){
                    htmlStatus = '<a style="text-decoration:none"  class="ml-5" href="javascript:user_change_status('+data.loginId+',3,this);" title="锁定"><i class="Hui-iconfont">&#xe60e;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:user_change_status('+data.loginId+',2,this);" title="注销"><i class="Hui-iconfont">&#xe6dd;</i></a>';
                }else if (data.loginStatus== 2){
                    htmlStatus = '<a style="text-decoration:none" class="ml-5"  href="javascript:user_change_status('+data.loginId+',1,this);" title="正常"><i class="Hui-iconfont">&#xe6e1;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:user_change_status('+data.loginId+',3,this);" title="锁定"><i class="Hui-iconfont">&#xe60e;</i></a>';
                }else if (data.loginStatus== 3){
                    htmlStatus = '<a style="text-decoration:none" class="ml-5"  href="javascript:user_change_status('+data.loginId+',1,this);" title="正常"><i class="Hui-iconfont">&#xe6e1;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:user_change_status('+data.loginId+',2,this);" title="注销"><i class="Hui-iconfont">&#xe6dd;</i></a>';
                }else {
                    htmlStatus = '<a style="text-decoration:none" class="ml-5"  title="其它"><i class="Hui-iconfont">&#xe631;</i></a>';
                }
                return htmlStatus+'<a style="text-decoration:none" class="ml-5"  href="javascript:member_show(\'查看\',\'member-show.html?id='+data.loginId+'&status='+data.loginStatus+'\','+data.loginId+');" title="查看"><i class="Hui-iconfont">&#xe616;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:change_password(\'修改密码\',\'change-password.html?id='+data.loginId+'&pwd='+data.loginPassword+'\','+data.loginId+');" title="修改密码"><i class="Hui-iconfont">&#xe63f;</i></a> ';
            } }
        ]
    });


});


/*user change status 1正常， 2注销,3锁定*/

function user_change_status(id,status,obj){
    msg=loginStatus2num[status];
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            var args = {
                loginId : id,
                loginStatus: status,
                token: token
            };
            $.ajaxByPost("appLogin/updateAppLogin/", args, function (result) {
                if (result.code === 200) {
                    layer.msg(msg+'成功!',{icon:1,time:1000});
                    /*window.location.reload();*/
                    user_datatable.fnDraw();
                }

            });
        });
}

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


