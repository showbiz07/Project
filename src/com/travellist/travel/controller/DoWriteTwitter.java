package com.travellist.travel.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.UserServiceImpl;

public class DoWriteTwitter extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    	try{
	    	String id = request.getParameter("shareId");
	    	String URL = request.getParameter("shareURL");
	    	String travelName = request.getParameter("travelName");
	    	
	    	UserBO userBO = new UserBO();
	    	userBO.setId(Integer.parseInt(id));
	    	userBO = userService.getUserInfo(userBO);
	    	
	    	Twitter twitter = new TwitterFactory().getInstance();
	    	
	    	AccessToken accessToken= new AccessToken(userBO.getAccessToken(), userBO.getTokenSecret());
	    	twitter.setOAuthAccessToken(accessToken);
	    	
	    	twitter.updateStatus(travelName + " " + URL + " " + new Date());
	    	
	    	request.setAttribute("userBO", userBO);
	    	return "success";
    	}catch(Exception e){
    		return "error";
    	}
    }
}