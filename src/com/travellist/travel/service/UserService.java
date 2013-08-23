package com.travellist.travel.service;

import com.travellist.travel.object.UserBO;


public interface UserService {
	
	public void setUserInfo(UserBO userBO) throws Exception;
	public UserBO getUserInfo(UserBO userBO) throws Exception;
}
