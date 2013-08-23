package com.travellist.travel.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.travellist.config.sqlmap.SqlSessionManager;
import com.travellist.travel.dao.UserDAO;
import com.travellist.travel.object.UserBO;

public class UserDAOImpl implements UserDAO{
  	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlMapper();
	private SqlSession session = sqlSessionFactory.openSession(true);
	
	public UserBO getUserInfo(UserBO userBo) throws Exception{
		return session.selectOne("user.getUserInfo", userBo);		
	}
	public void updateUserInfo(UserBO userBo) throws Exception{
		session.update("user.updateUserInfo", userBo);
	}
	public void insertUserInfo(UserBO userBo) throws Exception{
		session.insert("user.insertUserInfo", userBo);
	}
}
