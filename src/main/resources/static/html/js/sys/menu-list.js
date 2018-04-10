/***
 * 资源列表
 */
var menu_datatable="";
var token=MyLocalStorage.Cache.get('token');
$(function () {
    $("#menubtSearch").on("click",function(){
        if(menu_datatable!=null){
            menu_datatable.draw();
        }
    });
    $("#menubtCanner").on("click",function(){
        $(".search :input[name^=t]").each(function(){
            this.value="";
        });
        if(menu_datatable!=null){
            menu_datatable.draw();
        }
    });
    menu_datatable = $(".table-sort").DataTable({
        /****************************************表格数据加载****************************************************/
        "bStateSave": true,//状态保存
        "paging": false,//分页功能
        searching: false,  //禁用原生搜索
        bLengthChange: false,   //去掉每页显示多少条数据方法
        "ordering": false,//排序功能
        "bAutoWidth" : true, //是否自适应宽度
        "info": false,   //去掉底部文字
        ajax: function (data, callback, settings) {
            //ajax请求数据
            $.ajax({
                type: "get",
                url: web_url+"menu/findMenuPage",
                cache: false,  //禁用缓存
                data: {"token":token},  //传入组装的参数
                dataType: "json",
                success: function (result) {
                    var menu_data=[];
                    var result=result.data;
                    $.each(result,function (index,item) {//重新处理json数据，子级与父级并列（插件要求）
                        if(item.tMenuParentid==0){
                            menu_data.push(item);
                            if(item.childrenList.length!=0){
                                $.each(item.childrenList,function (key,value) {
                                    menu_data.push(value);
                                })
                            }
                        }
                    })
                    var returnData = {};
                    returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                    returnData.recordsTotal = result.total;//返回数据全部记录
                    returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                    returnData.data = menu_data;//返回的数据列表
                    callback(returnData);
                }
            });
        },
        /*serverSide: true,*/
        "bServerSide" : true,
        "columns": [//列绑定
            { "data": "tMenuName" },
            { "data": "tMenuUrl" },
            { "data": function (data) {
                return data.tMenuStatus==1? '启用':'禁用';
            } },
            { "data": "tMenuOrdernum" },
            { "data": function(data){
                return '<a style="text-decoration:none" class="ml-5"  href="javascript:menu_edit(\'角色编辑\',\'menu-add.html?id='+data.tMenuId+'\');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ],
        "rowCallback": function (row, data, displayIndex) {//行定义
            if (data.tMenuParentid != "0") {
                $(row).attr("class", "treegrid-" + data.tMenuId + " treegrid-parent-" + data.tMenuParentid);
            } else {
                $(row).attr("class", "treegrid-" + data.tMenuId);
            }
        },
        "initComplete": function (settings, json) { //表格初始化完成后调用
            $(".table-sort").treegrid({
                "initialState": 'expanded',//默认展开
                "treeColumn":0//三角图形所在的列数
            });

        }
        /****************************************表格数据加载****************************************************/
        /****************************************表格样式控制****************************************************/
    });

});
/*初始化table*/
function menu_edit(title,url) {
    var index = layer.open({
        maxmin: true,
        type: 2,
        title: title,
        content: url,
        shadeClose: true //点击遮罩关闭层
    });
    layer.full(index);
}
