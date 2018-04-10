/**
 * Created by linannan on 2017/12/22.
 */
var Max_Size = 2000; //2M
var Max_Width = 750; //100px
var Max_Height = 300; //200px
var bannerInfoId=getQueryString("id");
var token = MyLocalStorage.Cache.get('token');
$(function(){
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    var args = {};

    if(bannerInfoId!=null&&bannerInfoId!=""){
        $("input[name='tBannerId']").val(bannerInfoId);
        args = {
            tBannerId: bannerInfoId,
            token: token
        };

        //ajax请求数据
        $.ajax({
            type: "get",
            url:web_url+ "proBanner/selectByParam",
            cache: false,  //禁用缓存
            data: args,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code === 200) {
                    $(":input[name^='t']").each(function(){
                        (result.data)[0][this.name]!=null?$(this).val((result.data)[0][this.name]):this.value;
                    });
                    $('#banner-uploadimg #fileList img:first').attr("src",imageupload_url+(result.data)[0]["tBannerUrl"]);
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
			  {"fileNumLimit":1,
                  calluploadProgress :function(file,uploader){
                      var isAllow = false;
                      var imgWidth = file._info.width;
                      var imgHeight = file._info.height;
                      isAllow = imgWidth >Max_Width || imgHeight > Max_Height;
                          if (isAllow){
                              var text = '图片尺寸不符合规范，请重新上传！';
                              $('#banner-uploadimg .info').html($error.text(text));
                          }else {
                              var text = '上传图片！';
                              $('#banner-uploadimg .info').html(text);
                          }
                     },
              callSuccessBack:function(response){
                  $(":input[name='tBannerUrl']").val(response.data);
                 /* $("#banner-uploadimg video").attr("src",imageupload_url+response.data);*/
                },
                url:imageupload_url+"resource/savemaxpicture"
            });
});


    function banner_save(){
        /*alert("刷新父级的时候会自动关闭弹层。")
         window.parent.location.reload();*/
        var  param={};
            param['token'] = token;
        var errorFlag = false;
        errorFlag =  $("#banner-uploadimg .info p").hasClass("error");
        $(":input[name^=t]").each(function(v,i){
            if(this.value!=""){
                param[this.name]=this.value;
            }
        });
        var tBannerInfoVal = $(":input[name='tBannerInfo']").val();
        var tBannerSortVal = $(":input[name='tBannerSort']").val();
        /* var optionVal = $(":input[name='tStatus'] option:selected").val();*/
        if (tBannerInfoVal !="" && tBannerInfoVal != undefined && tBannerSortVal !="" && tBannerSortVal!=undefined){

            if (errorFlag == false){
                var url=bannerInfoId>0?url="proBanner/updateBanner":"proBanner/insertBanner";
                $.ajaxByPost(url, param, function (result) {
                    if (result.code === 200) {
                        layer.alert('保存成功',function(index){
                            layer.close(index);
                            parent.window.$('.table-sort').dataTable().fnDraw();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);

                        });
                    }else{
                        layer.msg(result.detailMessage);
                    }

                });
            }
        }else {
            layer.msg("请完善页面信息");
        }

    }
