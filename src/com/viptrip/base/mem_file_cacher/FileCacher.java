package com.viptrip.base.mem_file_cacher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.viptrip.util.StringUtil;

import ch.qos.logback.classic.Logger;



/**
 * 内存文件缓存器
 * @author selfwhisper
 *
 */
public class FileCacher{
	final static Logger log =  (Logger) LoggerFactory.getLogger(FileCacher.class);
	private static boolean isAuto = false;
	private KeyGenerator generator;
	private static final boolean defaultIsAutoClean = true;
	private static final boolean defaultIsCleanByDay = true;
	private static final int defaultCleanCount = 10;
	
	private String name;//cacher名称
	private String directory;//目录
	private String basePath;//根目录
	private String filepath;//缓存目录
	
	private String listName = "list.conf";//缓存文件
	private String configName = "conf.conf";//配置文件
	
	private boolean isAutoClean;//是否自动清理
	private boolean isCleanByDay;//是否按天自动清理
	private int cleanCount;//清理频率 
	
	private Properties list = new Properties(); 
	private Properties config = new Properties();
	
	private Map<String, CachedFile> cfiles = new LinkedHashMap<String, CachedFile>();//
	
	/**
	 * 获取缓存文件list
	 * @return List<CacheFile>
	 */
	public List<CachedFile> getAllFiles(){
		List<CachedFile> result = new ArrayList<CachedFile>();
		Set<String> keySet = cfiles.keySet();
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			String key =  iterator.next();
			result.add(cfiles.get(key));
		}
		return result;
	}
	
	public FileCacher(String name, String directory, String basePath) {
		this(name, directory, basePath, null, null, null,null);
	}
	public FileCacher(String name, String directory, String basePath,KeyGenerator generator) {
		this(name, directory, basePath, null, null, null,generator);
	}
	public FileCacher(String name, String directory, String basePath,Boolean isAutoClean,Boolean isCleanByDay,Integer cleanCount) {
		this(name, directory, basePath, isAutoClean, isCleanByDay, cleanCount, null);
	}
	public FileCacher(String name, String directory, String basePath,Boolean isAutoClean,Boolean isCleanByDay,Integer cleanCount,KeyGenerator generator) {
		this.name = name;
		this.directory = directory;
		this.basePath = basePath;
		this.isAutoClean = isAutoClean==null?defaultIsAutoClean:isAutoClean;
		this.isCleanByDay = isCleanByDay==null?defaultIsCleanByDay:isCleanByDay;
		this.cleanCount = cleanCount==null?defaultCleanCount:cleanCount;
		this.generator = generator==null?new DefaultKeyGenerator():generator;
		this.filepath = this.basePath + File.separator + this.directory;
		init();
	}
	
	/**
	 * 初始化
	 */
	private void init(){
		if(!StringUtil.isEmpty(basePath)&&!StringUtil.isEmpty(directory)){
			File base = new File(basePath);
			if(null!=base&&!base.exists()){
				base.mkdirs();
			}
			File dire = new File(this.filepath);
			if(null!=dire&&!dire.exists()){
				dire.mkdir();
			}
			File listFile = new File(this.filepath + File.separator + listName);
			if(null!=listFile&&!listFile.exists()){//初始化配置文件
				try {
					listFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			File configFile = new File(this.filepath + File.separator + configName);
			if(null!=configFile&&!configFile.exists()){//初始化缓存列表文件
				try {
					configFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			config.setProperty("isAutoClean", isAutoClean + "");
			config.setProperty("isCleanByDay", isCleanByDay + "");
			config.setProperty("cleanCount", cleanCount + "");
			writeConfigFile(configFile, config);
			list = loadProps(this.filepath + File.separator + listName);
			syschFile();//同步缓存
			log.info("缓存器" + name + "初始化完成:" + "根目录-->" + basePath + ",缓存目录-->" + directory );
		}
	}
	
	
	/**
	 * 同步缓存
	 */
	private synchronized void syschFile(){
		List<String> keys = new ArrayList<String>();
		List<String> toDel = new ArrayList<String>();
		if(list.size()>0){
			Set<Object> keyset = list.keySet();
			for (Iterator<Object> iterator = keyset.iterator(); iterator
					.hasNext();) {
				String key = (String) iterator.next();
				String name = list.getProperty(key);
				File file = new File(basePath + File.separator + name);
				if(!file.exists()){
					toDel.add(key);
					cfiles.remove(key);
				}else{
					keys.add(key);
					String filename = name.substring(name.indexOf(File.separator)+1);
					cfiles.put(key, new CachedFile(key, filename, name, basePath));
				}
			}
			if(toDel.size()>0){
				for(String key:toDel){
					list.remove(key);
				}
				writeConfigFile(this.filepath + File.separator + listName, list);
			}
		}else{
			cfiles = new LinkedHashMap<String, CachedFile>();
		}
		
		//删除本地多余文件
		String path = this.filepath;
		File f = new File(path);
		File[] files = f.listFiles();
		if(null!=files&&files.length>0){
			for(File file:files){
				String name = file.getName();
				if(name.indexOf(".conf")<0){
					if(StringUtil.isEmpty(getKey(name))){
						file.delete();
					}
				}
			}
		}
	}
	
	/**
	 * 加载配置文件
	 * @param filename 文件名 绝对路径
	 * @return 
	 * @throws IOException
	 */
	private synchronized Properties loadProps(String filename){
		Properties result = new Properties();
		InputStreamReader isFileStream = null;
		try {
			isFileStream = new InputStreamReader(new FileInputStream(filename),"UTF-8") ;
			result.load(isFileStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally{
			if(isFileStream!=null){
				try {
					isFileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 加载配置文件
	 * @param filename 文件名 绝对路径
	 * @return 
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	private synchronized Properties loadProps(File file){
		Properties result = new Properties();
		InputStreamReader isFileStream = null;
		try {
			isFileStream = new InputStreamReader(new FileInputStream(file),"UTF-8") ;
			result.load(isFileStream);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}finally{
			if(isFileStream!=null){
				try {
					isFileStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 保存配置文件
	 * @param filename 文件名
	 * @param p Properties属性
	 * @throws IOException
	 */
	private synchronized void writeConfigFile(String filename,Properties p){
		OutputStreamWriter fos = null;
		try {
			fos = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8");
			p.store(fos, null);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 清空缓存
	 */
	public void clear(){
		list = new Properties();
		writeConfigFile(this.filepath + File.separator + listName, list);
		syschFile();
	}
	
	/**
	 * 保存配置文件
	 * @param file 文件
	 * @param p Properties属性
	 * @throws IOException
	 */
	private synchronized void writeConfigFile(File file,Properties p){
		OutputStreamWriter fos = null;
		try {
			fos = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			p.store(fos, null);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 添加文件
	 * @param key 文件唯一标识
	 * @param flag 自定义标志
	 * @param files 文件数据
	 * @param ext 扩展名
	 */
	public CachedFile add(String key,String flag,byte[] files,String ext){
		String filename = generator.exec(key,flag) + "." + ext;
//			String filename = key + "_" + flag + "." + ext;
		String filePath = this.filepath;
		File file = new File(filePath + File.separator + filename);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(files);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = directory + File.separator + filename;
		list.setProperty(key, path);
		CachedFile cachedFile = new CachedFile(key, filename, path, basePath);
		cfiles.put(key, cachedFile);
		writeConfigFile(this.filepath + File.separator + listName,list);
		return cachedFile;
	}
	/**
	 * 添加文件
	 * @param key 文件唯一标识
	 * @param flag 自定义标志位
	 * @param files 文件数据流
	 * @param ext 扩展名
	 */
	public CachedFile add(String key,String flag,InputStream files,String ext){
		String filename = generator.exec(key,flag) + "." + ext;
//			String filename = key + "_" + flag + "." + ext;
		String filePath = this.filepath;
		File file = new File(filePath + File.separator + filename);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			InputStream is = files;
			byte[] by = new byte[4096];
			int len = 0;
			while((len=is.read(by))>0){
				fos.write(by, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = directory + File.separator + filename;
		list.setProperty(key, path);
		CachedFile cachedFile = new CachedFile(key, filename, path, basePath);
		cfiles.put(key, cachedFile);
		writeConfigFile(this.filepath + File.separator + listName,list);
		return cachedFile;
	}
	/**
	 * 添加文件
	 * @param key 文件唯一标识
	 * @param flag 自定义标志位
	 * @param files 文件数据
	 * @param ext 扩展名
	 */
	public CachedFile add(String key,String flag,File files,String ext){
		String filename = generator.exec(key,flag) + "." + ext;
//			String filename = key + "_" + flag + "." + ext;
		String filePath = this.filepath;
		File file = new File(filePath + File.separator + filename);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			InputStream is = new FileInputStream(files);
			byte[] by = new byte[4096];
			int len = 0;
			while((len=is.read(by))>0){
				fos.write(by, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String path = directory + File.separator + filename;
		list.setProperty(key, path);
		CachedFile cachedFile = new CachedFile(key, filename, path, basePath);
		cfiles.put(key, cachedFile);
		writeConfigFile(this.filepath + File.separator + listName,list);
		return cachedFile;
	}
	
	/**
	 * 获取缓存文件
	 * @param key
	 * @return
	 */
	public CachedFile get(String key){
		syschFile();
		return cfiles.get(key);
	}
	
	/**
	 * 根据文件名获取文件的主键
	 * @param filename 文件名
	 * @return
	 */
	public String getKey(String filename){
		String result = null;
		if(list.size()>0){
			Set<Object> keySet = list.keySet();
			for (Iterator<Object> iterator = keySet.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if(list.get(key).equals(filename)||list.get(key).equals(directory + File.separator + filename)){
					result = key;
					break;
				}
			}
		}
		return result;
	}
	/**
	 * 移除缓存文件
	 * @param key
	 */
	public void remove(String key){
		if(!StringUtil.isEmpty(key)){
			deleteFile(key);
			list.remove(key);
			cfiles.remove(key);
			writeConfigFile(this.filepath + File.separator + listName, list);
		}
	}
	//删除本地文件
	private void deleteFile(String key){
		String filePath = this.filepath;
		File file = new File(filePath);
		CachedFile cf = get(key);
		if(null!=cf){
			String filename = cf.getName();
			if(!StringUtil.isEmpty(filename)){
				if(file.exists()){
					File[] listFiles = file.listFiles();
					if(null!=listFiles&&listFiles.length>0){
						for(File f:listFiles){
							String name = f.getName();
							if(filename.equals(name)){
								f.delete();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 开启自动更新
	 */
	public FileCacher autoFresh(int frequency){
		if(!isAuto){
			new Thread(new AutoTask(frequency)).start();
			isAuto = true;
			log.info("缓存器" + name + "开启自动刷新成功");
		}
		return this;
	}
	//自动刷新同步
	private class AutoTask implements Runnable{
		private int frequency;
		
		public AutoTask(int frequency){
			this.frequency = frequency>0?frequency:60;
		}
		
		@Override
		public void run() {
			if(isAutoClean){
				while(true){
					syschFile();//校准文件
					if(isCleanByDay){
//						System.out.println("clean by day");
						cleanByDay();
					}else{
//						System.out.println("clean by count");
						cleanByCount();
					}
					try {
						Thread.sleep(1000*this.frequency);//刷新频率
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		}
		//按天
		private void cleanByDay(){
			int day = cleanCount;
			if(day<1){
				day = 1;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d;
			try {
				d = sdf.parse(sdf.format(new Date()));
				Date remainLine = new Date(d.getTime() - (day-1)*24*3600*1000);
				List<CachedFile> files = getAllFiles();
				if(files.size()>0){
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					for (Iterator<CachedFile> iterator = files.iterator(); iterator
							.hasNext();) {
						CachedFile cacheFile = iterator.next();
						String filename = cacheFile.getName();
						String dateStr = filename.substring(0, filename.indexOf("_"));
						Date date = simpleDateFormat.parse(dateStr);
						if(date.compareTo(remainLine)<0){
							remove(cacheFile.getKey());
						}
					}
				}
				log.info("缓存器" + name + "刷新成功，当前保存缓存文件" + cleanCount + "天");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		//按个数
		private void cleanByCount(){
			int count = cleanCount;
//			System.out.println("FileCacher.AutoTask.cleanByCount()==cfiles count==" + cacher.cfiles.size());
			List<CachedFile> files = getAllFiles();
			if(files.size()>count){
				int toDel = files.size() - count;
				for(int i=0;i<toDel;i++){
					CachedFile cacheFile = files.get(i);
					remove(cacheFile.getKey());
				}
			}
			log.info("缓存器" + name + "刷新成功，当前保存缓存文件" + cleanCount + "个");
		}
		
	}
}
	
	

