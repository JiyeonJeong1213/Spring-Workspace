package com.kh.spring.board.model.vo;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Board {

	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private int count;
	private String createDate; // 원하는 형태의 날짜로 얻어오기 위해서 문자열로 바꿈
	private String status;
	private String boardCd;
	
	// 다른 테이블이랑 조인해서 받아온 데이터 넣어줄 필드
	private String originName;
	private String changeName;
	private String nickName;
	
	private String thumbnail;
	
	private String profileImage;
	
	private ArrayList<BoardImg> imgList;
	
}
