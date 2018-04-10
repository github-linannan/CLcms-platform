/***
 * 综合查询列表
 */
var token = MyLocalStorage.Cache.get('token');
$(function () {
    $("#integratedQuerybtSearch").on("click", function () {
        if (integratedQuery_datatable != null) {
            integratedQuery_datatable.fnDraw();
        }
    });
    $("#integratedQuerybtCanner").on("click", function () {
        $(".search :input[name^=t]").each(function () {
            this.value = "";
        });
        if (integratedQuery_datatable != null) {
            integratedQuery_datatable.fnDraw();
        }
    });
    integratedQuery_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading": false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start / data.length) + 1;//当前页码 */
            $(".search :input[name^=t]").each(function () {
                if (this.value != "") {
                    param[this.name] = this.value;
                }
            });
            param['token'] = token;//token值
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url + "/message/queryByParam",
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
            {"data": "tTestingTime"},
            {"data": "tSiArea"},
            {"data": "tBatchName"},
            {"data": "tSampleNumber"},
            {"data": "tUserName"},
            {"data": "tUserSex"},
            {"data": "tUserTelphone"},
            {"data": "sampleTime"},
            {"data": "tSampleItem"},
            {
                "data": function (data) {
                    return data.tReportStatus == 1 ? '已出报告' : '未出报告';
                }
            },
            {"data": "tLogisticsNumber"},
            {"data": "tSaleName"}/*,
            {"data": "tReportTime"}*/
        ]
    });

//导出excel   export-report
    $("#export-report").on("click",function () {
        $(this).attr('href',web_url+'message/excelExport?token='+token);
    });

});


