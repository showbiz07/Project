package com.travellist.travel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import com.opensymphony.xwork2.ActionSupport;

public class DoTwitterOauth extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String url;
	
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
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
		   Twitter twitter = new TwitterFactory().getInstance();
	       request.getSession().setAttribute("twitter", twitter);
		
	
	       RequestToken requestToken = twitter.getOAuthRequestToken();
	       request.getSession().setAttribute("requestToken", requestToken);
	       
	       setUrl(requestToken.getAuthorizationURL());
	       
	    	return "redirect";
    	} catch(Exception e){
    		return "error";
    	}
    	
    }
}