
var productTypeId=getQueryString("id");
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(productTypeId!=null&&productTypeId!=""){
		$("input[name='goodstypeId']").val(productTypeId);
		 $.ajax({
	         type: "get",
	         url: web_url+"goodsType/findGoodsType/"+productTypeId,
	         cache: false,  //禁用缓存
	         data: {"goodstypeId":productTypeId,"token":MyLocalStorage.Cache.get('token')},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $(":input[name^=good]").each(function(){
	                		  (result.data)[this.name]!=null?$(this).val((result.data)[this.name]):this.value;
	                	  });
	                 }         
	         }
	     });
	}
});
function productType_save(){
	  var  param={};
	  $(":input[name^=good]").each(function(v,i){
		  if(this.value!="")
		  param[this.name]=this.value;
	  });
	  var url=productTypeId>0?url="goodsType/update/":"goodsType/insert";
    param["token"]=MyLocalStorage.Cache.get('token');
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
	            	   parent.window.$('.table-sort').dataTable().fnDraw();
              			 var index = parent.layer.getFrameIndex(window.name);
              			   parent.layer.close(index);
	              	  });
	                 }         
	         }
	     }); 
	  
}
function productType_cancel(){
	 var index = parent.layer.getFrameIndex(window.name);
	   parent.layer.close(index);
}