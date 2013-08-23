package com.travellist.travel.dao;

import com.travellist.travel.object.UserBO;

public interface UserDAO {
	public UserBO getUserInfo(UserBO userBo) throws Exception;
	public void updateUserInfo(UserBO userBo) throws Exception;
	public void insertUserInfo(UserBO userBo) throws Exception;
}
