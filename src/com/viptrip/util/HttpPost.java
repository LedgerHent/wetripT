package com.viptrip.util;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import etuf.v1_0.model.base.output.OutputBase;


/*
 * 
 * url 路径
 * param 参数
 * encode 编码
 */
public class HttpPost {
	
	
	
	 public  String readContentFromPost(String url,String urlParam,String param) throws IOException {
		 	
  		 	
		 	String httpUrl = "";
		 	if(urlParam != null && !"".equals(urlParam)){
		 		httpUrl = url + "?" + urlParam;
		 	}else{
		 		httpUrl = url;
		 	}
		 	
	        URL postUrl = new URL(httpUrl);
		
	        // 打开连接
	        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
	      
	        // 设置是否向connection输出，因为这个是post请求，参数要放在
	        // http正文内，因此需要设为true
	        connection.setDoOutput(true);
	        // Read from the connection. Default is true.
	        connection.setDoInput(true);
	        // 默认是 GET方式
	        connection.setRequestMethod("POST");
	       
	        // Post 请求不能使用缓存
	        connection.setUseCaches(false);
	       
	        connection.setInstanceFollowRedirects(true);
	       
	        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
	        // 要注意的是connection.getOutputStream会隐含的进行connect。
	        connection.connect();
	        DataOutputStream out = new DataOutputStream(connection
	                .getOutputStream());
	        // The URL-encoded contend
	        // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
	      
	 
	        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
	        
	        out.write(param.getBytes());
	        out.flush();
	        out.close(); 
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        String revalue = "";
	        while ((line = reader.readLine()) != null){
	            revalue=revalue+line;
	            
	        }
	        System.out.println("响应状态：    "+revalue);
	        reader.close();
	        connection.disconnect();
	        
	        return revalue;
	}
	 
	 
	 public static String Post(String url, String param) {
		 
		   
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("Accept-Charset", "UTF-8");
//	            conn.setRequestProperty(
//	                    "User-Agent",
//	                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.2;"
//	                            + " Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; "
//	                            + ".NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152;"
//	                            + " .NET CLR 3.5.30729)");
	            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");
	            conn.setRequestProperty("Connection", "Keep-Alive");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    } 
	 /**
	 * 通过HttpURLConnection方式 发送Http Post请求 
	 * @author votory
	 * @param url
	 * @param postData
	 * @param encoding 编码方式
	 * @return 响应字符串
	 * @throws Exception
	 */
	public static <T> Boolean  httpPost(String url, String param,OutputBase out)
			throws Exception {
		String result="";
		Boolean flag=true;
		//Log.log("url", url + "&" + postData);
		// 建立连接
		try{
			URL url2 = new URL(url);
			HttpURLConnection httpConnection = (HttpURLConnection) url2
					.openConnection();
	
			// 设置连接参数
			httpConnection.setDoInput(true);// 使用 URL 连接进行输入
			httpConnection.setDoOutput(true);// 使用 URL 连接进行输出
			httpConnection.setUseCaches(false);// 忽略缓存
			httpConnection.setConnectTimeout(3000);
			httpConnection.setRequestMethod("POST");// 设置URL请求方法
	
			// 设置请求参数
			byte[] postBytes = param.getBytes("gbk");
			httpConnection.setRequestProperty("Content-length", ""
					+ postBytes.length);
			httpConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			// httpConnection.setRequestProperty("Connection", "Keep-Alive");//
			// 维持长连接
			httpConnection.setRequestProperty("Charset", "gbk");
			// 建立输出流并写入发送数据
			OutputStream outputStream = httpConnection.getOutputStream();
			outputStream.write(postBytes);
			outputStream.close();
	
			// 获得响应流
			String baos="";
			if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream=httpConnection.getInputStream();
				
				BufferedReader  in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		        String line;
	            while ((line = in.readLine()) != null) {
	            	baos += line;
	            }
				/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buffer=new byte[1024];
				int len=0;
				while ((len=inputStream.read(buffer))>0) {
					baos.write(buffer, 0, len);
				}*/
				result=baos;
				inputStream.close();
				//baos.close();
				
				out.code=0;
				out.result=result;
			} else{
			    result="错误代码：201211141122。错误信息：HTTP POST请求响应失败，返回消息为--"+ result + "。";
			      flag=false;
	        	  out.code=1;
				  out.result=result;
				  throw new Exception("错误代码：201211141122。错误信息：HTTP POST请求响应失败，返回消息为--"+ httpConnection.getResponseMessage() + "。");
				  }
		 }catch (Exception e) {
			// TODO: handle exception
			 e.printStackTrace();
        	  flag=false;
        	  out.exception=e;
        	  out.code=1;
			  out.result=result;
		}
		return flag;
	  }
	    
	}

