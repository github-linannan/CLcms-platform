/***
 * 添加物流信息
 */
var orderNumber=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
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
            url: web_url+"deliverOrder/findDeliverDetails/"+orderNumber,
            cache: false,  //禁用缓存
            data: {"token":token},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    var data=result.data[0];
                    $("span[name^=order],span[name^=address],span[name^=login],span[name^=user],[name=tSampleNumbers]").each(
                        function(){
                            data[$(this).attr("name")]!=null?$(this).text(data[$(this).attr("name")]):this.value;
                        }
                    );
                    $("#orderId").val(data['orderId']);
                    $("#orderStatus").val(data['orderStatus']);
                    $("[name=orderShipNumber]").val(data['orderShipNumber']);
                    var status= $("span[name=orderStatusSpan]");
                    var dstatus=data['orderStatus'];
                    switch(dstatus){
                        case '1':
                            status.text("支付成功");
                            break;
                        case '2':
                            status.text("已发货（盒子）");
                            break;
                        case '7':
                            status.text("完成");
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
                    var obj=data.list;
                    for (var i = 0; i < obj.length; i++) {
                        str += '<tr role="row" class="odd"><td>' + obj[i].goodsId + '</td><td>' + obj[i].goodsName + '</td><td>' + obj[i].orderGoodsNum + '</td><td>' + obj[i].orderGoodsPrice + '</td><td>' + obj[i].orderGoodsDiscount + '</td><td>' + obj[i].orderGoodsDiscountPrice + '</td><td>' + obj[i]. orderParticulars + '</td></tr>';
                    }
                    $(".goods-list thead ").after(str);
                }
                else {
                    layer.msg(result.detailMessage);
                }
            }
        });
    }
    $("#form-sendManagement-express").validate({
        rules:{
            orderShipType:{
                required:true
            },
            orderShipNumber:{
                required:true
            },
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            if(isCharNum($("#tSampleNumbers").val())){
                sendManagement_express_save();
            }
            else{
                layer.msg("请输入正确的采样盒子编号(只能是字母、数字、英文逗号)");
            }

        }
    });

});
function sendManagement_express_save(){
    var  param={};
    $("[id^=order],[id=tSampleNumbers]").each(function(v,i){
        if(this.value!="")
            param[this.name]=this.value;
    });
    param['token']=token;//token值
    param['orderStatus']=2;//状态
    $.ajax({
        type: "post",
        url: web_url+"deliverOrder/update",
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
function sendManagement_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function isCharNum(flagValue){
    var flagPattern=/^[0-9a-zA-Z,]+$/; //是否为字母和数字 +英文逗号
    var result=flagPattern.test(flagValue);
    return result;
}