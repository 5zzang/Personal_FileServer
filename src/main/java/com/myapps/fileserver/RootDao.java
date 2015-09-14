package com.myapps.fileserver;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class RootDao {
	private static final Logger logger = LoggerFactory.getLogger(RootDao.class);

	@Autowired @Qualifier("sqlSession") private SqlSession sqlSession;
	
	
	public FileInfoDomain selectFileInfo(String fileName) {
		logger.info("selectFileInfo");
		
		return sqlSession.selectOne("Mapper.selectFileInfo", fileName);
	}
	
	public int insertFileInfo(HashMap<String, Object> params) {
		logger.info("insertFileInfo");
		
		return sqlSession.insert("Mapper.insertFileInfo", params);
	}
}
