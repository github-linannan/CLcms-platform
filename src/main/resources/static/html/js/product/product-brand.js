//医生管理js
var hospital_table=null;
$(function(){    
	hospital_table =$("#productBrand").dataTable({
        //language:lang,  //提示信息
        autoWidth: false,  //禁用自动调整列宽
        stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
        processing: true,  //隐藏加载提示,自行处理
        serverSide: true,  //启用服务器端分页
        searching: false,  //禁用原生搜索
        orderMulti: false,  //启用多列排序
        order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
        sDom:'<"top">rt<"bottom"lip><"clear">',
        renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
        pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
        columnDefs: [{
            "targets": 'nosort',  //列的样式名
            "orderable": false    //包含上样式名‘nosort’的禁止排序
        }],
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            //param["startCount"] = data.start;//开始的记录序号
            param["currentPage"] = (data.start/data.length)+1;//当前页码
           // param["goodstypeName"]=$("div.sysusersearch  #state").val();
           // param["username"]=$("div.sysusersearch #user_name").val();
           // param["userstate"]=$("div.sysusersearch #user_state").val();
            //ajax请求数据
            $.ajax({
                type: "GET",
                url: web_url+"/goodsType/findGoodsTypePage",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                        //封装返回数据
                        console.info(result);
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.total;//返回数据全部记录
                        returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.list;//返回的数据列表
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);           
                }
            });
        },
        //列表表头字段
        columns: [
            /*{"data" : "tId",hidden:true},  */
			{"data" : "goodstypeId"} ,
			{"data" : "goodstypeName"} ,
			{"data" : function(data){
				//console.info(data["tStatus"]);
				    if(data["goodstypeStatus"]==0){
				    	return "正常";  
				    }else{
				    	return "禁用";
				    }
				}
			},  
			{"data" : "tCreateTime"},
			{"data" : "tHospitalAddress"} , 
            {"data": "","render": function(data, type, full){
            	var id=full.tId;
            	var html="";
            	if(full.tStatus==0){
            		html+="<a style\=\"text-decoration:none\" onClick=\"member_stop(this,'"+id+"')\" href=\"javascript:;\" title=\"停用\"><i class=\"Hui-iconfont\">&#xe631;</i></a>"
            	}else{
            		html+="<a style\=\"text-decoration:none\" onClick=\"member_start(this,'"+id+"')\" href=\"javascript:;\" title=\"启用\"><i class=\"Hui-iconfont\">&#xe6e1;</i></a>";
            	}
            	 html+=" <a title=\"编辑\" href=\"javascript:;\" onclick=\"member_edit('编辑','hospital-add-edit.html?action=update&&id="+id+"','"+id+"','','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a>";
            	return html;
            } }
        ],
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页显示 _MENU_ 条",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示第 _START_ 至 _END_ 条，共 _TOTAL_ 条",
            "sInfoEmpty": "显示第 0 至 0 条，共 0 条",
            "sInfoFiltered": "(由 _MAX_ 条结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "未搜索到数据",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        }
    });
 
});

//-------------------------------增删改查操作---------------------------------------------
/*医院-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}


/*医院-查看*/
function member_show(title,url,id,w,h){
	
	 
	
	layer_show(title,url,w,h);
}


/*医院-停用*/
function member_stop(obj,id){
	layer.confirm('确认要禁用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: web_url+'hospital/updateByStatus/-1/'+id,
			dataType: 'json',
			success: function(data){
				//console.info(data);
				if(data.code==200){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="禁用"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常</span>');
					$(obj).remove();
					layer.msg('已禁用!',{icon: 6,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
	hospital_table.fnDraw(false);
	//console.info(hospital_table);
	//清除表格数据
	//hospital_table.fnClearTable();
    //重新请求数据（不写参数代表请求初始化时的默认接口数据）
	
}



/*用户-启用*/
function member_start(obj,id){
	console.info(id);
	layer.confirm('确认要启用吗？',function(index){
		$.ajax({
			type: 'POST',
			url: '/hospital/updateByStatus/0/'+id,
			dataType: 'json',
			success: function(data){
				//consoel.info(data);
				if(data.code==200){
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="member_stop(this,id)" href="javascript:;" title="禁用"><i class="Hui-iconfont">&#xe631;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">正常</span>');
					$(obj).remove();
					layer.msg('已正常!',{icon: 6,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});
	});
}


/*用户-编辑*/
function member_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}




/*用户-删除*/
function member_del(obj,id){
	console.info(id);
	layer.confirm('确认要删除吗？',function(index){
		/*	$.ajax({
			type: 'POST',
			url: "",
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data);
			},
		});	*/	
	});
}




