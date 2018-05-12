package cn.story.domain;

public class Category {
	
	 /*`cid` VARCHAR(32) NOT NULL,
	  `cname` VARCHAR(20) DEFAULT NULL,*/
	private String cid;
	private String cname;
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

}
