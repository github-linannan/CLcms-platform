
var videoTypeId=getQueryString("id");
var token = MyLocalStorage.Cache.get('token');
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(videoTypeId!=null&&videoTypeId!=""){
		$("input[name='tId']").val(videoTypeId);
		 $.ajax({
	         type: "get",
	         url: web_url+ "/videoType/selectByParam",
	         cache: false,  //禁用缓存
	         data: {"tId":videoTypeId,"token":token},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $(":input[name^=t]").each(function(){
	                		  (result.data)[0][this.name]!=null?$(this).val((result.data)[0][this.name]):this.value;
	                	  });
	                 }         
	         }
	     });
	}else{
		 $(":input").each(function(){
   		  $(this).val("");
   	  });
	}
});
function videoType_save(){
	  var  param={};
	  param["token"]=token;
	  $(":input[name^=t]").each(function(v,i){
		  if(this.value!="")
		  param[this.name]=this.value;
	  });
	  var url=videoTypeId>0?url="/videoType/updateVideoType":"/videoType/insertVideoType";
	
 	  $.ajax({
	         type: "post",
	         url: web_url+ url,
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
	                	
	                	
	                 }else{
	 				  layer.msg(result.detailMessage,{icon:1,time:1000});
	                 }         
	         }
	     }); 
	  
}
function videoType_cancel(){
	 var index = parent.layer.getFrameIndex(window.name);
	   parent.layer.close(index);
}