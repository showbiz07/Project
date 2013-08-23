package com.travellist.travel.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.opensymphony.xwork2.ActionSupport;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.UserService;
import com.travellist.travel.service.impl.UserServiceImpl;

public class DoTwitterOauthAccess extends ActionSupport implements ServletRequestAware, ServletResponseAware{

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
	    	  Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
	          RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
	          String verifier = request.getParameter("oauth_verifier");
	          AccessToken accessToken = null;
	          try {
	              accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
	              request.getSession().removeAttribute("requestToken");
	              request.getSession().removeAttribute("twitter");
	          } catch (TwitterException e) {
	              throw new ServletException(e);
	          }
	          
	    	UserBO userBO = new UserBO();
	    	
	    	userBO.setId(accessToken.getUserId());
	    	userBO.setAccessToken(accessToken.getToken());
	    	userBO.setTokenSecret(accessToken.getTokenSecret());
	    	userBO.setScreenId(accessToken.getScreenName());
	    	
	    	userService.setUserInfo(userBO);
	    	
	    	request.setAttribute("userBO", userBO);
	    	
	    	return SUCCESS;
    	} catch(Exception e) {
    		return "error";
    	}
    }
}