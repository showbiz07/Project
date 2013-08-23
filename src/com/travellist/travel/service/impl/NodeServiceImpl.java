package com.travellist.travel.service.impl;

import java.util.List;

import com.travellist.travel.dao.NodeDAO;
import com.travellist.travel.dao.impl.NodeDAOImpl;
import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;
import com.travellist.travel.service.NodeService;

public class NodeServiceImpl implements NodeService{
	private NodeDAO nodeDAO = new NodeDAOImpl();

	public List<NodeBO> getNodeList(TravelInfoBO travelInfoBO) throws Exception{
		return nodeDAO.getNodeList(travelInfoBO);
	}
	
	public void insertNode(NodeBO nodeBO) throws Exception{
		if( nodeBO.getNodeNo() == null ){
			Integer nodeNo = nodeDAO.getNodeNo(nodeBO);
			nodeBO.setNodeNo(nodeNo);
		}
		
		nodeDAO.insertNode(nodeBO);
	}
}
