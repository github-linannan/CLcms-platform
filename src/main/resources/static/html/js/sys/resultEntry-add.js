
/*var resultEntryNumber=getQueryString("id");*/
var resultEntryNumber='S56787656';
var token=MyLocalStorage.Cache.get('token');
$(function(){
	$('#resultEntrybtAdd').on('click',function () {
        str = '<tr role="row" class="odd"><td>'+resultEntryNumber+'</td><td><input type="text" placeholder="结果编码" class="input-text"></td><td><input type="text" placeholder="值" class="input-text"></td><td><a class="btn btn-danger radius" data-title="删除 " onclick="resultEntryDel(this)" href="javascript:;"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a></td></tr>';
        $(".goods-list tbody").append(str);
    });
	if(resultEntryNumber!=null&&resultEntryNumber!=""){
		 $.ajax({
	         type: "get",
	         url: web_url+"report/findReport/",
	         cache: false,  //禁用缓存
	         data: {"token":token,"sampleNumber":resultEntryNumber},  //传入组装的参数
	         dataType: "json",
	         success: function (result) {
                 console.log(result);
	                 if(result.code==200){
                         //结果录入列表
                         var str = '';
                         var data=result.data;
                         data.forEach(function (t) {
                             str += '<tr role="row" class="odd"><td>'+t['sampleNumber']+'</td><td><input type="text" placeholder="结果编码" class="input-text" value="'+t['geneCode']+'"></td><td><input type="text" placeholder="值" class="input-text" value="'+t['reportValue']+'"></td><td><a class="btn btn-danger radius" data-title="删除 " onclick="resultEntryDel(this)" href="javascript:;"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a></td></tr>';
                         })

                         $(".goods-list thead ").after(str);
                     }
                     else {
	                     layer.msg(result.detailMessage);
                    }
	         }
	     });
	}


});
function resultEntryDetail_cancel(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function resultEntryDel(obj) {
   $(obj).parent().parent().remove();
}
function resultEntrySave() {
    var  param={};
    var body=[];
    $(".odd").each(function(){
        var list={};
        list['sampleNumber']=resultEntryNumber;
        list['geneCode']=$(this).find('input').first().val();
        list['reportValue']=$(this).find('input').last().val();
        body.push(list);
    });
    param['token']=token;//token值
    param['body']=JSON.stringify(body);//body值
    param['sampleNumber']=resultEntryNumber;//sampleNumber值
    /*var url=resultEntryNumber>0?url="report/updateReportBatch":"report/addReportBatch";*/
    var url="report/updateReportBatch";
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
            else{
                layer.msg(result.detailMessage,{icon:1,time:1000});
            }
        },
        error:function (err) {
            console.log("错误");
        }
    });
}

