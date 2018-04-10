/***
 * 发货管理列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#sendManagementbtSearch").on("click",function(){
        if(sendManagement_datatable!=null){
            sendManagement_datatable.fnDraw();
        }
    });
    $("#sendManagementbtCanner").on("click",function(){
        $("[name^=order],input[name^=address],input[name=loginTelephone]").each(function(){
            this.value="";
        });
        $("select[name=orderStatus]").val('1,2,7');//默认为全部
        if(sendManagement_datatable!=null){
            sendManagement_datatable.fnDraw();
        }
    });
    sendManagement_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start/data.length)+1;//当前页码 */
            $("[name^=order],input[name^=address],input[name=loginTelephone]").each(function(){
                if(this.value!=""){
                    param[this.name]=this.value;
                }
            });
            param['token']=token;//token值
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"deliverOrder/findDeliverPage",
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    console.log(result);
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
            {"data": "loginNickname"},
            {"data": "loginTelephone"},
            {"data": "orderNumber"},
            {"data": "orderPayTotalPrice"},
            {"data": "orderCreateTime"},
            {"data": "orderPayTime"},
            {"data": "orderStatus",
                'targets': 0,
                'searchable':false,
                'orderable':false,
                "createdCell": function (nTd, sData, oData, iRow, iCol) {
                    /*订单状态 0：未支付，1：支付成功，2：已发货（盒子） 3:确认收货 4：已回寄，5：已收到样品，6：检测中，7：报告待审核，8 报告已审核   9：完成报告，10、纸质已回寄 。-1支付失败，-2申请退款，-3已退款 -9 删除订单*/
                    var smg="";
                    switch(sData){
                        case '1':
                            smg="支付成功";
                            break;
                        case '2':
                            smg="已发货（盒子）";
                            $(nTd).css('background', '#e8e8e8');
                            break;
                        case '9':
                            smg="完成";
                            break;
                        case '10':
                            smg="完成";
                            break;
                    }
                    $(nTd).html(smg);
                }

            },
            {"data": "addressName"},
            {"data": "addressTelephone"},
            {"data": "tSampleNumbers"},
            {"data": "orderShipNumber"},
            {"data": "orderShipTime"},
            { "data": function(data){
                var deail_btn='<button class="btn btn-primary radius mr-10" type="button" onclick="sendManagement_edit(\'订单详情-发货 \',\'order-detail-send.html?id=\','+data.orderNumber+',\'\',)"><i class="Hui-iconfont">&#xe665;</i> 详情</button>';
                var express_btn='<button class="btn btn-primary radius" type="button" onclick="sendManagement_edit(\'物流 \',\'express-look.html?id=\','+data.orderShipNumber+',\'\',)"><i class="Hui-iconfont">&#xe665;</i> 物流</button>';
               if(data.orderStatus==1){//已支付
                    return '<button class="btn btn-primary radius mr-10" type="button" onclick="sendManagement_edit(\'发货 \',\'sendManagement-express-add.html?id=\','+data.orderNumber+',\'\',)"><i class="Hui-iconfont">&#xe669;</i> 发货</button>'+deail_btn;
                }
                else if(data.orderStatus==2){//已发货
                   if(isTwoDay(data.orderShipTime)){//两天以内可以修改
                       return '<button class="btn btn-primary radius mr-10" type="button" onclick="sendManagement_edit(\'修改 \',\'sendManagement-express-add.html?id=\','+data.orderNumber+',\'\',)"><i class="Hui-iconfont">&#xe6df;</i> 修改</button>'+deail_btn+express_btn;
                   }
                   else{//过了两天不能修改
                       return '<button class="btn disabled radius mr-10" type="button"><i class="Hui-iconfont">&#xe6df;</i> 修改</button>'+deail_btn+express_btn;
                   }

                }
                else{//报告完成
                   return '<button class="btn disabled radius mr-10" type="button"><i class="Hui-iconfont">&#xe6df;</i> 修改</button>'+deail_btn+express_btn;
               }
            }
                }

        ]
    });


});
/*发货 物流 订单 编辑*/
function sendManagement_edit(title,url,id,w,h){
    /*layer_show(title,url,w,h);*/
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url+id,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}
function isTwoDay(date){//判断是否超过两天
    var date1 = new Date();
    var date2 = new Date(date);
    var total = (date1-date2)/1000;//毫秒
    var day = parseInt(total / (24*60*60));//计算整数天数
    if(day<2){
        return true;
    }
    else{
        return false;
    }
}

