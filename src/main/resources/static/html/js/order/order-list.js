/***
 * 订单列表
 */

$(function(){
	$("#orderbtSearch").on("click",function(){
		if(order_datatable!=null){
            order_datatable.fnDraw();
		}
	});
	$("#orderbtCanner").on("click",function(){
		  $(".search :input[name^=good],.search select[name^=order]，.search :input[name^=login]").each(function(){
              this.value="";
              $(".search select[name^=order]").val('');
              $(".search input[name^=login]").val('');
          });
		if(order_datatable!=null){
            order_datatable.fnDraw();
		}
	});
    order_datatable = $('.table-sort').dataTable({
    	"bStateSave": true,//状态保存
    	"pading":false,
    	searching: false,  //禁用原生搜索
    	 bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            var url=window.location.href;
            var slc=$("select[name='orderStatus']").val();
            // console.log(slc);
            if(url.indexOf('order-list-pay')<0){
                // console.log('未支付'+slc);
                if(slc=='0') param["orderStatus_"] = '0';
                else if(slc=='-1') param["orderStatus_"] = '-1';
                else param["orderStatus_"] = '0,-1';
            }
            else {
                // console.log('已支付'+slc);
                var dstatus="";
                switch(slc){
                    case '':
                        dstatus= '1,2,3,4,5,6,7';
                        break;
                    case '1':
                        dstatus='1';
                        break;
                    case '2':
                        dstatus= "2";
                        break;
                    case'3':
                        dstatus= "3";
                        break;
                    case '4':
                        dstatus  = "4";
                        break;
                    case '5':
                        dstatus  = "5";
                        break;
                    case '6':
                        dstatus  = "6";
                        break;
                    case '7':
                        dstatus= "7";
                        break;
                }
                param["orderStatus_"]=dstatus;
                // param["orderStatus_"] = '1,2,3,4,5,6,7';//0：未支付，1：支付成功，2：已发货（盒子） 3：已回寄，4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。-1支付失败，-2申请退款，-3已退款 -9 删除订单
            }
            param["token"]=MyLocalStorage.Cache.get('token');
            param["goodsName"] =$("input[name='goodsName']").val();
            param["loginNickname"] =$("input[name='loginNickname']").val();
            // console.log($("input[name='goodsName']").val());
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
             param["page"] = (data.start/data.length)+1;//当前页码 */
	            $(".search :input[name^=order]").each(function(){
	            	if(this.value!=""){
	            		param[this.name]=this.value;
	            	}
	            });  
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"order/findOrderPage",
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
            {"data": "orderId"},
            {"data": "orderNumber"},
            {"data": "loginNickname"},
            {"data": "loginTelephone"},
            {"data": "orderPayTotalPrice"},
            {"data": "orderCreateTime"},
            {"data": "orderPayTime"},
            {"data": function(data){
                var dstatus=parseInt(data.orderStatus);
                switch(dstatus){
                    case 0:
                        dstatus= "未支付";
                        break;
                    case -1:
                        dstatus= "支付失败";
                        break;
                    case -2:
                        dstatus= "申请退款";
                        break;
                    case -3:
                        dstatus= "已退款";
                        break;
                    case -9:
                        dstatus=  "删除订单";
                        break;
                    case 1:
                        dstatus="支付成功";
                        break;
                    case 2:
                        dstatus= "已发货（盒子）";
                        break;
                    case 3:
                        dstatus= "已回寄";
                        break;
                    case 4:
                        dstatus  = "已收到样品";
                        break;
                    case 5:
                        dstatus  = "检测中";
                        break;
                    case 6:
                        dstatus  = "报告待审核";
                        break;
                    case 7:
                        dstatus  = "完成报告";
                        break;
                }
                return dstatus;
            }},
            { "data": function(data){
                var url=window.location.href;
                if(url.indexOf('order-list-pay')>=0){
                    return '<a style="text-decoration:none" class="ml-5"  href="javascript:order_detail(\'订单详情-已支付\',\'order-detail-pay.html?id='+data.orderId+'\','+data.orderId+');" title="详情"><i class="Hui-iconfont">&#xe6df;</i></a> ';
                }else {
                    return '<a style="text-decoration:none" class="ml-5"  href="javascript:order_detail(\'订单详情-未支付\',\'order-detail-not-pay.html?id='+data.orderId+'\','+data.orderId+');" title="详情"><i class="Hui-iconfont">&#xe6df;</i></a> ';
                }
            } }
        ]
    });

});

/*订单- 详情*/
function order_detail(title,url,id,w,h){
	var index = layer.open({
	    maxmin: true,
		type: 2,
		title: title,
		content: url,
	    shadeClose: true //点击遮罩关闭层
	});
	layer.full(index);
}



