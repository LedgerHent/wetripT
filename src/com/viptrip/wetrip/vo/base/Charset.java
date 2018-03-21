package com.viptrip.wetrip.vo.base;


public enum Charset{
	GBK("gbk"),UTF8("utf-8"),ISO8859_1("ISO8859-1"),UTF16("utf-16"),GB2312("gb2312");
	
	private String charset;
	
	private Charset(String charset){
		this.charset = charset;
	}

	public String getCharset() {
		return charset;
	}
	
	/**
	 * charset是否存在
	 * @param charset
	 * @return
	 */
	public boolean contains(String charset){
		boolean result = false;
		Charset[] sets = Charset.values();
		for(Charset cs:sets){
			if(cs.getCharset().equalsIgnoreCase(charset)){
				result = true;
				break;
			}
		}
		return result;
	}
}