package com.travellist.travel.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.service.NodeService;
import com.travellist.travel.service.impl.NodeServiceImpl;

public class DoInsertNode extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	

	private static final long serialVersionUID = 1L;
	private NodeService nodeService = new NodeServiceImpl();
	
	HttpServletRequest request;
	HttpServletResponse response;
	Integer travelNo = new Integer(0);
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
    public Integer getTravelNo() {
		return travelNo;
	}
	public void setTravelNo(Integer travelNo) {
		this.travelNo = travelNo;
	}
	
	public String execute() throws Exception {
    	
		try{
	    	DateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     	String id = request.getParameter("id");
	    	String travelNoTemp = request.getParameter("travelNo");
	    	String travelName = request.getParameter("travelName");
	    	int travelNo = Integer.parseInt(travelNoTemp);
	    	
	    	TravelInfoBO travelInfoBO = new TravelInfoBO();
	    	travelInfoBO.setId(Integer.parseInt(id));
	    	travelInfoBO.setTravelName(travelName);
	    	travelInfoBO.setTravelNo(travelNo);
	    	
	    	String endX = request.getParameter("endX");
	    	String endY = request.getParameter("endY");
	    	String endDateTemp = request.getParameter("endDate");
	    	String nodeName = request.getParameter("nodeName1");
	    	String content =  request.getParameter("content");
	
	    	Date endDate = sdFormat.parse(endDateTemp);    	
	    	
	    	NodeBO nodeBO = new NodeBO();
			
	    	nodeBO.setId(travelInfoBO.getId());
	    	nodeBO.setTravelNo(travelInfoBO.getTravelNo());
	    	nodeBO.setNodeName(nodeName);
	    	nodeBO.setTravelName(travelInfoBO.getTravelName());
	    	nodeBO.setContent(content);
	    	nodeBO.setEndX(endX);
	    	nodeBO.setEndY(endY);
	    	nodeBO.setEndDate(endDate);
	    	
	    	nodeService.insertNode(nodeBO);
	    	
	    	setTravelNo(travelInfoBO.getTravelNo());
	    	
	    	return SUCCESS;
		} catch (Exception e){
			return "error";
		}
    }
}
