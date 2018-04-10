var token = MyLocalStorage.Cache.get('token');
var batchAddId = getQueryString("id");
get_sale();
get_responsible();

//批次查看
function see_batch() {
    var tit=$(parent.$(".layui-layer-title")[0]).text();
    if(tit==='批次查看'){
        $("#form-report-batch-add form input,select").attr('disabled',true);
        $("#form-report-batch-add form .btn").addClass('dis-none');
    }
}

//编辑页面
$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    if (batchAddId != null && batchAddId != "") {
        $("input[name='tSiId']").val(batchAddId);
        $.ajax({
            type: "get",
            url: web_url + "sampleInformation/findSampleInformation/" + batchAddId,
            cache: false,  //禁用缓存
            data: {"tSiId": batchAddId, "token": token},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    var str='';
                    $(":input[name^=t],select[name^=t]").each(function () {
                        (result.data)[this.name] != null ? $(this).val((result.data)[this.name]) : this.value;
                    });

                    $(":input[name=tSiLabel]").attr('disabled','disabled');
                    jude();
                    //查询患者信息
                    for (var i = 0; i < result.data.list.length; i++) {
                        str += '<tr role="row" class="odd"><td>' + result.data.list[i].userId + '</td><td>' + result.data.list[i].userName + '</td><td>' + result.data.list[i].userTelephone + '</td><td>' + result.data.list[i].tComeNumber + '</td><td>' + result.data.list[i].tSiProject + '</td><td><a class="btn btn-danger radius" href="javascript:;" onclick="sick_delete('+result.data.list[i].userId+',this)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a></td></tr>';
                    }
                    $(".show-sick thead ").after(str);
                    $("select[name='tSaleId']").select2("val",[result.data.tSaleId]);
                    $("select[name='tReciveUserId']").select2("val",[result.data.tReciveUserId]);
                    $("select[name='tUserId']").select2("val",[result.data.tUserId]);
                    see_batch();
                }
                else {
                    layer.msg(result.detailMessage);
                }
                get_jsonP();
                toggle_hide();
            }
        });
    }
});


//获取销售人员列表
function get_sale() {
    // $("select[name='tSaleId']").on("click", function () {
    $.ajax({
        type: "get",
        url: web_url + "mlogin/findLoginPage",
        cache: false,  //禁用缓存
        data: {"token": token,"tUserType":1},  //tId传入组装的参数
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                var a ="<option value=''> 请选择</option>";
                for (var i = 0; i < result.data.length; i++) {
                       a += '<option value=' + result.data[i].tId + '>' + result.data[i].tUserName +"-"+ result.data[i].tTelephone+'</option>';
                }
                $("select[name='tSaleId']").append(a);
            }
            else {
                layer.msg(result.detailMessage);
            }
        }
        });
    // });
}

// 获取负责人列表
function get_responsible() {
    $.ajax({
        type: "get",
        url: web_url + "appUser/selectByParam",
        cache: false,  //禁用缓存
        data: {"token": token},  //tId传入组装的参数
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                var a ="<option value=''> 请选择</option>";
                for (var i = 0; i < result.data.length; i++) {
                    a += '<option value=' + result.data[i].userId + '>' + result.data[i].userName +"-"+ result.data[i].userTelephone+'</option>';
                }
                $("select[name='tUserId']").append(a);
                $("select[name='tReciveUserId']").append(a);
            }
            else {
                layer.msg(result.detailMessage);
            }
        }
    });
}

