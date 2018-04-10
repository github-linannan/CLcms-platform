/***
 * 产品列表
 */

$(function(){
	$("#productbtSearch").on("click",function(){
		if(product_datatable!=null){
            product_datatable.fnDraw();
		}
	});
	$("#productbtCanner").on("click",function(){
		  $(".search :input[name^=good]").each(function(){
          		this.value="";
              	// $(".search select[name=goodstypeStatus]").val('');
          });
		if(product_datatable!=null){
            product_datatable.fnDraw();
		}
	});
	var token=MyLocalStorage.Cache.get('token');
	console.log(token);
    product_datatable = $('.table-sort').dataTable({
    	"bStateSave": true,//状态保存
    	"pading":false,
    	searching: false,  //禁用原生搜索
    	 bLengthChange: false,   //去掉每页显示多少条数据方法
        ajax: function (data, callback, settings) {
            //封装请求参数
            var param = {};
            param["token"]=MyLocalStorage.Cache.get('token');
            param["goodsName"] =$("input[name='goodsName']").val();
             param["pageSize"] = data.length;//页面显示记录条数，在页面显示每页显示多少条的时候
             param["page"] = (data.start/data.length)+1;//当前页码 */
	            $(".search :input[name^=good]").each(function(){
	            	if(this.value!=""){
	            		param[this.name]=this.value;
	            	}
	            });  
            //ajax请求数据
            $.ajax({
                type: "get",
                url:web_url+"goods/findGoodsPage",
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
            {"data": "goodsId"},
            {"data": "goodsName"},
            {"data":function(data,v){
                // var imgStr=(imageupload_url+data.goodsImageOne).split(",");
                // var nstr='';
                // for (var i=0;i<imgStr.length; i++){
                //     if(imgStr[i]!="") {
                //         nstr=imgStr[i];
                //         return '<img src='+ (imageupload_url+nstr)+' style="width:150px;height:100px;">';
                //         // console.log(nstr);
                //     }
                // }
                return '<img src='+ (imageupload_url+data.goodsImageOne)+' style="width:150px;height:100px;">';
            }},
            {"data": "goodsPrice"},
            {"data": "goodsDiscount"},
            {"data": "goodsDiscountPrice"},
            {"data": "goodsCounts"},
            {"data": "goodstypeName"},
            {"data": function(data){
            	return data.goodsStatus==0?"上架":"下架";
            }}, 
            { "data": function(data){
            	return '<a style="text-decoration:none"  href="javascript:product_shenhe('+data.goodsId+','+data.goodsStatus+',this);" title="'+(data.goodsStatus==0?"下架":"上架")+'"><i class="Hui-iconfont">'+(data.goodsStatus==0?"&#xe631;":"&#xe6e1;")+'</i></a><a style="text-decoration:none" class="ml-5"  href="javascript:product_edit(\'资讯编辑\',\'product-add.html?id='+data.goodsId+'\','+data.goodsId+');" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a> ';
            } }
        ]
    });

    get_type();
});
//获取商品类型
function get_type() {
    // $("select[name='goodsTypeId']").on("click", function () {
    $.ajax({
        type: "get",
        url: web_url + "goodsType/findGoodsTypePage",
        cache: false,  //禁用缓存
        data: {"token":MyLocalStorage.Cache.get('token')},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                for (var i = 0; i < result.data.length; i++) {
                    var a='<option value='+result.data[i].goodstypeId+'>' + result.data[i].goodstypeName + '</option>'
                    $("select[name='goodsTypeId']").append(a);
                }
            }
        }
        // });
    });
}
/*资讯- 添加 编辑*/
function product_edit(title,url,id,w,h){
	var index = layer.open({
	    maxmin: true,
		type: 2,
		title: title,
		content: url,
	    shadeClose: true //点击遮罩关闭层
	});
	layer.full(index);
}


/*资讯-禁用，启用*/
function product_shenhe(id,status,obj){
	msg=status==0?"下架":"上架";
	layer.confirm(msg+'？', {
		btn: ['确定','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$.ajax({
			type: 'POST',
			url: web_url+'goods/updateGoodsStatus/'+id+"/"+(status==0?-1:0),
			data:{"goodsId":id,"goodsStatus":(status==0?-1:0),"token":MyLocalStorage.Cache.get('token')},
			dataType: 'json',
			success: function(data){
				if(data.code==200)
				layer.msg(msg+'成功!',{icon:1,time:1000});
				product_datatable.fnDraw();
			},
			error:function(data) {
				console.log(data.msg);
			}
		});		
	});
}
//上传图片

