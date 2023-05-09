package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(4)
public class AroundTest {

	private Logger logger = LoggerFactory.getLogger(AroundTest.class);
	
	// @Around : Before + After가 합쳐진 어노테이션
	@Around("CommonPointcut.implPointcut()")
	public Object checkRunningTime(ProceedingJoinPoint jp) throws Throwable {
		
		// ProceedingJoinPoint 인터페이스 : 전/후처리 관련된 기능을 제공. 
		//								TargetObject의 값을 가져올 수 있는 메서드도 함께 제공.
		
		// proceed()메서드 호출 전 : @Before 용도로 사용할 advice 작성 
		// proceed()메서드 호출 후 : @After 용도로 사용할 advice 작성
		// 메서드의 마지막에 proceed()의 반환값을 리턴해줘야 함
		
		// before시작
		long start = System.currentTimeMillis(); // 시스템 서버시간을 ms단위로 나타낸 값
		// before끝
		
		Object obj = jp.proceed(); // 중간지점
		
		// after시작
		long end = System.currentTimeMillis();
		
		logger.info("Running time : {} ms", (end-start)); // printf문이랑 비슷
		// after끝
		return obj;
	}
	
}
