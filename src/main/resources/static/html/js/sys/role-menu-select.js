/***
 * 分配资源
 */
var menu_select_Id=getQueryString("id");
var menu_select_datatable;
var result_data=[];
var menu_data=[];
var token=MyLocalStorage.Cache.get('token');
$(function(){
    menu_select_datatable = $('.table-sort').dataTable({
        /****************************************表格数据加载****************************************************/
        "bStateSave": true,//状态保存
        "paging": false,//分页功能
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        "ordering": false,//排序功能
        ajax: function (data, callback, settings) {
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"menu/findMenuPage",
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    result_data=result.data;
                    $.each(result_data,function (index,item) {//重新处理json数据，子级与父级并列
                        if(item.tMenuParentid==0){
                            menu_data.push(item);
                            if(item.childrenList.length!=0){
                                $.each(item.childrenList,function (key,value) {
                                    menu_data.push(value);
                                })
                            }
                        }
                    });
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;//返回数据全部记录
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = menu_data;//返回的数据列表
                    callback(returnData);
                }
            });
        },
        "columns": [//列绑定
            { "defaultContent": "" },
            { "data": "tMenuName" }

        ],
        "columnDefs": [//列定义
            {
                "targets": [0],
                "data": "tMenuId",
                "render": function (data, type, full) {//全部列值可以通过full.列名获取,一般单个列值用data PS:这里的render是有多少列就执行多少次方法。。。不知道为啥
                    return "<input type='checkbox' value='" + data + "' name='checkList'>";
                }
            }
        ],
        "rowCallback": function (row, data, displayIndex) {//行定义
            if (data.tMenuParentid != "0") {
                $(row).attr("class", "treegrid-" + data.tMenuId + "  treegrid-parent-" + data.tMenuParentid);
            } else {
                $(row).attr("class", "treegrid-" + data.tMenuId);
            }
        },
        "initComplete": function (settings, json) { //表格初始化完成后调用
            $(".table-sort").treegrid({
                "initialState": 'expanded',
            });
            $.ajax({//选中用户已有的角色
                type: "get",
                url: web_url+'roleMenu/findRoleMenu/'+menu_select_Id,
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    var data=result.data;
                    if(result.code==200){
                        $.each(data,function (index,item) {
                            $("input[name=checkList][value='"+item.tMenuId+"']").prop("checked", true);
                        });
                    }
                }
            });
            $.each(result_data,function (index,item) {//checkbox点击事件
               $(".treegrid-"+item.tMenuId).on('click','input',function () {
                   $(".treegrid-parent-"+item.tMenuId).find('input').prop("checked", $(this).prop("checked"));
                });
                  $(".treegrid-parent-"+item.tMenuId).on('click','input',function(){
                      if($(this).prop("checked")){
                          $(".treegrid-"+item.tMenuId).find('input').prop("checked", true);
                      }else{
                          if($(".treegrid-parent-"+item.tMenuId).find("input:checked").length==0){
                              $(".treegrid-"+item.tMenuId).find('input').prop("checked", false);
                          }
                      }
                  });
            });

        }
    });

});
//获取所有选中行的UUID
function menu_select_save(){
    var text="";//选中的checkbox的id
    $('input[name="checkList"]:checked').each(function(){
        if(text==""){
            text+=$(this).val();
        }
        else{
            text+=','+$(this).val();
        }
    });
    if(text && menu_select_Id){
        var param = {};
        param["tRoleId"] = menu_select_Id;//角色id
        param["tMenuIds"] = text;//选中的资源id
        param['token']=token;//token值
        $.ajax({
            type: "post",
            url: web_url+'roleMenu/insert',
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    layer.alert('提交成功',function(index){
                        layer.close(index);
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                }
                else{
                    layer.msg(result.detailMessage,{icon:1,time:1000});
                }
            }
        });
    }
    else{
        layer.alert('请选择资源');
    }
}


function menu_select_cancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}


