
var orderNumber=getQueryString("id");
var pageType=getQueryString("type");
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(orderNumber!=null&&orderNumber!=""){
	    if(pageType){
	        $("input[type=text]").attr("disabled",true);
	        $("#order-detail-save").hide();
        }
		$("span[name='orderNumber']").val(orderNumber);
		 $.ajax({
	         type: "get",
	         url: web_url+"acceptOrder/findAccpectDetails/"+orderNumber,
	         cache: false,  //禁用缓存
	         data: {"token":MyLocalStorage.Cache.get('token')},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $("span[name^=order],span[name^=tS],span[name^=login],span[name^=user],span[name=goodsName]").each(
	                	  	function(){
	                	  		(result.data[0])[$(this).attr("name")]!=null?$(this).text((result.data[0])[$(this).attr("name")]):this.value;
	                		  }
	                	  );
                         var status= $("span[name=orderInfoStatus]");
                         var dstatus=result.data[0].orderInfoStatus;
                         switch(dstatus){
                             case '1':
                                 status.text("未发货");
                                 $('.has-express').hide();
                                 break;
                             case '3':
                                 status.text("已回寄");
                                 $('.has-express').show();
                                 break;
                             case '4':
                                 status.text("已收到样品");
                                 $('.has-express').show();
                                 break;
                             case '5':
                                 status.text("检测中");
                                 $('.has-express').show();
                                 break;
                             case '6':
                                 status.text("报告待审核");
                                 $('.has-express').show();
                                 break;
                             case '7':
                                 status.text("报告完成");
                                 $('.has-express').show();
                                 break;
                         }
	                	  //支付成功状态显示 订单状态 0：未支付，1：支付成功，2：已发货（盒子） 3：已回寄，4：已收到样品，5：检测中，6：报告待审核，7：完成报告 。-1支付失败，-2申请退款，-3已退款 -9 删除订单
                     }
                     else {
	                     layer.msg(result.detailMessage);
                    }
	         }

	     });
        $("#form-orderDetail-return").validate({
            rules:{
                goodsName:{
                    required:true
                },
                tSiProject:{
                    required:true
                },
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                orderDetail_return_save();
            }
        });
	}


});
function orderDetail_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
//确认收货
function orderDetail_return_save() {
    var  param={};
    $("[id^=order],[id=tSampleNumbers],[id=userId]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    param['token']=token;//token值
    param['orderInfoStatus']=4;//状态
    $.ajax({
        type: "post",
        url: web_url+"acceptOrder/update",
        cache: false,  //禁用缓存
        data: param,  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if(result.code==200){
                layer.alert('保存成功',function(index){
                    layer.close(index);
                    parent.window.$('.table-sort').dataTable().fnDraw();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);

                });
            }
            else{
                layer.msg(result.detailMessage,{icon:1,time:1000});
            }
        },
        error:function (err) {
            console.log("错误");
        }
    });
}
