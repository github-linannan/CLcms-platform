/***
 * 资讯列表
 */
var token = MyLocalStorage.Cache.get('token');
$(function(){
	$("#infomationbtSearch").on("click",function(){
		if(infomation_datatable!=null){
			infomation_datatable.fnDraw();
		}
	});
	$("#infomationbtCanner").on("click",function(){
		  $(".search :input[name^=t]").each(function(){
        		this.value="";
        });
		if(infomation_datatable!=null){
			infomation_datatable.fnDraw();
		}
	});
	
    infomation_datatable = $('.table-sort').dataTable({
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
	            $(".search :input[name^=t]").each(function(){
	            	if(this.value!=""){
	            		param[this.name]=this.value;
	            	}
	            });  
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+ "/infomation/selectByParam",
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
            {"data": "tTitle"},
            {"data": "tStitle"},
            {"data": "tSource"},
            {"data": "tAuthor"}, 
            {"data": function(data){
            	return data.tStatus==0?"启用":"禁用";
            }}, 
            { "data": function(data){
            	return '<a style="text-decoration:none"  href="javascript:article_shenhe('+data.tId+','+data.tStatus+',this);" title="'+(data.tStatus==0?"禁用":"启用")+'"><i class="Hui-iconfont">'+(data.tStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:article_edit(\'资讯编辑\',\'article-add.html?id='+data.tId+'\','+data.tId+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ]
    });
  
  
});
/*资讯- 添加 编辑*/
function article_edit(title,url,id,w,h){
	var index = layer.open({
	    maxmin: true,
		type: 2,
		title: title,
		content: url,
	    shadeClose: true //点击遮罩关闭层
	});
	layer.full(index);
}


/*资讯-禁用，启用*/
function article_shenhe(id,status,obj){
	msg=status==0?"禁用":"启用";
	layer.confirm(msg+'？', {
		btn: ['确定','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$.ajax({
			type: 'POST',
			url: web_url+ '/infomation/updateInfomation',
			data:{"tId":id,"tStatus":(status==0?-1:0),"token": token},
			dataType: 'json',
			success: function(data){
				if(data.code==200){
					   layer.msg(msg+'成功!',{icon:1,time:1000});
					}else{
						 layer.msg(msg+'失败!',{icon:1,time:1000});
					}
				infomation_datatable.fnDraw();
			},
			error:function(data) {
				console.log(data.msg);
			}
		});		
	});
}
//上传图片

