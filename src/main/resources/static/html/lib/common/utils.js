/**
 * 获取html？参数
 * 2017/12/21
 * **/	
//测试环境IP
//var web_url="http://10.10.7.12:8087/";
//开发环境
var web_url="http://10.10.7.11:8087/";
var imageupload_url="http://10.10.7.11:8899/";
//var web_url="http://10.10.2.245:8087/";//飞
//var web_url="http://10.10.2.244:8087/";//董
/***
 * 取URL参数
 * @param name
 * @returns
 */
function getQueryString(name) {  
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
	    var r = window.location.search.substr(1).match(reg);

	    if (r != null) return decodeURI(r[2]); return null;
	}



function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


var MyLocalStorage ={
    Cache : {
        /**
         * 总容量5M
         * 存入缓存，支持字符串类型、json对象的存储
         * 页面关闭后依然有效 ie7+都有效
         * @param key 缓存key
         * @param stringVal
         * @time 数字 缓存有效时间（秒） 默认60s
         * 注：localStorage 方法存储的数据没有时间限制。第二天、第二周或下一年之后，数据依然可用。不能控制缓存时间，故此扩展
         * */
        put : function(key,stringVal,time){
            try{
                if(!localStorage){return false;}
                if(!time || isNaN(time)){time=24*60*60;}
                var cacheExpireDate = (new Date()-1)+time*1000;
                var cacheVal = {val:stringVal,exp:cacheExpireDate};
                localStorage.setItem(key,JSON.stringify(cacheVal));//存入缓存值
                //console.log(key+":存入缓存，"+new Date(cacheExpireDate)+"到期");
            }catch(e){}
        },
        /**获取缓存*/
        get : function (key){
            try{
                if(!localStorage){return false;}
                var cacheVal = localStorage.getItem(key);
                var result = JSON.parse(cacheVal);
                var now = new Date()-1;
                if(!result){return null;}//缓存不存在
                if(now>result.exp){//缓存过期
                    this.remove(key);
                    return "";
                }
                //console.log("get cache:"+key);
                return result.val;
            }catch(e){
                this.remove(key);
                return null;
            }
        },/**移除缓存，一般情况不手动调用，缓存过期自动调用*/
        remove : function(key){
            if(!localStorage){return false;}
            localStorage.removeItem(key);
        },/**清空所有缓存*/
        clear : function(){
            if(!localStorage){return false;}
            localStorage.clear();
        }
    }//end Cache
}


/**
 * 
 */