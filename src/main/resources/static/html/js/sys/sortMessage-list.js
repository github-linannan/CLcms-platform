/***
 * 短信列表
 */
var token=MyLocalStorage.Cache.get('token');
$(function(){
    $("#sortMessagebtSearch").on("click",function(){
        if(sortMessage_datatable!=null){
            sortMessage_datatable.fnDraw();
        }
    });
    $("#sortMessagebtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        $(":input[name=saleUserName]").val('');//销售人
        if(sortMessage_datatable!=null){
            sortMessage_datatable.fnDraw();
        }
    });
    sortMessage_datatable = $('.table-sort').dataTable({
        "bStateSave": true,//状态保存
        "pading":false,
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
            param["page"] = (data.start/data.length)+1;//当前页码 */
            $(".search :input[name^=t],:input[name=saleUserName]").each(function(){
                if(this.value!=""){
                    param[this.name]=this.value;
                }
            });
            param['token']=token;//token值
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"message/selectByParam",
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
            { "data": "tBatchId",
                'targets': 0,
                'searchable':false,
                'orderable':false,
                "createdCell": function (nTd, sData, oData, iRow, iCol) {
                var email_had=1;//有邮箱
                var phone_had=1;//有联系电话
                if(oData.saleEmail=="" || oData.saleEmail==null){
                    email_had=0;
                }
                if(oData.tUserTelphone=="" || oData.tUserTelphone==null){
                    phone_had=0;

                }
                if((oData.tStatus>0 ||oData.tUserTelphone=="" || oData.tUserTelphone==null )&& (oData.tSendEmailTatus==1 ||oData.saleEmail=="" || oData.saleEmail==null)){
                    $(nTd).html("");
                }
                else{
                    $(nTd).html("<input type='checkbox' name='checkList' value='" + oData.tId + "' data='" + oData.tBatchId + "' data-email='" + oData.tSendEmailTatus + "' data-type='" + oData.tType + "' email-had='" + email_had + "' phone-had='" + phone_had + "'>");
                }

                }
            },
            {"data": "tBatchTopic"},
            {"data": function(data){
                return data.tBatchType==1?"个人":"团体";
            }},
            {"data": "tUserName"},
            {"data": "tUserTelphone"},
            {"data": function(data){
                var smg="";
                switch(data.tType){
                    case 0:smg="接收";
                        break;
                    case 1:smg="检测";
                        break;
                    case 2:smg="报告完成";
                        break;
                    default:
                    break;
                }
                return smg;
            }},
            {"data": "tStatus",//短信发送状态
                'targets': 0,
                'searchable':false,
                'orderable':false,
                "createdCell": function (nTd, sData, oData, iRow, iCol) {
                var smg2="";
                switch (sData){
                    case 0:
                        smg2="未发送";
                        break;
                    case 1:
                        smg2="发送成功";
                        $(nTd).css('background', '#e8e8e8');
                        break;
                    case 2:
                        smg2="忽略";
                        break;
                }
                    $(nTd).html(smg2);
                }

            },
            {"data": "saleUserName"},
            {"data": "saleEmail"},
            {"data": "tSendEmailTatus",
                'targets': 0,
                'searchable':false,
                'orderable':false,
                "createdCell": function (nTd, sData, oData, iRow, iCol) {
                    var smg="";
                if(oData.tType==0 || oData.tType==1 || oData.saleEmail=="" || oData.saleEmail==null){
                    smg="——";
                }
                else if(oData.tType==2){//报告完成

                    if(sData==0){
                        smg="未发送";
                    }
                    else if(sData==1){
                        smg="发送成功";
                        $(nTd).css('background', '#e8e8e8');
                    }
                }
                    $(nTd).html(smg);
                }

            },
            {"data": "tLogistics"},
            {"data": "tLogisticsNumber"},
            {"data": "tSendTime"}

        ]
    });


});
/*1发送短信 2忽略短信*/
function sortMessage_send(obj) {
    var text="";//选中的短信行
    var url="";//请求后台链接
    var msg="";//提示信息
    var phone_had=false;//手机号是否为空
    if(obj==1){
        url='message/sendSortMessage';
        msg='发送短信';
    }
    else if(obj==2){
        url='message/ignoreSortMessage';
        msg='忽略短信';
    }
    $('input[name="checkList"]:checked').each(function(){
        if($(this).attr('phone-had')==0){//没有邮箱
            phone_had=true;
        }
        else {
            if (text == "") {
                text += $(this).val();
            }
            else {
                text += ',' + $(this).val();
            }
        }
    });
    if(text){//都是有效的text
        var param = {};
        param["ids"] = text;//选中的id
        param['token']=token;//token值
        $.ajax({//提交选中的角色
            type: "get",
            url: web_url+url,
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    layer.alert(msg+'成功',function(index){
                        layer.close(index);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                    sortMessage_datatable.fnDraw();
                }
                else{
                    layer.msg(result.detailMessage,{icon:1,time:1000});
                }

            }
        });
    }
    else{
        layer.alert('请选择需要'+msg+'的批次');
    }
    if(phone_had){//只要选中的手机号有一个为空，就有提示。不为空的手机号可以发送
        layer.alert('手机号为空，短信不能发送');
    }
}
/*发送邮件*/
function saleMail_send() {
    var text="";//选中需要发送的
    var email_send=false;//未发送的邮件
    var email_had=false;//邮箱是否为空
    var report_had=false;//未出报告
    $('input[name="checkList"]:checked').each(function(){
        if($(this).attr('email-had')==0){//没有邮箱
            email_had=true;
        }
        else{
            if($(this).attr('data-type')==0 || $(this).attr('data-type')==1){//未出报告
                report_had=true;
            }
            else{//已出报告
                if($(this).attr('data-email')==0 && $(this).attr('data-type')==2){//未发邮件
                    if(text==""){
                        text+=$(this).attr('data');
                    }
                    else{
                        text+=','+$(this).attr('data');
                    }
                }
                else if($(this).attr('data-email')==1){//已发邮件
                    email_send=true;
                }
            }
        }
    });
    text=text.split(',').distinct().toString();//生成数组 去重  数组转成字符串
        if(text){
            var param = {};
            param["ids"] = text;//选中的id
            param['token']=token;//token值
            $.ajax({//提交选中的角色
                type: "get",
                url: web_url+'message/sendEmailMessage',
                cache: false,  //禁用缓存
                data: param,  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    if(result.code==200){
                        layer.alert('邮件发送成功',function(index){
                            layer.close(index);
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);

                        });
                        sortMessage_datatable.fnDraw();
                    }
                    else{
                        layer.msg(result.detailMessage,{icon:1,time:1000});
                    }
                }
            });
        }
        else{
            layer.alert('请选择需要发送邮件的批次');
        }

    if(email_send){
        layer.alert('已发送的邮件不能再次发送');
    }
    if(report_had){
        layer.alert('未出报告，邮件不能发送');
    }
    if(email_had){
        layer.alert('邮件为空，邮件不能发送');
    }
}
/*去重*/
Array.prototype.distinct = function (){
    var arr = this,
        result = [];
    arr.forEach(function(v, i ,arr){    //这里利用map，filter方法也可以实现
        var bool = arr.indexOf(v,i+1);    //从传入参数的下一个索引值开始寻找是否存在重复
        if(bool === -1){
            result.push(v);
        }
    })
    return result;
};


