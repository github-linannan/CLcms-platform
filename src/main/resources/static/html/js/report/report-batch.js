/***
 * 样本批次
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
	$("#reportBatchSearch").on("click",function(){
		if(report_batch_datatable!=null){
            report_batch_datatable.fnDraw();
		}
	});
	$("#reportBatchCanner").on("click",function(){
		  $(".search :input[name^=t],:input[name^=sale]").each(function(){
          		this.value="";
              	// $(".search select[name=goodstypeStatus]").val('');
          });
		if(report_batch_datatable!=null){
            report_batch_datatable.fnDraw();
		}
	});


    report_batch_datatable = $('.table-sort').dataTable({
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
	            $(".search :input[name^=t],:input[name^=sale]").each(function(){
	            	if(this.value!=""){
	            		param[this.name]=this.value;
	            	}
	            });
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"sampleInformation/findSampleInformationPage",
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
            {"data": "tSiId"},
            {"data": "tSiNumber"},
            {"data": "tSiSampleBatch"},
            {"data": "tSiArea"},
            {"data": function (data) {
                return data.tSiLabel==1?"个人":"团体";
            }},
            {"data": "userName"},
            {"data": "reciveUeserName"},
            {"data": "saleName"},
            {"data": "tSiCreatedate"},
            { "data": function(data){
            	return '<a href="javascript:report_batch_see(\'批次查看\',\'report-batch-add.html?id='+data.tSiId+'\','+data.tSiId+');" class="btn btn-success radius"><i class="Hui-iconfont">&#xe695; </i>查看</a> <a style="text-decoration:none" class="ml-5 btn btn-primary radius"  href="javascript:report_batch_edit(\'批次编辑\',\'report-batch-add.html?id='+data.tSiId+'\','+data.tSiId+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i>编辑</a><a href="javascript:report_batch_del('+data.tSiId+',this);" class="btn btn-danger radius ml-5"><i class="Hui-iconfont">&#xe6e2;</i>删除</a> ';
            } }
        ]
    });
});
//删除批次
function  report_batch_del(id,obj){
    layer.confirm('确定删除？', {
        btn: ['确定','取消'],
        shade: false,
        closeBtn: 0
    },
    function () {
        $.ajax({
            type: "get",
            url: web_url + "sampleInformation/delete/"+id,
            cache: false,  //禁用缓存
            data: {"token":token,"tSiId":id},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    $(obj).parent().parent().addClass('dis-none');
                    layer.msg('删除成功!',{icon:1,time:1000});
                    report_batch_datatable.fnDraw();
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

/*批次-  编辑*/
function report_batch_edit(title,url,id){
	var index = layer.open({
	    maxmin: true,
		type: 2,
		title: title,
		content: url,
	    shadeClose: true //点击遮罩关闭层
	});
	layer.full(index);
}

/*批次- 查看*/
function report_batch_see(title,url,id){
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}
