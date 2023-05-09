package com.kh.spring.board.model.service;

import java.util.List;

import com.kh.spring.board.model.vo.Reply;

public interface ReplyService {

	// 댓글등록
	int insertReply(Reply reply);
	// public abstract 자동으로 앞에 붙음 interface라서 추상메소드만 사용 가능하기 때문에
	
	// 댓글목록조회
	List<Reply> selectReplyList(int bno);
	
	// 댓글삭제
	int deleteReply(int replyNo);
	
	// 댓글수정
	int updateReply(Reply reply);
}
