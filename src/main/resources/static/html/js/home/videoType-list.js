/***
 * 视频分类列表
 */
var token = MyLocalStorage.Cache.get('token');
$(function(){
	$("#videoTypebtSearch").on("click",function(){
		if(videoType_datatable!=null){
			videoType_datatable.fnDraw();
		}
	});
	$("#videoTypebtCanner").on("click",function(){
		  $(".search :input[name^=t]").each(function(){
          		this.value="";
          });
		if(videoType_datatable!=null){
			videoType_datatable.fnDraw();
		}
	});
    videoType_datatable = $('.table-sort').dataTable({
    	"bStateSave": true,//状态保存
    	"pading":false,
    	searching: false,  //禁用原生搜索
    	 bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
             param["token"]=token;
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
                url:web_url+ "videoType/selectByParam",
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
            {"data": "tType"},
            {"data": "tTypeInfo"},
            { "data": function(data){
            	return '<a style="text-decoration:none"  href="javascript:videoType_del('+data.tId+');" title="删除"><i class="Hui-iconfont">&#xe631;</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:videoType_edit(\'视频分类编辑\',\'videoType-add.html?id='+data.tId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ]
    });
  
  
});
/*视频分类- 添加 编辑*/
function videoType_edit(title,url,w,h){
	var index = layer.open({
	    maxmin: true,
		type: 2,
		title: title,
		content: url,
	    shadeClose: true //点击遮罩关闭层
	});
	layer.full(index);
}


/*视频分类-禁用，启用*/
function videoType_del(id){
	layer.confirm('确定删除吗？', {
		btn: ['确定','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$.ajax({
			type: 'get',
			url:web_url+ '/videoType/delVideoType/'+id,
			data:{"token":token},
			dataType: 'json',
			success: function(data){
				if(data.code==200){
					   layer.msg('删除成功!',{icon:1,time:1000});
					}else{
						 layer.msg('删除失败!',{icon:1,time:1000});
					}
				videoType_datatable.fnDraw();
			},
			error:function(data) {
				console.log(data.msg);
			}
		});		
	});
}
//上传图片

