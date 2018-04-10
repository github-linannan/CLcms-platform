
var orderNumber=getQueryString("id");
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(orderNumber!=null&&orderNumber!=""){
		$("span[name='orderNumber']").val(orderNumber);
		 $.ajax({
	         type: "get",
	         url: web_url+"order/findOrderDetails/"+orderNumber,
	         cache: false,  //禁用缓存
	         data: {"orderNumber":orderNumber,"token":MyLocalStorage.Cache.get('token')},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $("span[name^=order],span[name^=address],span[name^=login],span[name^=jc]").each(
	                	  	function(){
	                	  		(result.data)[$(this).attr("name")]!=null?$(this).text((result.data)[$(this).attr("name")]):this.value;
	                		  }
	                	  );
                         var status= $("span[name=orderStatus]");
                         var dstatus=result.data.orderStatus;
                         switch(dstatus){
                             case 0:
                                 status.text("未支付");
                                 break;
                             case -1:
                                 status.text("支付失败");
                                 break;
                             case -2:
                                 status.text("申请退款");
                                 break;
                             case -3:
                                 status.text("已发货（盒子）");
                                 break;
                             case -9:
                                 status.text("删除订单");
                                 break;
                             case 1:
                                 status.text("支付成功");
                                 break;
                             case 2:
                                 status.text("已发货（盒子）");
                                 break;
                             case 3:
                                 status.text("已回寄");
                                 break;
                             case 4:
                                 status.text("已收到样品");
                                 break;
                             case 5:
                                 status.text("检测中");
                                 break;
                             case 6:
                                 status.text("报告待审核");
                                 break;
                             case 7:
                                 status.text("完成报告");
                                 break;
                         }
                         //订单详情-商品列表
                         var str = '';
                         var obj=result.data.list;
                         for (var i = 0; i < obj.length; i++) {
                             str += '<tr role="row" class="odd"><td>' + obj[i].goodsId + '</td><td>' + obj[i].goodsName + '</td><td>' + obj[i].orderGoodsNum + '</td><td>' + obj[i]. goodsPrice + '</td><td>' + obj[i]. goodsDiscount + '</td><td>' + obj[i]. goodsDiscountPrice + '</td></tr>';
                         }
                         $(".goods-list thead ").after(str);
	                	  //支付成功状态显示 订单状态 0：未支付，1：支付成功，2：已发货（盒子） 3：已回寄，4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。-1支付失败，-2申请退款，-3已退款 -9 删除订单
                     }
                     else {
	                     layer.msg(result.detailMessage);
                    }
	         }
	     });
	}



});
