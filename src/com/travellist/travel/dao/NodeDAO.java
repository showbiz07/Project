package com.travellist.travel.dao;

import java.util.List;

import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;

public interface NodeDAO {
	public List<NodeBO> getNodeList(TravelInfoBO travelInfoBO) throws Exception;
	public void insertNode(NodeBO nodeBO) throws Exception;
	public Integer getNodeNo (NodeBO nodeBO) throws Exception;
}
