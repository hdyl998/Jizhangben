package com.example.bean;

import java.io.Serializable;


public class BaseBean implements Serializable{

	private String title1;//标题
	private String articleAreaName;//文章的范围
	private String articleInnerType;//文章的类型
	private String publishTime;//发布时间
	private String dangerLevel;//危险指数
	private String bigImgUrlServer;//大图片
	private String smallImgUrlServer;//小图片
	private String newsContent;//新闻详细内容
	
	
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getBigImgUrlServer() {
		return bigImgUrlServer;
	}
	public void setBigImgUrlServer(String bigImgUrlServer) {
		this.bigImgUrlServer = bigImgUrlServer;
	}
	public String getSmallImgUrlServer() {
		return smallImgUrlServer;
	}
	public void setSmallImgUrlServer(String smallImgUrlServer) {
		this.smallImgUrlServer = smallImgUrlServer;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getArticleAreaName() {
		return articleAreaName;
	}
	public void setArticleAreaName(String articleAreaName) {
		this.articleAreaName = articleAreaName;
	}
	public String getArticleInnerType() {
		return articleInnerType;
	}
	public void setArticleInnerType(String articleInnerType) {
		this.articleInnerType = articleInnerType;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getDangerLevel() {
		return dangerLevel;
	}
	public void setDangerLevel(String dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
		
}
