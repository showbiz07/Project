package com.travellist.travel.dao;

import java.util.List;

import com.travellist.travel.object.TravelInfoBO;

public interface TravelDAO {

	public List<TravelInfoBO> getTravelList(TravelInfoBO travelInfoBO) throws Exception;
	
	public void doInsertTravel(TravelInfoBO travelInfoBO) throws Exception;
	
	public int getMaxTravelNo() throws Exception;
	
	public TravelInfoBO getTravelInfo (TravelInfoBO travelInfoBO) throws Exception;
}
