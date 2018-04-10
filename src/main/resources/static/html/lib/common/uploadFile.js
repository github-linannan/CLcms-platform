(function( $ ){
	 // 当domReady的时候开始初始化

	uploader = new Array();

	function fileUpload(element,defaults){
		this.element=element;
		 var opts={"fileNumLimit":5,calluploadProgress:function(file,uploader){

		 },
			 "extensions":'gif,jpg,jpeg,bmp,png,mp4',
             "mimeTypes" :'image/*',
			 callSuccessBack:function(file,response){
		 },"url":"http://127.0.0.1:8899/resource/savepicture",responseInput:":input[name='tPackageUrl']"};
		this.opts=$.extend({},opts,defaults);
		
		
		this.init($(element).index());
	}
	fileUpload.prototype.init=function(index){
		var tthis=this,$this=$(this);
	     uploader = WebUploader.create({
			auto: true,
			swf: 'lib/webuploader/0.1.5/Uploader.swf',
			// 文件接收服务端。
			server: tthis.opts.url,
			// 选择文件的按钮。可选。
			// 内部根据当前运行是创建，可能是input元素，也可能是flash.
			pick: '#imgPicker'+(index+1),
			// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
			resize: false,
             // 只允许选择图片文件。
			accept: {
				title: 'Images',
				extensions: tthis.opts.extensions,
				mimeTypes: tthis.opts.mimeTypes
			}
		});
	      
	     uploader.on( 'fileQueued', function( file ) {
			$img =$(this.options.pick).find('img').length==0?$(this.options.pick).find('video'):$(this.options.pick).find('img');
			console.info($img);
          var  ratio = window.devicePixelRatio || 1,
            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio;
			// 创建缩略图
			// 如果为非图片文件，可以不用调用此方法。
			// thumbnailWidth x thumbnailHeight 为 100 x 100
          uploader.makeThumb( file, function( error, src ) {
        	  console.info(file);
        	  console.info(file.type);
        	 if(file.type=="video/mp4"){  
        		 
        		 return ;
        	 }  
        	
				if ( error ) {
					$img.replaceWith('<span>不能预览</span>');
					return;
				}
				$img.attr( 'src', src );
			}, thumbnailWidth, thumbnailHeight );
		});
		// 文件上传过程中创建进度条实时显示。
	     uploader.on( 'uploadProgress', function( file, percentage ) {
             tthis.opts.calluploadProgress(file,uploader,tthis.element);
		    var $li = $( '#'+file.id ),
		        $percent = $li.find('.progress span');
		    // 避免重复创建
		    if ( !$percent.length ) {
		        $percent = $('<p class="progress"><span></span></p>')
		                .appendTo( $li )
		                .find('span');
		    }
		    $percent.css( 'width', percentage * 100 + '%' );
		});
		
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
	     uploader.on( 'uploadSuccess', function( file,response ) {
			tthis.opts.callSuccessBack(response);
			 $(tthis.opts.responseInput,tthis.element).val(response.data);
			$( '#'+file.id ).addClass('upload-state-success');
		});
		
		// 文件上传失败，显示上传出错。
	     uploader.on( 'uploadError', function( file ) {
		    var $li = $( '#'+file.id ),
		        $error = $li.find('div.error');

		    // 避免重复创建
		    if ( !$error.length ) {
		        $error = $('<div class="error"></div>').appendTo( $li );
		    }
		    $error.text('上传失败');
		});
		// 完成上传完了，成功或者失败，先删除进度条。
	     uploader.on( 'uploadComplete', function( file ) {
		    $( '#'+file.id ).find('.progress').remove();
		});
	}
	
	
	$.fn.uploadFile=function(options){
		return this.each(function(i,v){
			return new fileUpload(this,options);
		});
	}
	
	}( jQuery ));