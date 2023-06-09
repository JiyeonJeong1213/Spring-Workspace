package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.BoardType;

@Repository
public class BoardDao {

	@Autowired
	SqlSession sqlSession;
	
	public ArrayList<BoardType> selectBoardTypeList() {
		return (ArrayList)sqlSession.selectList("boardMapper.selectBoardTypeList");
	}
}
