package com.kh.spring.chat.model.vo;

import lombok.Data;

@Data
public class ChatRoom {

	private int chatRoomNo;
	private String title;
	private String status;
	private int userNo; // 방장의 userNo
	
	private String nickName;
	private int cnt; // 채팅방에 접속한 사용자 수
}
