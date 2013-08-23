package com.travellist.travel.service.impl;

import com.travellist.travel.dao.UserDAO;
import com.travellist.travel.dao.impl.UserDAOImpl;
import com.travellist.travel.object.UserBO;
import com.travellist.travel.service.UserService;

public class UserServiceImpl implements UserService{
	UserDAO userDAO = new UserDAOImpl();

	public void setUserInfo(UserBO userBO) throws Exception{
		
		if( userDAO.getUserInfo(userBO) == null )
			userDAO.insertUserInfo(userBO);
		else
			userDAO.updateUserInfo(userBO);
	}
	
	public UserBO getUserInfo(UserBO userBO) throws Exception{
		return userDAO.getUserInfo(userBO);
	}
}
