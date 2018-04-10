/**
 * Created by linannan on 2017/12/22.
 */
var token = MyLocalStorage.Cache.get('token');
var param={}, userId;
param['token'] = token;
$(function(){
    if(token!=null&&token!=""){
        $.ajax({
            type: "get",
            url: web_url+"mlogin/findToken/",
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if(result.code==200){
                    var data=result.data;
                    userId= data.tId;
                    $('.dropDown_A span').text(data.tUserName);
                    /*=======个人信息---查看个人信息，修改密码======*/
                    if(userId!=null&&userId!=""){
                      $('#personalInfo').click(function () {
                            layer_show("个人信息","user-info-show.html?id="+userId);
                        });
                        $('#changeUserPwd').click(function () {
                            layer_show("修改密码","change-platform-user-password.html?id="+userId);
                        });

                    }
                }
            }
        });
        $.ajax({//菜单列表
            type: "get",
            url: web_url+"menu/findMenuAll",
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                var data = result.data;
                var dl = "";
                $.each(data, function (index, object) {
                    var child=object['childrenList'];
                    var data_li="";
                    var iconfont_type="";
                    if(child.length!=0) {
                        $.each(child,function (key,value) {
                            data_li+='<li><a data-href="'+value['tMenuUrl']+'" data-title="'+value['tMenuName']+'" href="javascript:void(0)">'+value['tMenuName']+'</a></li>';
                        })
                    }
                    if(object['tMenuType']!=""){
                        iconfont_type='<i class="Hui-iconfont">&#x'+object['tMenuType']+';</i> ';
                    }
                    dl+='<dl id="menu-'+object['tMenuId']+'">' +
                        '<dt>'+iconfont_type+object['tMenuName'] +
                        '<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>' +
                        '<dd><ul>'+data_li+'</ul></dd></dl>';
                });
                $(".menu_dropdown").html(dl);
                $(".Hui-aside").Huifold({//左侧菜单（需重新引用）
                    titCell:'.menu_dropdown dl dt',
                    mainCell:'.menu_dropdown dl dd',
                });
            }

            });
    }else {
        window.location.href='login.html';
    }
    $('#logout').click(function () {
        $.ajax({
            type: "get",
            url: web_url+"mlogin/logout",
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
             if (result.code == 200){
                 MyLocalStorage.Cache.clear();
                 layer.msg('退出成功');
                 window.location.href='login.html';
             }
            }
        });
    });

    /*$("#min_title_list li").contextMenu('Huiadminmenu', {
     bindings: {
     'closethis': function(t) {
     console.log(t);
     if(t.find("i")){
     t.find("i").trigger("click");
     }
     },
     'closeall': function(t) {
     alert('Trigger was '+t.id+'\nAction was Email');
     },
     }
     });*/
});
/*个人信息*/
function myselfinfo(){
    layer.open({
        type: 1,
        area: ['300px','200px'],
        fix: false, //不固定
        maxmin: true,
        shade:0.4,
        title: '查看信息',
        content: '<div>管理员信息</div>'
    });
}

/*资讯-添加*/
function article_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
    layer_show(title,url,w,h);
}