$(":input[name=tSiLabel]").on("click", function () {
    jude();
});
//判断团体-个人
function jude() {
    if($(":input[name=tSiLabel]").val()==1){
        $(":input[name=tReciveUserId],:input[name=tUserId]").parent().parent().addClass('dis-none');
    }else {
        $(":input[name=tReciveUserId],:input[name=tUserId]").parent().parent().removeClass('dis-none');
    }
}
//获取患者列表信息
$(function () {

    /*批次患者- 添加 编辑*/
    $("#report_batch_add").on("click", function () {
        layer.open({
            maxmin: true,
            type: 1,
            title: '添加患者',
            area: ['90%', '550px'], //宽高
            content: ' <div class="text-c mt-30 search"><input type="text" name="userName" id="" placeholder="患者姓名" style="width:250px" class="input-text"> <input type="text" name="userTelephone" id="" placeholder="患者手机号" style="width:250px" class="input-text"><button name="" id="sickSearch" class="btn btn-success ml-5" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button></div><aside id="addSick" class=""><form id="form" class="mt-20 col-offset-2"><table class="table-sick table table-border table-bordered table-bg table-hover table-sort table-responsive col-xs-8 col-sm-9 zy-pad"><thead id="add-sick-table"> <tr class="text-c"> <th></th> <th>ID</th> <th>姓名</th> <th >手机号码</th> <th>来样编号</th><th>检测项目</th> <th class="dis-none">操作</th> </tr> </thead> </table> </form></aside>',
            shadeClose: true, //点击遮罩关闭层
            success: function (layero, index) {
                add_sick_datatable = $('.table-sick').dataTable({
                    "bStateSave": true,//状态保存
                    "pading": false,
                    searching: false,  //禁用原生搜索
                    bLengthChange: false,   //去掉每页显示多少条数据方法
                    ajax: function (data, callback, settings) {
                        //ids拼接
                        var ids=[];
                        var list= $(".show-sick")[0].children;
                        for(var i=1; i<list.length-1; i++ ){
                            ids.push(list[i].children[0].innerText);
                        }
                        // console.log(ids.join(','));
                        var param = {};
                        param["token"] = token;
                        if(ids!=''){
                            param["ids"]=ids.join(",");
                        }
                        param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
                        param["page"] = (data.start / data.length) + 1;//当前页码 */
                        $(".search :input[name^=user]").each(function(){
                            if(this.value!=""){
                                param[this.name]=this.value;
                            }
                        });
                        $.ajax({
                            type: "get",
                            url: web_url + "appUser/selectByParam",
                            cache: false,  //禁用缓存
                            data: param,  //传入组装的参数
                            dataType: "json",
                            success: function (result) {
                                var returnData = {};
                                returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = result.total;//返回数据全部记录
                                returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果list.length-slength
                                returnData.data = result.list;//返回的数据列表
                                callback(returnData);
                            }
                        });
                    },
                    // editable:true,//开启编辑模式
                    serverSide: true,
                    columns: [
                        {
                            "data": function (data) {
                                return '<input type="checkbox" name="sick">';
                            }
                        },
                        {"data": "userId"},
                        {"data": "userName"},
                        {"data": "userTelephone"},
                        {"data": function (data) {
                            return "<input type='text' class='input-text' value='' placeholder='请输入来样编号' name='tComeNumber' >";//onkeyup='var reg= /^[0-9a-zA-Z]+$/; if(!reg.test(this.value)){this.value='';}'
                        }
                        },
                        {"data": function (data) {
                                return '<input type="text" class="input-text" value="" placeholder="请输入检测项目" name="tSiItem">';
                            }
                        }
                    ]
                });
                if (add_sick_datatable != null) {
                    add_sick_datatable.fnDraw();
                }
                $("#sickSearch").on("click",function(){
                    if(add_sick_datatable!=null){
                        add_sick_datatable.fnDraw();
                    }
                });
            },
            btn: ['保存'],
            btn1: function (index, layero) {
                var isValidate = true;
                var str = '';
                //判断验证
                $("input[type=checkbox]:checked", "#addSick").each(function () {
                    var v = $(this).parent().parent().find('input[type=text]');
                    for(var i=0; i<v.length;i++){
                        isValidate = validate_add(v[i]);
                        if (!isValidate) {
                            return isValidate;
                        }
                }
                });
                if (isValidate) {
                    var obj=sick_add_save();
                    for (var i = 0; i < obj.length; i++) {
                        str += '<tr role="row" class="odd"><td>' + obj[i].id + '</td><td>' + obj[i].name + '</td><td>' + obj[i].tel + '</td><td>' + obj[i].tComeNumber + '</td><td>' + obj[i].item + '</td><td><a class="btn btn-danger radius" href="javascript:;" onclick="sick_delete('+obj[i].id+',this)"><i class="Hui-iconfont">&#xe6e2;</i>删除</a></td></tr>';
                    }
                    // $(str).appendTo(".show-sick thead ");
                    $(".show-sick thead ").after(str);
                    layer.close(index);
                    get_jsonP();
                    toggle_hide();
                    see_batch(); //批次查看
                }
            },
        });
    });
});

