package com.classnet.util.page;

public class PageInfo {

	private String url;
	private int total;
	private int page;
	private int page_size;
	private int total_page;
	private boolean next=false;
	private boolean prev=false;
	
	public PageInfo(String url, int total, int page, int page_size) {
		super();
//		if(url!=null){
//			this.url = url.replace(":9999", "");
//			this.url = url.replace(":8080", "");
//		}
		this.url = url;
		this.total = total;
		this.page = page;
		this.page_size = page_size;
		init(); 
	}
	
	private void init(){
		total_page=(int)(Math.ceil((double)total/page_size));
		next=page<total_page;
		prev=page>1;
	}

	public String getUrl() {
		return url;
	}

	public long getTotal() {
		return total;
	}

	public int getPage() {
		return page;
	}

	public int getPage_size() {
		return page_size;
	}

	public int getTotal_page() {
		return total_page;
	}

	public boolean isNext() {
		return next;
	}

	public boolean isPrev() {
		return prev;
	}
}
