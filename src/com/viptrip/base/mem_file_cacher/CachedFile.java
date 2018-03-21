package com.viptrip.base.mem_file_cacher;

public class CachedFile {
	private String key;
	private String name;//文件名
	private String path;//引用路径 包括路径及文件名
	private String basePath;//根路径
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public CachedFile() {
		
	}
	
	public CachedFile(String key, String name, String path, String bathPath) {
		this.key = key;
		this.name = name;
		this.path = path;
		this.basePath = bathPath;
	}
		
}
