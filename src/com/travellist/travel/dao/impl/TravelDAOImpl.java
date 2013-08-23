package com.travellist.travel.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.travellist.config.sqlmap.SqlSessionManager;
import com.travellist.travel.dao.TravelDAO;
import com.travellist.travel.object.TravelInfoBO;

public class TravelDAOImpl implements TravelDAO{
	
  	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlMapper();
	private SqlSession session = sqlSessionFactory.openSession(true);
	

	public List<TravelInfoBO> getTravelList(TravelInfoBO travelInfoBO) throws Exception{
		return session.selectList("travel.getTravelList", travelInfoBO);
	}
	
	public void doInsertTravel(TravelInfoBO travelInfoBO) throws Exception{
		session.insert("travel.insertTravel",travelInfoBO);
	}
	
	public int getMaxTravelNo() throws Exception{
		return session.selectOne("travel.getMaxTravelNo");
	}
	
	public TravelInfoBO getTravelInfo (TravelInfoBO travelInfoBO) throws Exception{
		return session.selectOne("travel.getTravelInfo",travelInfoBO);
	}
}
