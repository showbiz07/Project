package com.travellist.travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.NodeService;
import com.travellist.travel.service.TravelService;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.NodeServiceImpl;
import com.travellist.travel.service.impl.TravelServiceImpl;
import com.travellist.travel.service.impl.UserServiceImpl;

public class DoInsertNodeRedirect extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NodeService nodeService = new NodeServiceImpl();
	private UserService userService = new UserServiceImpl();
	private TravelService travelService = new TravelServiceImpl();
	
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
	    	String travelNo = request.getParameter("travelNo");
	    	TravelInfoBO travelInfoBO = new TravelInfoBO();
	    	travelInfoBO.setTravelNo(Integer.parseInt(travelNo));
	    	
	    	travelInfoBO = travelService.getTravelInfo(travelInfoBO);
	    	List<NodeBO> nodeList = nodeService.getNodeList(travelInfoBO);
	
	    	UserBO userBO = new UserBO();
	    	userBO.setId(travelInfoBO.getId());
	    	userBO = userService.getUserInfo(userBO);
	    	
	    	request.setAttribute("travelInfoBO", travelInfoBO);
	    	request.setAttribute("nodeList", nodeList);
	    	request.setAttribute("userBO", userBO);
	    	
	    	return SUCCESS;
    	} catch(Exception e){
    		return "error";
    	}
    	
    }
}
