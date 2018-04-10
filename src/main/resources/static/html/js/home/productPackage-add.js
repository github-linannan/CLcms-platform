var token = MyLocalStorage.Cache.get('token');
var productPackageId=getQueryString("id");
var Max_Size = 2000; //2M
var Max_Width = 750; //100px
var Max_Height = 200; //200px
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	if(productPackageId!=null&&productPackageId!=""){
		$("input[name='tPackageId']").val(productPackageId);
		 $.ajax({
	         type: "get",
	         url: web_url+ "/productPackage/selectByParam",
	         cache: false,  //禁用缓存
	         data: {"tPackageId":productPackageId,"token":token},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $(":input[name^=t]").each(function(){
	                		  (result.data)[0][this.name]!=null?$(this).val((result.data)[0][this.name]):this.value;
	                	  });
	                	  var pricturl=(result.data)[0]["tPackageUrl"];
	                	  $(pricturl.split(",")).each(function(i,v){
	                		  $('#package-uploadimg #fileList img:eq('+i+')').attr("src",imageupload_url+v);
	                	  });
	                 }         
	         }
	     });
	}else{
		 $(":input").each(function(){
   		  $(this).val("");
   	  });
	}

    var $info=$('#package-uploadimg .info'),
        $error = $('<p class="error"></p>');
	  $('#package-uploadimg .imgPicker').uploadFile(
			  {"fileNumLimit":1,/*"responseInput":".image",*/
                  calluploadProgress :function(file,uploader){
                      var isAllow = false;
                      var imgWidth = file._info.width;
                      var imgHeight = file._info.height;
                      isAllow = imgWidth >Max_Width || imgHeight > Max_Height;
                      if (isAllow){
                          var text = '图片尺寸不符合规范，请重新上传！';
                          $('#package-uploadimg .info').html($error.text(text));
                      }else {
                          var text = '上传图片！';
                          $('#package-uploadimg .info').html(text);
                      }
                  },
                  callSuccessBack:function(response){
                      $(":input[name='tPackageUrl']").val(response.data);
                  },
		  	url:imageupload_url+"resource/savemaxpicture"
		})
});


function productPackage_save(){
	  var  param={};
	  param["token"]=token;
	  $(":input[name^=t]").each(function(v,i){
		  if(this.value!="")
		  param[this.name]=this.value;
	     });
      var errorFlag =$("#package-uploadimg .info p").hasClass("error");
          var tPackageInfoVal = $(":input[name='tPackageInfo']").val();
          var tPackageSortVal = $(":input[name='tPackageSort']").val();
		  /* var optionVal = $(":input[name='tStatus'] option:selected").val();*/
          if (tPackageInfoVal !="" && tPackageInfoVal != undefined && tPackageSortVal !="" && tPackageSortVal!=undefined){
              if (errorFlag == false){
                  var url=productPackageId>0?url="/productPackage/updateProductPackage":"/productPackage/insertProductPackage";
                  $.ajaxByPost(url, param, function (result) {
                      if (result.code === 200) {
                          layer.alert('保存成功',function(index){
                              layer.close(index);
                              parent.window.$('.table-sort').dataTable().fnDraw();
                              var index = parent.layer.getFrameIndex(window.name);
                              parent.layer.close(index);

                          });
                      }else {
                      	layer.msg(result.detailMessage);
					  }

                  });
              }
          }else {
              layer.msg("请完善页面信息");
          }

	

	  
}
function productPackage_cancel(){
	 var index = parent.layer.getFrameIndex(window.name);
	   parent.layer.close(index);
}