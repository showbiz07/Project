package com.travellist.travel.service.impl;

import java.util.List;

import com.travellist.travel.dao.TravelDAO;
import com.travellist.travel.dao.impl.TravelDAOImpl;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.service.TravelService;

public class TravelServiceImpl implements TravelService{
	private TravelDAO travelDAO = new TravelDAOImpl(); 
	
	public List<TravelInfoBO> getTravelList(TravelInfoBO travelInfoBO) throws Exception{
		return travelDAO.getTravelList(travelInfoBO); 
	}
	
	public TravelInfoBO doInsertTravel(TravelInfoBO travelInfoBO) throws Exception{
		
		
		int travelNo = travelDAO.getMaxTravelNo();

		travelInfoBO.setTravelNo(travelNo);
		travelDAO.doInsertTravel(travelInfoBO);
		
		return travelInfoBO;
	}
	
	public TravelInfoBO getTravelInfo(TravelInfoBO travelInfoBO) throws Exception{
		
		travelInfoBO = travelDAO.getTravelInfo(travelInfoBO);
		return travelInfoBO;
	}
}
