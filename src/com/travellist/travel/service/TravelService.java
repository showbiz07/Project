package com.travellist.travel.service;

import java.util.List;

import com.travellist.travel.object.TravelInfoBO;

public interface TravelService {
	
	public List<TravelInfoBO> getTravelList(TravelInfoBO travelInfoBO) throws Exception;
	public TravelInfoBO doInsertTravel(TravelInfoBO travelInfoBO) throws Exception;
	public TravelInfoBO getTravelInfo(TravelInfoBO travelInfoBO) throws Exception;
}