//数据展开-收起
function toggle_hide() {
    var len=$("#sickTotal span").text();
    if(len>10){
        $(".show-sick tr").addClass('dis-none');
        for(var i=0;i<12; i++){
            $(".show-sick tr:nth-child("+i+")").removeClass('dis-none');
        }

        $("#hidden-btn").on("click",function () {
            console.log($("#hidden-btn a").text());
            if( $("#hidden-btn a").text()==='查看全部'){
                $(".show-sick  tr").removeClass('dis-none');
                $("#hidden-btn a").text('收起');
            }
            else {
                $(".show-sick tr").addClass('dis-none');
                for(var i=0;i<12; i++){
                    $(".show-sick tr:nth-child("+i+")").removeClass('dis-none');
                }
                $("#hidden-btn a").text('查看全部');
            }

        });
    }

}
//删除选中患者
function sick_delete(id,obj) {
    $(obj).parent().parent().addClass('dis-none');
    $(obj).parent().parent().remove();
    get_jsonP();
}
//获取患者json字符串
function get_jsonP() {
    var jsonP = "[";
    var tip=',';
    var a=[];
   var list= $(".show-sick")[0].children;
   // console.log($(".show-sick")[0].children);
    for(var i=1; i<list.length-1; i++ ){
        if(i===1){tip='';}else{tip=',';}
        jsonP += tip+"{id:\""+list[i].children[0].innerText+"\",tComeNumber:\""+list[i].children[3].innerText+"\",item:\""+list[i].children[4].innerText+"\"}";
        a.push({id:list[i].children[0].innerText});
    }
    $(".hidden-sick").val(jsonP);
    $("#sickTotal span").text(list.length-2);
    jsonP += "]";
    return jsonP;
}
//新增患者-保存
var oldObj=[];
// var jsonT = "[";
function sick_add_save() {
    var obj=[];//新增患者list
    var tabLen = $("#addSick table").find('input[type=checkbox]:checked');
    var tip=',';
    for (var i = 0; i < tabLen.length; i++) {
        var s = {
            id: tabLen[i].parentElement.parentElement.childNodes[1].innerText,
            name:tabLen[i].parentElement.parentElement.childNodes[2].innerText,
            tel: tabLen[i].parentElement.parentElement.childNodes[3].innerText,
            tComeNumber:tabLen[i].parentElement.parentElement.childNodes[4].lastChild.value,
            item: tabLen[i].parentElement.parentElement.childNodes[5].lastChild.value,
        };
        if(i===0){tip='';}else{tip=',';}
        // jsonT += tip+"{id:\""+tabLen[i].parentElement.parentElement.childNodes[1].innerText+"\",item:\""+tabLen[i].parentElement.parentElement.childNodes[4].lastChild.value+"\"}";
        oldObj.push(s);
        obj.push(s);
    }
    // jsonT += "]";
    // console.log(jsonT);
    return obj;
}

//新增批次-保存
function report_batch_add_save() {
    var isValidate = true;
    $("input,select", "#form-report-batch-add").each(function () {
        // console.log(this);
        isValidate = validate_add(this);
        if (!isValidate) {
            return isValidate;
        }
    });
    if (isValidate) {
        var param = {};
        $(":input[name^=t],select[name^=t]").each(function (v, i) {
            if (this.value != "")
                param[this.name] = this.value;
        });
        param["token"] = token;
        param["patient"] = get_jsonP();
        var url = batchAddId > 0 ? url = "sampleInformation/update/" : "sampleInformation/insert";
        $.ajax({
            type: "post",
            url: web_url + url,
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    layer.alert('保存成功', function (index) {
                        layer.close(index);
                        parent.window.$('.table-sort').dataTable().fnDraw();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                } else {
                    layer.msg('新增失败!', {icon: 1, time: 1000});
                }
            }
        });
    }
}

