package com.viptrip.wetrip.model.policy;


import java.util.List;

import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.policy.Bookpower;



public class BookpowerObj {
	private BookpowerVO pub;//因公
	private BookpowerVO pri;//因私
	private BookpowerVO both;//因公和因私
	
	public BookpowerVO getPub() {
		return pub;
	}
	public void setPub(Bookpower pub,List<TAcUser> user) {
		this.pub = new BookpowerVO(pub,user);
	}
	public BookpowerVO getPri() {
		return pri;
	}
	public void setPri(Bookpower pri,List<TAcUser> user) {
		this.pri = new BookpowerVO(pri,user);
	}
	public BookpowerVO getBoth() {
		return both;
	}
	public void setBoth(Bookpower both,List<TAcUser> user) {
		this.both = new BookpowerVO(both,user);
	}
	
	
}
