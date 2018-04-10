/***
 * 样本报告列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#sampleReportbtSearch").on("click",function(){
        if(sampleReport_datatable!=null){
            sampleReport_datatable.fnDraw();
        }
    });
    $("#sampleReportbtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        $(':input[name=userName]').val('');
        $(':input[name=saleUserName]').val('');
        $(':input[name=batchNumber]').val('');
        if(sampleReport_datatable!=null){
            sampleReport_datatable.fnDraw();
        }
    });
    sampleReport_datatable = $('.table-sort').dataTable({
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
            param['userName']=$(':input[name=userName]').val();//userName值
            param['saleUserName']=$(':input[name=saleUserName]').val();//saleUserName值
            param['batchNumber']=$(':input[name=batchNumber]').val();//saleUserName值
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"sampleReport/selectByParam",
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
            {"data": function(data){
                return data.tBatchType==1?"个人":"团体";
            }},
            {"data": "batchNumber"},
            {"data": "tSampleBatch"},
            {"data": "userName"},
            {"data": "userTelphone"},
            {"data": "saleUserName"},
            {"data": "saleTelphone"},
            {"data": "tLogistics"},
            {"data": "tLogisticsNumber"},
            {"data": function(data){
                return data.tReportStatus==1?"已出报告":"未出报告";
            }},
            {"data": function(data){
                return data.tStatus==0?"未确认":"确认完成";
            }},
            { "data": function(data){
                var report_btn='<button class="btn btn-primary radius mr-10" type="button" onclick="sampleReport_express(\'物流 \',\'\','+data.tId+',\'\',)"><i class="Hui-iconfont">&#xe669;</i> 物流</button>';
                var report_btn2='<button class="btn disabled radius mr-10" type="button"><i class="Hui-iconfont">&#xe669;</i> 物流</button>';
                if(data.tReportStatus==1){//报告已出
                    return data.tStatus==0?report_btn2+'<button class="btn btn-primary radius pt-15" type="button" onclick="sampleReport_sure('+data.tId+','+data.tStatus+',this)"><i class="Hui-iconfont">&#xe6a7;</i> 完成</button>':report_btn2+'<button class="btn disabled radius pt-15" type="button"><i class="Hui-iconfont">&#xe6a7;</i> 完成</button>';
                }
                else{//报告没出
                    return report_btn;
                }
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
/*样本报告- 添加 编辑*/
function sampleReport_edit(title,url,id,w,h){
    /*layer_show(title,url,w,h);*/
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}


/*样本报告-确认完成*/
function sampleReport_sure(id,status,obj){
    msg="确认完成";
    var param = {};
    param['token']=token;//token值
    param['tId']=id;//id
    param['tStatus']=1;//确认状态
    layer.confirm(msg+'?', {
            btn: ['确定','取消'],
            shade: false,
            closeBtn: 0
        },
        function(){
            $.ajax({
                type: 'POST',
                url: web_url+'sampleReport/confirmReportStatus',
                data: param,  //传入组装的参数
                dataType: 'json',
                success: function(data){
                    if(data.code==200){
                        layer.msg(msg+'成功!',{icon:1,time:1000});
                        sampleReport_datatable.fnDraw();
                    }
                    else{
                        layer.msg(data.detailMessage,{icon:1,time:1000});
                    }

                },
                error:function(data) {
                    console.log(data.msg);
                }
            });
        });
}
//添加快递信息
function sampleReport_express(title,url,id,w,h) {
        var index = layer.open({
            maxmin: true,
            type: 2,
            title: title,
            content: 'sampleReport-express-add.html?id='+id,
            shadeClose: true //点击遮罩关闭层
        });
        layer.full(index);
}

