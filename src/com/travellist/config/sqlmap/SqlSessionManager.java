package com.travellist.config.sqlmap;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionManager {
	 
	 private static final SqlSessionFactory sqlSessionFactory;
	 
	 static {
		 try{
			   String resource = "com/travellist/config/sqlmap/sqlmap-config.xml";
			   Reader reader = Resources.getResourceAsReader(resource);
			   sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		  }catch (Exception e) {
		            e.printStackTrace();
		            throw new RuntimeException(e);
		  }
	 }

	 public SqlSessionManager(){}
	 
	 public static SqlSessionFactory getSqlMapper(){
	  return sqlSessionFactory;
	 }
}

