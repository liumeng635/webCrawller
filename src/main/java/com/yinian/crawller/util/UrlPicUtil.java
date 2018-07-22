/**   
 * Copyright © 2018 eSunny Info. Tech Ltd. All rights reserved.
 * 
 * 功能描述：
 * @Package: com.yinian.crawller.util 
 * @author: 刘猛  
 * @date: 2018年7月22日 上午9:11:40 
 */
package com.yinian.crawller.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.CopyUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;

/**   
 * Copyright: Copyright (c) 2018 liumeng
 * @ClassName: GetUrlPic.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: 刘猛  
 * @date: 2018年7月22日 上午9:11:40 
 */
public class UrlPicUtil {
	public static void getUrlPic(String imgUrl,String localPath) throws Exception {
		  File dir = new File(localPath);
		  if(!dir.exists()) {
			  dir.mkdirs();
		  }
		  if(!dir.isDirectory()){
			  throw new IllegalArgumentException("localPath必须是一个文件夹");
		  }
		  String imgname = imgUrl.substring(imgUrl.lastIndexOf("/"));
		  //new一个URL对象 
		  URL url = new URL(imgUrl); 
		// 打开链接
		  HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
		  //设置请求方式为"GET" 
		  conn.setRequestMethod("GET"); 
		  //超时响应时间为5秒 
		  conn.setConnectTimeout(50 * 1000); 
		  //通过输入流获取图片数据 
		  InputStream inStream = conn.getInputStream();
		  
		  //new一个文件对象用来保存图片，默认保存当前工程根目录 
		  File imageFile = new File(localPath+File.separator+imgname);
		  //得到图片的二进制数据，以二进制封装得到数据，具有通用性 
		  byte[] data = readInputStream(inStream);
		//创建输出流 
		  FileOutputStream outStream = new FileOutputStream(imageFile);
		  IOUtils.write(data, outStream);
		  IOUtils.close(conn);
	}
		 public static byte[] readInputStream(InputStream inStream) throws Exception{ 
		  ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
		  //创建一个Buffer字符串 
		  byte[] buffer = new byte[1024]; 
		  //每次读取的字符串长度，如果为-1，代表全部读取完毕 
		  int len = 0; 
		  //使用一个输入流从buffer里把数据读取出来 
		  while( (len=inStream.read(buffer)) != -1 ){ 
		   //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度 
		   outStream.write(buffer, 0, len); 
		  } 
		  //关闭输入流 
		  inStream.close();
		  //把outStream里的数据写入内存 
		  return outStream.toByteArray(); 
		 } 
		 
}
