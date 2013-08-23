package com.travellist.travel.object;

import java.util.Date;

public class NodeBO {
	long id;
	Integer travelNo;
	String nodeName;
	Integer nodeNo;
	String travelName;
	String content;
	String endX;
	String endY;
	Date endDate;
	String useYn;
	
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getTravelNo() {
		return travelNo;
	}
	public void setTravelNo(Integer travelNo) {
		this.travelNo = travelNo;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public Integer getNodeNo() {
		return nodeNo;
	}
	public void setNodeNo(Integer nodeNo) {
		this.nodeNo = nodeNo;
	}
	public String getTravelName() {
		return travelName;
	}
	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEndX() {
		return endX;
	}
	public void setEndX(String endX) {
		this.endX = endX;
	}
	public String getEndY() {
		return endY;
	}
	public void setEndY(String endY) {
		this.endY = endY;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "NodeBO [id=" + id + ", travelNo=" + travelNo + ", nodeName="
				+ nodeName + ", nodeNo=" + nodeNo + ", travelName="
				+ travelName + ", content=" + content + ", endX=" + endX
				+ ", endY=" + endY + ", endDate=" + endDate + "]";
	}
	
}
