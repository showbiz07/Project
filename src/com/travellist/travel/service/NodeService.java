package com.travellist.travel.service;

import java.util.List;

import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;

public interface NodeService {
	
	public List<NodeBO> getNodeList(TravelInfoBO travelInfoBO) throws Exception;
	
	public void insertNode(NodeBO nodeBO) throws Exception;
}
