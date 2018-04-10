/**
 * 乐土精准医疗有限公司
 */
package com.letu.healthplatform.platformmanage.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

/**
 * @author dzb
 * @date 2018年2月7日 上午10:00:04
 * @version 1.0.0
 * @see
 */
public class FileUtil {
	
	
	final static Logger logger =LogManager.getLogger(FileUtil.class);
	
	
	public static void uploadFile(HttpServletResponse response,String  fileName) {
		String osName = System.getProperty("os.name");  
		logger.info("osName is {},"+osName);
		if(!osName.toLowerCase().startsWith("win")){  
			fileName = "file:///root/file/"+fileName;
		}
		logger.info("fileName is {},"+fileName);
		   FileInputStream fis = null; //文件输入流
	       BufferedInputStream bis = null;
	       OutputStream os = null; //输出流
	       File file =null;
	       //此方法只适合在window系统上可用，linux必须以流的形式来读取。
		       try {
		    	   logger.info("fileName {},"+fileName);
		    	   file = ResourceUtils.getFile("classpath:"+fileName);
		    	   logger.info("file  is name {},"+file.getName());
					//File file = new File(filePath);
					if(file.exists()){ //判断文件父目录是否存在
					   response.setHeader("content-type", "application/octet-stream");
					   response.setContentType("application/octet-stream");
			    	   response.setHeader("Content-Disposition", "attachment;fileName=" +  new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
			           byte[] buffer = new byte[1024];
			               os = response.getOutputStream();
			               fis = new FileInputStream(file); 
			               bis = new BufferedInputStream(fis);
			               int i = bis.read(buffer);
			               while(i != -1){
			                   os.write(buffer, 0, buffer.length);
			                   os.flush();
			                   i = bis.read(buffer);
			               }
					}
		          } catch (Exception e) {
		               e.printStackTrace();
		          }finally {
		        	   logger.info("file download  is ,{}" + file.getName());
		               try {
		                   bis.close();
		                   fis.close();
		               } catch (IOException e) {
		                   e.printStackTrace();
		               }
		          }
	}

}