//验证新增批次
function validate_add(ele) {
    // console.log(ele);
    var msg = "";
    var value = $(ele).val(),
        name = $(ele).attr('name');
    switch (name) {
        case 'tSiCreatedate':
            if (value === "") {
                msg = "请输入采样时间";
            }
            break;
        case 'tSiSampleBatch':
            if (value === "") {
                msg = "请输入批次名称";
            }
            break;
        case 'tSiNumber':
            if (value === "") {
                msg = "请输入批次编号";
            }
            else if(!(value.match( /^[0-9a-zA-Z]+$/))){
                msg = "请输入正确的批次编号";
            }
            break;
        case 'tSiArea':
            if (value === "") {
                msg = "请输入区域";
            }
            break;
        case 'tSiLabel':
            if (value === "") {
                msg = "请选择标签";
            }
            break;
        case 'tSiSource':
            if (value==="") {
                msg = "请输入批次来源";
            }
            break;
        case 'tSiItem':
            if (value === "") {
                msg = "检测项目不能为空";
            }
            break;
        case 'tComeNumber':
            if (value === "") {
                msg = "来样编号不能为空";
            }
            else if(!(value.match( /^[0-9a-zA-Z]+$/))){
                msg = "请输入正确的来样编号";
            }
            break;
        case 'tSaleId':
            if (value === "") {
                msg = "请选择销售人员";
            }
            break;
        case 'patient':
            if (value === "") {
                msg = "请选择患者";
            }
            break;
        case 'tUserId':
            if (($(ele).parent().parent().hasClass('dis-none')===false)&&value==="") {
                msg = "请选择发起负责人";
            }
            break;
        case 'tReciveUserId':
            if (($(ele).parent().parent().hasClass('dis-none')===false)&&value==="") {
                msg = "请选择接收负责人";
            }
            break;
        case 'sicklist':
            if (value === "") {
                msg = "请添加患者信息";
            }
            break;
    }
//                console.log(msg);
    if (msg != "") {//校验失败
        layer.msg(detailMessage);
        $(ele).addClass('border-error');
        return false;
    } else {
        $(ele).removeClass('border-error');
    }
    return true;
}

//取消
function report_batch_add_cancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//select2 -接收负责人-发起-销售
//

$(function () {

    $("#send_menu,#get_menu").select2({
        width: '200px',
       placeholder: '请选负责人',
        ajax:{
        url: web_url + "appUser/selectByParam",
        type: 'get',
        dataType: 'json',

        delay: 500,
        cache: true,
        data: function (params) {
            return {
                userName: params.term,
                token: token,
                page: params.page, //第几页返回查询
                number:3
            };
        },
        processResults: function (data, params) {
            // params.page = params.page || 1;
            var rows = data.data;
            for (var i in rows) {
                var row = rows[i];
                row.id = row.userId;
                row.text = row.userName;
                row.tel=row.userTelephone;
            }
            return {
                results: rows,
            };
        }
    },
        escapeMarkup: function (markup) { return markup; },
        templateResult: formatRepo,
        language: "zh-CN",
    });
    $("#sale_menu").select2({
        width: '200px',
        placeholder: '请选择对接销售人',
        language: "zh-CN",
        ajax: {
            url: web_url + "mlogin/findLoginPage",
            type: 'get',
            data: {"token": token,"tUserType":1},
            dataType: 'json',
            delay: 500,
            cache: true,

            processResults: function (data, params) {
//                        console.log(data);
                var rows = data.data;
                for (var i in rows) {
                    var row = rows[i];
                    row.id = row.tId;
                    row.text = row.tUserName;
                    row.tel=row.tTelephone;
                }
                return {
                    results: rows
                };
            }
        },
        escapeMarkup: function (markup) { return markup; },
        templateResult: formatRepo,

    });

});

function formatRepo(repo){
    return repo.text+'-'+repo.tel;
}

