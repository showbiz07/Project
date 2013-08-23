package com.travellist.travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.NodeService;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.NodeServiceImpl;
import com.travellist.travel.service.impl.UserServiceImpl;

public class ShowTravel extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private NodeService nodeService = new NodeServiceImpl();
	private UserService userService = new UserServiceImpl();
	
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
		try {
			request.setCharacterEncoding("utf-8");
			
		
	    	String contentName = request.getParameter("show_contentName");
	    	String id = request.getParameter("show_id");
	    	String travelNo = request.getParameter("travelNo");
	    	
	    	TravelInfoBO travelInfoBO = new TravelInfoBO();
	    	
	    	travelInfoBO.setId(Integer.parseInt(id));
	    	travelInfoBO.setTravelName(contentName);
	    	travelInfoBO.setTravelNo(Integer.parseInt(travelNo));
	    	
	    	request.setAttribute("travelInfoBO", travelInfoBO);
	    	
	    	List<NodeBO> nodeList = nodeService.getNodeList(travelInfoBO);
	    	
	    	request.setAttribute("nodeList", nodeList);
	    	
	    	UserBO userBO = new UserBO();
	    	userBO.setId(travelInfoBO.getId());
	    	userBO = userService.getUserInfo(userBO);
	    	request.setAttribute("userBO", userBO);
	
	    	return SUCCESS;
		} catch(Exception e) {
			return "error";
		}
	}

}
