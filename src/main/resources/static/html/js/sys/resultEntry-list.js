/***
 * 发货管理列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#resultEntrybtSearch").on("click",function(){
        if(returnManagement_datatable!=null){
            returnManagement_datatable.fnDraw();
        }
    });
    $("#resultEntrybtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        $(':input[name=userName]').val('');
        $(':input[name=saleUserName]').val('');
        $(':input[name=batchNumber]').val('');
        if(resultEntry_datatable!=null){
            resultEntry_datatable.fnDraw();
        }
    });
    resultEntry_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start/data.length)+1;//当前页码 */
            $(".search :input[name^=t]").each(function(){
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
            {"data": "goodsName"},
            {"data": "tSiProject"},
            {"data": "userName"},
            {"data": "userTelephone"},
            {"data": "tSampleNumber"},
            {"data": "orderReturnShipType"},
            {"data": "orderReturnShipNumber"},
            {"data": function (data) {
                var dstatus=data.orderInfoStatus;
                switch(dstatus){
                    case '1':
                        dstatus= "未发货";
                        break;
                }
                return dstatus;
            }},
            { "data": function(data){
                var report_btn='<button class="btn btn-primary radius mr-10" type="button" onclick="resultEntry_sure(\'录入 \',\'resultEntry-add.html?id=\','+data.orderinfoId+',\'\',)"><i class="Hui-iconfont">&#xe600;</i> 录入</button>';
                var report_btn2='<button class="btn btn-primary radius" type="button" onclick="resultEntry_sure(\'详情 \',\'order-detail-return.html?type=1&id=\','+data.orderinfoId+',\'\',)"><i class="Hui-iconfont">&#xe6a7;</i> 详情</button>';
                    return report_btn+report_btn2;
            }
                }

        ],
        initComplete:function(){
            //初始化单选操作
            checkbox("table-sort");

        }
    });


});
function checkbox(tableClass){
    //每次加载时都先清理
    $('.'+tableClass+' tbody').off("click","tr");
    $('.'+tableClass+' tbody').on("click", "tr", function () {
        $(this).siblings('tr').find("input").prop("checked",false)
        $(this).find("input").prop("checked",true);
    });
}

//确认收货
function resultEntry_sure(title,url,id,w,h) {
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url+id,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}
//查看物流
function sendManagement_look(title,url,id,w,h) {
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: 'express-look.html?id='+id,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}


