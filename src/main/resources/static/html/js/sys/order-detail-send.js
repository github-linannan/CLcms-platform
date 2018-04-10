
var orderNumber=getQueryString("id");
$(function(){
	$('#express_look_btn').on('click',function () {
        var index = layer.open({
            maxmin: true,
            type: 1,
            area: ['80%','500px'],
            title: '物流',
            content: '<div class="new-order-track">\n' +
            '\t\t<div class="img-content">\n' +
            '\t\t\t<i class="Hui-iconfont">&#xe669;</i>\n' +
            '\t\t</div>\n' +
            '\t\t<div>\n' +
            '\t\t\t<span>快递公司：<label>sfgsdfgdgsdfsfgsdfgdgsdf</label></span>\n' +
            '\t\t\t<span>快递单号：<label>000000000000000000001</label></span>\n' +
            '\t\t</div>\n' +
            '\t</div>\n' +
            '\n' +
            '\t<div class="flow-list">\n' +
            '\t\t<div class="new-order-flow new-p-re">\n' +
            '\t\t\t<ul class="new-of-storey">\n' +
            '\t\t\t\t<li key="0">\n' +
            '\t\t\t\t\t<span class="top-white"></span>\n' +
            '\t\t\t\t\t<span class="icon on"></span>\n' +
            '\t\t\t\t\t<span class=\'first\'>\n' +
            '\t\t\t\t\t\t到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7\n' +
            '\t\t\t\t\t\t到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7到哪到哪7\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span class=\'first\'>\n' +
            '\t\t\t\t\t\t2017-01-02\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="1">\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8到哪到哪8\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t\t<span>\n' +
            '\t\t\t\t\t\t2017-01-03\n' +
            '\t\t\t\t\t</span>\n' +
            '\t\t\t\t</li>\n' +
            '\t\t\t\t<li key="2">\n' +
            '\t\t\t\t\t<span class="bottom-white"></span>\n' +
            '\t\t\t\t\t<span class="icon"></span>\n' +
            '\t\t\t\t<span>\n' +
            '\t\t\t\t到哪到哪9到哪到哪9到哪到哪9到哪到哪9到哪到哪9到哪到哪9到哪到哪9到哪到哪9到哪到哪9\n' +
            '\t\t\t\t</span>\n' +
            '\t\t\t\t<span>\n' +
            '\t\t\t\t2017-01-04\n' +
            '\t\t\t\t</span>\n' +
            '\t\t\t</li>\n' +
            '\t\t\t</ul>\n' +
            '\t\t</div>\n' +
            '\t</div>',
            shadeClose: true //点击遮罩关闭层
        });
    });
	if(orderNumber!=null&&orderNumber!=""){
		$("span[name='orderNumber']").val(orderNumber);
		 $.ajax({
	         type: "get",
	         url: web_url+"deliverOrder/findDeliverDetails/"+orderNumber,
	         cache: false,  //禁用缓存
	         data: {"token":MyLocalStorage.Cache.get('token')},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
                 var data=result.data[0];
	                 if(result.code==200){
	                	  $("span[name^=order],span[name^=address],span[name^=login],span[name^=user],span[name=tSampleNumbers]").each(
	                	  	function(){
                                data[$(this).attr("name")]!=null?$(this).text(data[$(this).attr("name")]):this.value;
	                		  }
	                	  );
                         var status= $("span[name=orderStatus]");
                         var dstatus=data.orderStatus;
                         switch(dstatus){
                             case '1':
                                 status.text("支付成功");
                                 $('.has-express').hide();
                                 break;
                             case '2':
                                 status.text("已发货（盒子）");
                                 $('.has-express').show();
                                 break;
                             case '7':
                                 status.text("完成");
                                 $('.has-express').show();
                                 break;
                         }
                         var pay_type=$("span[name=orderPayType]");
                         var dpay_ype=data['orderPayType'];
                         switch(dpay_ype){
                             case '1':
                                 pay_type.text("微信支付");
                                 break;
                             case '2':
                                 pay_type.text("支付宝支付");
                                 break;
                         }
                         //订单详情-商品列表
                         var str = '';
                         var obj=result.data[0].list;
                         for (var i = 0; i < obj.length; i++) {
                             str += '<tr role="row" class="odd"><td>' + obj[i].goodsId + '</td><td>' + obj[i].goodsName + '</td><td>' + obj[i].orderGoodsNum + '</td><td>' + obj[i].orderGoodsPrice + '</td><td>' + obj[i].orderGoodsDiscount + '</td><td>' + obj[i].orderGoodsDiscountPrice + '</td><td>' + obj[i]. orderParticulars + '</td></tr>';
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
function orderDetail_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

