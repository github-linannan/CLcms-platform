/*var ue = UE.getEditor('editor');*/

// var ue = UE.getEditor('editor');
var E = window.wangEditor;
var editor = new E('#editor');
editor.customConfig.uploadImgServer = imageupload_url + 'resource/savemaxpicture';
editor.customConfig.uploadFileName = 'file';
// 将图片大小限制为 3M
editor.customConfig.uploadImgMaxSize = 2 * 1024 * 1024;
// 限制一次最多上传 5 张图片
editor.customConfig.uploadImgMaxLength = 5;
editor.customConfig.uploadImgHooks = {
    customInsert: function (insertImg, result, editor) {
        validate_img(result, function () {
            insertImg(imageupload_url + result.data);
        });
    }
}
editor.create();


var infomationId=getQueryString("id");
var token = MyLocalStorage.Cache.get('token');
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	 $.ajax({
         type: "get",
         url: web_url+ "/infomationType/selectByParam",
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
	
	if(infomationId!=null&&infomationId!=""){
		$("input[name='tId']").val(infomationId);
		 $.ajax({
	         type: "get",
	         url: web_url+ "/infomation/selectByParam",
	         cache: false,  //禁用缓存
	         data: {"tId":infomationId,"token":token},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
	                 if(result.code==200){
	                	  $(":input[name^=t]").each(function(){
	                		
	                		  (result.data)[0][this.name]!=null?$(this).val((result.data)[0][this.name]):this.value;
	                	  });
	                	   $('#infomation-uploadimg #fileList img:first').attr("src",imageupload_url+(result.data)[0]["tHomePic"]);

                         //富文本编辑器
                         var wdetail = (result.data)[0]["tContent"];
                         var re = new RegExp('weditor', "g"); //定义正则表达式
                         var str = wdetail.replace(re, imageupload_url);


                         editor.txt.html(str);
	                 }         
	         }
	     });
	}else{
		 $(":input").each(function(){
   		  $(this).val("");
   	  });
	}
	
	  $('#infomation-uploadimg .imgPicker').uploadFile(
			  {"fileNumLimit":1,
		  callSuccessBack:function(response){
			  $(":input[name='tHomePic']").val(response.data);
		  	},
		  	url:imageupload_url+"resource/savepicture"
		})
    
    
});
function article_save(){
    var isValidate = true;
    $("input","#form-article-add").each(function(){
        isValidate = validate_add(this);
        if(!isValidate){
            return isValidate;
        }
    });
    var  tTypeId = $("select[name='tTypeId']").val();
    var  tContent = editor.txt.html();
	//不是必须字段
    /*if (tTypeId === ""){
        layer.msg("请选择文章类型");
	}*/
    if (tContent === ""){
        layer.msg("请完善文章内容");
	}
   /* if(isValidate && tTypeId != "") {*/
    if(isValidate && tContent != "") {
        var  param={};
        param["token"]=token;
        $(":input[name^=t]").each(function(v,i){
            if(this.value!="")
                param[this.name]=this.value;
        });
        param["tContent"] = tContent;
        /*param["tType"] = $("select[name='tTypeId']").val();*/


       /* var ehtml = editor.txt.html();
        var re = new RegExp(imageupload_url, "g"); //定义正则表达式
        var str = ehtml.replace(re, 'weditor');
        param["tContent"] = str;*/
        var url=infomationId>0?url="/infomation/updateInfomation":"/infomation/insertInfomation";
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
	  
}

//上传图片尺寸，大小验证
function validate_img(a, callback) {
    var file = a.data;
    if (!/.(gif|jpg|jpeg|png|GIF|JPG|png)$/.test(file)) {
        alert("图片类型必须是.gif,jpeg,jpg,png中的一种");
    } else {
        var image = new Image();
        image.src = imageupload_url + file;
        image.onload = function () {
            console.log(image.size);
            var width = image.width;
            var height = image.height;
            console.log("宽：" + width + "高：" + height);
            if (width <= 750 && height < 500) {
                callback();
            } else {
                alert('请上传宽750，高500以内的图片！');
            }
        };
    }
}
function validate_add(ele) {
    var msg = "";
    var value = $(ele).val(),
        name = $(ele).attr('name');

    switch (name) {
        case 'tTitle':
            if (value === "") {
                msg = "请输入标题";
            }
            break;
        case 'tHomePic':
            if (value === "") {
                msg = "请上传一张缩略图";
            }
            break;
    }
    if (msg != "") {//校验失败
        layer.msg(msg);
        $(ele).addClass('border-error');
        return false;
    } else {
        $(ele).removeClass('border-error');
    }
    return true;
}
function article_cancel(){
	 var index = parent.layer.getFrameIndex(window.name);
	   parent.layer.close(index);
}