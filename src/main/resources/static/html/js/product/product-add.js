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

var token=MyLocalStorage.Cache.get('token');
var productId = getQueryString("id");
$(function () {
    $('.skin-minimal input').iCheck({
        checkboxClass: 'icheckbox-blue',
        radioClass: 'iradio-blue',
        increaseArea: '20%'
    });
    if (productId != null && productId != "") {
        $("input[name='goodsId']").val(productId);
        $.ajax({
            type: "get",
            url: web_url + "goods/findGoods/" + productId,
            cache: false,  //禁用缓存
            data: {"goodsId": productId, "token": token},  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    $(":input[name^=good]").each(function () {
                        (result.data)[this.name] != null ? $(this).val((result.data)[this.name]) : this.value;
                        $(this).next('img').attr("src", imageupload_url + (result.data)[this.name]);
                    });
                    $("select[name=goodsTypeId]").val(result.data.goodstypeName);

                    // var disPrice = result.data.goodsPrice * (parseFloat($(":input[name=goodsDiscount]").val()));
                    // console.log(disPrice);
                    // $(":input[name=goodsDiscountPrice]").val(disPrice);

                    //富文本编辑器
                    var wdetail = result.data.goodsinfoDetails;
                    var re = new RegExp('weditor', "g"); //定义正则表达式
                    var str = wdetail.replace(re, imageupload_url);
                    // console.log(str);
                    editor.txt.html(str);

                }
            }
        });
    }
    //图片上传
    $('#package-uploadimg .imgPicker').uploadFile({
        "fileNumLimit": 1,
        calluploadProgress: function (file, uploader,e) {
            var files = file._info;
            if (files.width == 750 && files.height ==490) {
                $(e).find('.info').removeClass('c-red').text('上传成功!');
            } else {
                 $(e).find('.info').addClass('c-red').text('上传失败!');
            }
        },
        "responseInput": ".image",
        url: imageupload_url + "resource/savemaxpicture",
    });
    $('#package-uploadimg #imgPicker1').uploadFile({
        "fileNumLimit": 1, "responseInput": ".image",
        calluploadProgress: function (file, uploader,e) {
            var files = file._info;
            if (files.width == 750 && files.height ==490) {
                $(e).find('.info').addClass('img-success').text('上传成功!');
            } else {
                $(e).find('.info').removeClass('img-success').text('上传失败!');
            }
        },
        url: imageupload_url + "resource/savepicture"
    });
    get_type();
});

//折扣
$(":input[name=goodsDiscount]").change(function () {
    var disPrice = (parseFloat($(":input[name=goodsPrice]").val())) * (parseFloat($(":input[name=goodsDiscount]").val()));
    $(":input[name=goodsDiscountPrice]").val(disPrice.toFixed(2));
});


//获取商品类型
function get_type() {
    // $("select[name='goodsTypeId']").on("click", function () {
    $.ajax({
        type: "get",
        url: web_url + "goodsType/findGoodsTypePage",
        cache: false,  //禁用缓存
        data: {"token": token},  //传入组装的参数
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                for (var i = 0; i < result.data.length; i++) {
                    if(result.data[i].goodstypeStatus==0){
                        var a = '<option value=' + result.data[i].goodstypeId + '>' + result.data[i].goodstypeName + '</option>'
                        console.log(result);
                        $("select[name='goodsTypeId']").append(a);
                    }
                }
            }
        }
        // });
    });
}
function product_save() {
    var isValidate = true;
    $("input","#form-product-add").each(function(){
        // console.log(this);
        isValidate = validate_add(this);
        if(!isValidate){
            return isValidate;
        }
    });
    if(isValidate) {
        var param = {};
        $(":input[name^=good]").each(function (v, i) {
            if (this.value != "")
                param[this.name] = this.value;
        });

        var ehtml = editor.txt.html();
        var re = new RegExp(imageupload_url, "g"); //定义正则表达式
        var str = ehtml.replace(re, 'weditor');
        param["token"] = token;
        param["goodsinfoDetails"] = str;
        var url = productId > 0 ? url = "goods/update/" : "goods/insert";
        $.ajax({
            type: "post",
            url: web_url + url,
            cache: false,  //禁用缓存
            data: param,  //传入组装的参数
            dataType: "json",
            success: function (result) {
                if (result.code == 200) {
                    layer.alert('保存成功', function (index) {
                        layer.close(index);
                        parent.window.$('.table-sort').dataTable().fnDraw();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                } else {
                    layer.msg(msg + '失败!', {icon: 1, time: 1000});
                }
            }
        });
    }
}

//验证新增
// function Discount(obj) {
//     var str = $(obj).val();
//     var re = /^0[1-9]$|^00?\.(?:0[1-9]|[1-9][0-9]?)$|^(?:0[1-9]|[1-9][0-9]?)\.[0-9]$/;
//     alert(re.test(str));
// }
function validate_add(ele) {
    // console.log(ele);
        var msg = "";
        var value = $(ele).val(),
            name = $(ele).attr('name');

        switch (name) {
            case 'goodsName':
                if (value === "") {
                    msg = "请输入商品名称";
                }
                break;
            case 'goodsShortname':
                if (value === "") {
                    msg = "请输入商品简称";
                }
                break;
            case 'goodsPrice':
                if (value === "") {
                    msg = "请输入商品原价";
                }
                break;
            case 'goodsDiscount':
                if (value === "") {
                    msg = "请输入商品折扣";
                }else if(!(value.match(/^0[1-9]$|^00?\.(?:0[1-9]|[1-9][0-9]?)$|^(?:0[1-9]|[1-9][0-9]?)\.[0-9]$/))){
                    msg = "请输入商品折扣0.01-0.99";
                }
                break;
            case 'goodsCounts':
                if (value === "") {
                    msg = "请输入商品库存数量";
                }
                break;
            case 'goodsTypeId':
                if (value === "") {
                    msg = "请选择商品类型";
                }
                break;
            case 'goodsStatus':
                if (value === "") {
                    msg = "请选择商品状态";
                }
                break;
            case 'goodsImageOne':
                console.log(($(ele).siblings('.info').text()));
                if (($(ele).siblings('img').src==='lib/webuploader/0.1.5/images/image.png')) {
                    msg = "请上传第一张商品主图";
                }
               else if (($(ele).siblings('.info').text()==='上传失败!')) {
                    msg = "请上传正确的第一张商品主图";
                }
                break;
            case 'goodsImageTwo'||'goodsImageThree'||'goodsImageFour'||'goodsImage1Five':
                if (($(ele).siblings('.info').text()==='上传失败!')) {
                    msg = "请上传正确的商品主图";
                }
                break;
        }
//                console.log(msg);
        if (msg != "") {//校验失败
            layer.msg(msg);
            $(ele).addClass('border-error');
            return false;
        } else {
            $(ele).removeClass('border-error');
        }
        return true;
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
            var width = image.width;
            var height = image.height;
            console.log("宽：" + width + "高：" + height);
            if (width == 750 && height < 500) {
                callback();
            } else {
                alert('请上传宽750，高500以内的图片！');
            }
        };
    }
}

function product_cancel() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

