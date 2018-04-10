/**
 * Created by linannan on 2017/12/22.
 */
/*banner 页面*/
var token = MyLocalStorage.Cache.get('token');
$(function () {

    $("#bannerListbtSearch").on("click",function(){
        if(banner_datatable!=null){
            banner_datatable.fnDraw();
        }
    });
    $("#bannerListbtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(banner_datatable!=null){
            banner_datatable.fnDraw();
        }
    });
    banner_datatable = $('#example').dataTable({
            "bStateSave": true,//状态保存
           /* "ajax": test_url+"proBanner/selectByParam",*/
            searching: false,  //禁用原生搜索
       	    bLengthChange: false,   //去掉每页显示多少条数据方法
            ajax: function (data, callback, settings) {
                //封装请求参数
                var param = {};

                param['token'] = token;
                param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
                param["page"] = (data.start/data.length)+1;//当前页码 */
                $(".search :input[name^=t]").each(function(){
                    if(this.value!=""){
                        param[this.name]=this.value;
                    }

                });
                //ajax请求数据
                $.ajax({
                    type: "get",
                    url:web_url+ "proBanner/selectByParam",
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
            "columns": [
                {
                    data: null,
                    title: "<input type='checkbox' name='checklist' id='checkall' />",
                    className: "text-c"
                },

                {
                    data: "tBannerSort",
                    className: "text-c",
                    title: "排序"
                },
                {
                    data: "tBannerId",
                    className: "text-c bannerId",
                    title: "ID"
                },
                {
                    data: "tBannerInfo",
                    className: "text-c",
                    title: "图片名称"
                },
                {
                    data:  function(data){
                    	return '<img src='+ (imageupload_url+data.tBannerUrl)+' style="width:300px;">';
                    },
                    className: "text-c",
                    title: "图片链接"
                },
                {
                    data: "tCreateTime",
                    className: "text-c",
                    title: "创建时间"
                },

                {
                    data: "tStatus",
                    className: "text-c",
                    title: "状态",
                    "render":function(Status){
                        return Status == 0 ? "启用" : "禁用";
                    }

                },
                {
                    data: "tStatus",
                    className: "text-c",
                    title: "操作",
                    "render": function(data, type, full){
                        var id=full.tBannerId, status = full.tStatus;
                        return '<a style="text-decoration:none" class="ml-5"  href="javascript:banner_add(\'图片编辑\',\'banner-add.html?id='+full.tBannerId+'\','+full.tBannerId+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a><a style="text-decoration:none" class="ml-5" href="javascript:banner_change_status('+full.tBannerId+','+full.tStatus+',this);" title="'+(status==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(status==0?"&#xe631;":"&#xe6e1;")+'</i></a> ';
                    }
                }],
            'columnDefs': [{
                'targets': 0,
                'searchable': false,
                'orderable': false,
                'width': '5%',
                'className': 'dt-body-center text-c',
                'textAlign': 'center',
                'render': function (data, type, row, meta){
                    return '<input type="checkbox" name="checklist" value="' + row.id + '" />';
                }
            }],
        });

    //checkbox全选
    $("#checkall").on("click", function () {
        if ($(this).prop("checked") === true) {
            $("input[name='checklist']").prop("checked", $(this).prop("checked"));
            $('#example tbody tr').addClass('selected');
        } else {
            $("input[name='checklist']").prop("checked", false);
            $('#example tbody tr').removeClass('selected');
        }
    });

});



/*banner change status 启用 禁用*/

function banner_change_status(id,status,obj){
    msg=status==0?"禁用":"启用";
    layer.confirm(msg+'？', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            var args = {
                tBannerId : id,
                tStatus: status==0?-1:0,
                token: token
            };
            $.ajaxByPost("proBanner/updateBanner", args, function (result) {
                if (result.code === 200) {
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                        /*window.location.reload();*/
                        banner_datatable.fnDraw();
                }

            });
        });
}


/*banner- 添加 编辑*/
function banner_add(title,url){
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}
