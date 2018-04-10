/***
 * 添加、修改资源
 */
var menuId=getQueryString("id");
var token=MyLocalStorage.Cache.get('token');
$(function(){
    parentList();//加载选择父级列表
    $(".tree-select-top").on('click',function () {//显示下拉框
        $(this).siblings(".tree-select-cont").toggle();
    });
    $(".tree-list-icon li").on('click',function () {//选择图标
        $(".tree-select-icon").html($(this).html());
        $(".tree-list-icon").parent().hide();
        $("input[name='tMenuType']").val($(this).find('i').attr('data'));
    });
    $(".tree-select-close").on('click',function () {//关闭下拉框
      $(this).parent().hide();
        if($(".tree-select-text").html()==""|| $(".tree-select-text").html()==0){//没有父级，显示菜单图标选择
            $("#menu-icon").show();
        }
        else{//有父级，隐藏菜单图标选择
            $("#menu-icon").hide();
        }
    });
    if(menuId!=null&&menuId!=""){
        $("input[name='tMenuId']").val(menuId);
        $.ajax({
            type: "get",
            url: web_url+"menu/findMenu/"+menuId,
            cache: false,  //禁用缓存
            data: {"token":token},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    $(":input[name^=t]").each(function(){
                        (result.data)[this.name]!=null?$(this).val((result.data)[this.name]):this.value;
                    });
                    if((result.data)['tMenuParentid']==0){//本身是父级，赋值图标
                        $(".tree-select-icon").html('<i class="Hui-iconfont" data="'+(result.data)['tMenuType']+'">&#x'+(result.data)['tMenuType']+';</i>');
                    }
                    else{//本身是子级别，图标隐藏
                        $("#menu-icon").hide();
                    }
                }
            }
        });
    }else{
        $(":input").each(function(){
            $(this).val("");
        });
        $("input[name='tMenuParentid']").val('0');//父级默认为空
    }
    $("#form-menu-add").validate({
        rules:{
            tMenuName:{
                required:true
            }
        },
        onkeyup:false,
        focusCleanup:true,
        success:"valid",
        submitHandler:function(form){
            menu_save();
        }
    });
});
function menu_save(){
    var  param={};
    $(":input[name^=t]").each(function(v,i){
        if(this.value!="" || this.name=="tMenuUrl"){
            param[this.name]=this.value;
        }
    });
    param['token']=token;//token值
    var url=menuId>0?url="menu/update":"menu/insert";
    $.ajax({
        type: "post",
        url: web_url+url,
        cache: false,  //禁用缓存
        data: param,  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if(result.code==200){
                layer.alert('保存成功',function(index){
                    layer.close(index);
                    parent.location.reload();
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
function menu_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function parentList() {//加载选择父级列表
    $.ajax({//选择父级列表
        type: "get",
        url: web_url+"menu/findMenuPage",
        cache: false,  //禁用缓存
        data: {"token":token},  //传入组装的参数
        /*data: {"tId":menuId},  //传入组装的参数*/
        dataType: "json",
        success: function (result) {
            var data=result.data;
            var parent_list="";
            $.each(data,function (index,item) {
                if(menuId){//显示父级名称
                    if($("input[name='tMenuParentid']").val()==item.tMenuId){
                        $(".tree-select-text").html(item.tMenuName);
                    }

                }
                var parent_li="";//父级加子级所有li
                if(item.childrenList.length!=0){
                    var child_list_data=item.childrenList;
                    var child_list="";
                    $.each(child_list_data,function (key,value) {
                        child_list+='<li class="tree-list-li" data-id="'+value.tMenuParentid+'"><span>'+value.tMenuName+'</span></li>';//子级列表
                    });
                    parent_li='<li class="tree-list-parent"><div class="tree-list-li" data-id="'+item.tMenuId+'"><img src="lib/jquery.treegrid/img/collapse.png"><span>'+item.tMenuName+'</span></div><ul class="tree-list-child">'+child_list+'</ul></li>';////父级加子级所有li
                }
                else{//没有子级
                    parent_li='<li class="tree-list-parent"><div class="tree-list-li" data-id="'+item.tMenuId+'"><label class="span-width16"></label><span>'+item.tMenuName+'</span></div></li>';
                }
                parent_list+=parent_li;
            });
            $(".tree-list-ul").html(parent_list);
            $(".tree-list-parent img").click(function(event) {//选择父级li收缩与展开
                var img_before="lib/jquery.treegrid/img/expand.png";
                var img_after="lib/jquery.treegrid/img/collapse.png";
                if($(this).attr("src")==img_before){
                    $(this).attr("src",img_after);
                }
                else if($(this).attr("src")==img_after){
                    $(this).attr("src",img_before);
                }
                $(this).parent().siblings('ul').slideToggle();
                return false;//阻止冒泡
            });
            $(".tree-list-li").on('click',function () {//选择父级
                $(".tree-select-text").html($(this).find('span').html());
                $(".tree-select-cont").hide();
                $("input[name='tMenuParentid']").val($(this).attr("data-id"));
                if($("input[name='tMenuParentid']").val()!=0){
                    $("#menu-icon").hide();
                }
                else{//没有父级，显示菜单图标选择
                    $("#menu-icon").show();
                }
            });

        }
    });
}