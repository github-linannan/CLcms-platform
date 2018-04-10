/***
 * 样本导入
 */
var token = MyLocalStorage.Cache.get('token');
$(function () {
    $("#reportImportSearch").on("click", function () {
        if (report_import_datatable != null) {
            report_import_datatable.fnDraw();
        }
    });
    $("#reportImportCancer").on("click", function () {
        $(".search :input[name^=t],:input[name^=sale],select[name=tStatus]").each(function () {
            this.value = "";
            // $(".search select[name=goodstypeStatus]").val('');
        });
        if (report_import_datatable != null) {
            report_import_datatable.fnDraw();
        }
    });

    report_import_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading": false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["token"] = token;
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start / data.length) + 1;//当前页码 */
            $(".search :input[name^=t]").each(function () {
                if (this.value != "") {
                    param[this.name] = this.value;
                }
            });
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url + "excelSample/findExcelSamplePage",
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
            {"data": "tSiLable"},
            {"data": "tSiArea"},
            {"data": "tSiSource"},
            {"data": "tComeNumber"},
            {"data": "tUserName"},
            {"data": "tUserSex"},
            {"data": "tUserTelphone"},
            {"data": "tTestingTime"},
            {"data": "tSiProject"},
            {"data": "tSiNumber"},
            {"data": "tSaleName"},
            {"data": "tReportTime"},
            {"data": "tLogisticsNumber"},
             /*{"data": "tTelephone"},*/
            {"data": function(data){
                return data.tStatus==1?"未处理":"已处理";
            }},
        ]
    });


//上传
    $("#upload-report").click('on',function () {

        var formData = new FormData();
        formData.append("file", $("#upfile")[0].files[0]);
        // formData.append("token",token);
        $.ajax({
            url: web_url + 'excelSample/importExcel?token='+token,
            type: 'post',
            data: formData,
// 告诉jQuery不要去处理发送的数据
            processData: false,
// 告诉jQuery不要去设置Content-Type请求头
            contentType: false,
            success: function (data) {
                if (data.code == 200) {
                    if (report_import_datatable != null) {
                        report_import_datatable.fnDraw();
                    }
                    layer.msg(data.detailMessage);
                }else {
                    layer.msg(data.detailMessage);
                }
            }
        });
    });

    //下载模板
    $("#down-temp").on("click",function () {
        $(this).attr('href',web_url+'excelSample/downloadTemplate?token='+token);
    });


});

