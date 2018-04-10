var videoId=getQueryString("id");
var token = MyLocalStorage.Cache.get('token');
var Max_Size = 2000*1024*1024; //2M
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	 $.ajax({
         type: "get",
         url: web_url+ "/videoType/selectByParam",
         cache: false,  //禁用缓存
         async:false,
         data: {"tStatus":"0","token":token},  //传入组装的参数
         dataType: "json",
         success: function (result) {
                 if(result.code==200){
                	 var typeOptions=['<option value="">请选择</option>'];
                	   $(result.data).each(function(v,i){
                		   typeOptions.push('<option value='+this.tId+'>'+this.tType+'</option>');
                	   });
                	 $(':input[name="tTypeId"]').empty().append(typeOptions.join(""));
                 }         
         }
     });

	if(videoId!=null&&videoId!=""){
		$("input[name='tId']").val(videoId);
		 $.ajax({
	         type: "get",
	         url: web_url+ "/video/selectByParam",
	         cache: false,  //禁用缓存
	         data: {"tId":videoId,"token":token},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $(":input[name^=t]").each(function(){
	                		
	                		  (result.data)[0][this.name]!=null?$(this).val((result.data)[0][this.name]):this.value;
	                	  });
	                	  $('#form-video-add #fileList video').attr("src",imageupload_url+(result.data)[0]["tVideoUrl"]);

	                	
	                	 
	                 }         
	         }
	     });
	}else{
		 $(":input").each(function(){
   		  $(this).val("");
   	  });
	}



    var $info=$('#banner-uploadimg .info'),
        $error = $('<p class="error"></p>');

    $('#banner-uploadimg .imgPicker').uploadFile(

        {
            "fileNumLimit":1,
            "extensions":'3gp,mp4,rmvb,mov,avi,m4v',
            "mimeTypes" :'video/*,audio/*,application/*',
            callSuccessBack:function(response){
                $(":input[name='tVideoUrl']").val(response.data);
                $("#banner-uploadimg video").attr("src",imageupload_url+response.data);
            },
            url:imageupload_url+"resource/saveVideo"
        });



});
function video_save(){
	  var  param={};
	  param["token"]=token;
    var errorFlag = false;
    errorFlag =  $("#banner-uploadimg .info p").hasClass("error");
	  $(":input[name^=t]").each(function(v,i){
          if(this.value!=""){
              param[this.name]=this.value;
              param["tType"] = $("select[name='tTypeId']").val();
          }
          param["tVideoUrl"] = $(":input[name='tVideoUrl']").val();
	  });
    var tTitleVal = $(":input[name='tTitle']").val();
    var tVideoClickVal = $(":input[name='tVideoClick']").val();
    /* var optionVal = $(":input[name='tStatus'] option:selected").val();*/
    if (tTitleVal !="" && tTitleVal != undefined && tVideoClickVal !="" && tVideoClickVal!=undefined){
        if (errorFlag == false){
            var url=videoId>0?url="/video/updateVideo":"/video/insertVideo";

            $.ajaxByPost(url, param, function (result) {
                if (result.code === 200) {
                    layer.alert('保存成功',function(index){
                        layer.close(index);
                        parent.window.$('.table-sort').dataTable().fnDraw();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);

                    });
                }else{
                    layer.msg(result.detailMessage,{icon:1,time:1000});
                }

            });
        }
    }else {
        layer.msg("请完善页面信息");
    }

	  
}
function video_cancel(){
	 var index = parent.layer.getFrameIndex(window.name);
	   parent.layer.close(index);
}
