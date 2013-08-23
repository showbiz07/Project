package com.travellist.travel.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.travellist.config.sqlmap.SqlSessionManager;
import com.travellist.travel.dao.NodeDAO;
import com.travellist.travel.object.NodeBO;
import com.travellist.travel.object.TravelInfoBO;

public class NodeDAOImpl implements NodeDAO {
  	private SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlMapper();
	private SqlSession session = sqlSessionFactory.openSession(true);
	
	public List<NodeBO> getNodeList(TravelInfoBO travelInfoBO) throws Exception{
		return session.selectList("node.getNodeLIst", travelInfoBO);
	}
	
	public void insertNode(NodeBO nodeBO) throws Exception{
		session.insert("node.insertNode", nodeBO);
	}
	
	public Integer getNodeNo (NodeBO nodeBO) throws Exception{
		return session.selectOne("node.getNodeNo");
	}
	
	

}
