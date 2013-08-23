package com.travellist.travel.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.TravelService;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.TravelServiceImpl;
import com.travellist.travel.service.impl.UserServiceImpl;

public class Loggin extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private TravelService getTravelList = new TravelServiceImpl();
		
	
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

	public String execute() throws Exception {
		try{
	    	UserBO userBO = new UserBO();
	    	
	    	String tempId = request.getParameter("id");
	    	Integer id = Integer.parseInt(tempId);
	
	    	userBO.setId(id);
	    	userBO = userService.getUserInfo(userBO);
	    	
			TravelInfoBO travelInfoBO = new TravelInfoBO();
			travelInfoBO.setId(userBO.getId());
					
			List<TravelInfoBO> travelList = getTravelList.getTravelList(travelInfoBO);
	
			request.setAttribute("travelList", travelList);
			request.setAttribute("travelInfoBO", travelInfoBO);
			request.setAttribute("userBO", userBO);
			
			return "success";
		} catch(Exception e){
			return "error";
		}
	}
}
