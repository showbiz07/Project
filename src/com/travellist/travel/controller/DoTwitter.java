package com.travellist.travel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.UserServiceImpl;

public class DoTwitter extends ActionSupport implements ServletRequestAware, ServletResponseAware{

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
    	try {
	    	Integer id = Integer.parseInt(request.getParameter("id"));
	    	UserBO userBO = new UserBO();
	    	
	    	userBO.setId(id);
	    	
	    	userBO = userService.getUserInfo(userBO);
	    	
	    	request.setAttribute("userBO", userBO);
	    	
	    	Twitter twitter = new TwitterFactory().getInstance();
	    	
	    	AccessToken accessToken= new AccessToken(userBO.getAccessToken(), userBO.getTokenSecret());
	    	twitter.setOAuthAccessToken(accessToken);
	    	
	    	List<Status> status = twitter.getUserTimeline(userBO.getId());
	    	
	    	request.setAttribute("status", status);
	    	
	    	return "success";
    	} catch(Exception e){
    		return "error";
    	}
    }
}