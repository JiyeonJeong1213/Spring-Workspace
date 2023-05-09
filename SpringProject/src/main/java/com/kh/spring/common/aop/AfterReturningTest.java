package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
@Order(5)
public class AfterReturningTest {
	
	private Logger logger = LoggerFactory.getLogger(AfterReturningTest.class);

	// @AfterReturning : 메서드 실행 이후에 반환값을 얻어오는 기능의 어노테이션
	// returning : 반환값을 저장할 매개변수명을 지정하는 속성
	@AfterReturning(pointcut = "CommonPointcut.implPointcut()", returning = "returnObj")
	public void returnValue(JoinPoint jp, Object returnObj) { // JoinPoint는 항상 첫번째 매개변수
		
		if(returnObj instanceof Member) { // 리턴값이 Member라면
			Member loginMember = (Member)returnObj;
			//loginMember.setNickName("캬캬캬캬캬캬캬캬캬캬캬캬캬"); // returnObj반환값을 통해 정보를 조작할 수 있음
		}
		logger.info("return value : {}", returnObj);
	}
}
