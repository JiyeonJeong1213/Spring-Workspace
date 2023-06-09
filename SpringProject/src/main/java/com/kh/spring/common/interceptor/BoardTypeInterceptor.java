package com.kh.spring.common.interceptor;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.BoardType;

public class BoardTypeInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BoardService boardService;
	
	// Filter -> dispatcherServlet -> interceptor -> controller
	
	@Override // 전처리할 메서드 작성
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		// application scope에 boardTypeList가 있는지 체크, 없을 경우 이를 조회하는 boardService메서드 호출 후 결과 셋팅
		
		// application scope객체 얻어오기
		ServletContext application = request.getServletContext();
		
		if(application.getAttribute("boardTypeList") == null) {
			ArrayList<BoardType> list = boardService.selectBoardTypeList();
			
			application.setAttribute("boardTypeList", list);
		}
		return true;
	}
	
	@Override // 후처리할 메서드 작성
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
		System.out.println("후처리 실행");
	}
	
	@Override // view화면까지 다 만들어지고 나서
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		System.out.println("view 처리 완료 후 수행");
	}
}
