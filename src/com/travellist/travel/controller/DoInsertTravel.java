package com.travellist.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.service.TravelService;
import com.travellist.travel.service.impl.TravelServiceImpl;

public class DoInsertTravel extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TravelService travelService = new TravelServiceImpl();
	private Integer travelNo = new Integer(0);
	
	HttpServletRequest request;
	HttpServletResponse response; 
	
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
	    	String contentName = request.getParameter("contentName");
	    	String id = request.getParameter("id");
	    	TravelInfoBO travelInfoBO = new TravelInfoBO();
	    	
	    	travelInfoBO.setId(Integer.parseInt(id));
	    	travelInfoBO.setTravelName(contentName);
	    	
	    	
	    	travelInfoBO = travelService.doInsertTravel(travelInfoBO);
	    	setTravelNo(travelInfoBO.getTravelNo());
	    	
	    	return SUCCESS;
    	} catch(Exception e){
    		return "error";
    	}
    	
    }
}