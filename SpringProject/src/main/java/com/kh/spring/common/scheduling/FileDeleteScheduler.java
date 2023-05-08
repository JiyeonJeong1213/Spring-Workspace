package com.kh.spring.common.scheduling;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kh.spring.board.model.service.BoardService;

@Component // 개발자가 직접 작성한 Class를 Bean으로 등록하기 위한 Annotation (scheduler관련 메소드 사용 가능)
public class FileDeleteScheduler {

	private Logger logger = LoggerFactory.getLogger(FileDeleteScheduler.class);

	@Autowired
	private ServletContext application; // 내가 등록한 적은 없지만 등록되어있음

	@Autowired
	private BoardService service;

	// 1. BOARD_IMG테이블 안에 있는 이미지 목록들 모두 조회하여
	// 2. images/boardT 디렉토리 안에 있는 이미지들과 대조하여
	// 3. 일치하지 않는 이미지 파일들을 삭제(DB에는 없는 데이터인데, boardT에는 존재하는 경우)
	// 4. 우선 5초간격으로 테스트 후 정상적으로 작동한다면 매달 1일 정시에 실행되는 스케줄러로 만들기

	@Scheduled(cron = "0 0 0 1 * *")
	public void deleteFile() {
		logger.info("파일 삭제 시작");

		// 1) BOARD_IMG테이블 안에 있는 모든 파일 목록들 조회
		List<String> dbList = service.selectAllBoardImgs();

		// 2) images/boardT폴더 아래에 존재하는 모든 이미지 파일목록 조회(File클래스 활용)
		File path = new File(application.getRealPath("/resources/images/boardT"));
		// path가 참조하고 있는 폴더에 들어가서 모든 파일을 File배열로 얻어오기
		String[] serverList = path.list(); // 파일 이름만 
		File[] files = path.listFiles(); // 파일

		// 3. 두 목록을 비교해서 일치하지 않는 파일 삭제(삭제시 File클래스의 delete()활용)
		for (int i = 0; i < serverList.length; i++) {
			// 방법1
			int count = 0;
			for (int j = 0; j < dbList.size(); j++) {
				if (!serverList[i].equals(dbList.get(j))) {
					count++;
				}
			}
			if (count == dbList.size()) {
				files[i].delete();
				logger.info(serverList[i] + "파일 삭제함");
			}
			
//			// 방법2
//			// List.indexOf(value) : List에 value와 같은 값이 있으면 인덱스 반환/없으면 -1반환
//			if(dbList.indexOf(serverList[i]) == -1) {
//				files[i].delete();
//			}
		}
		logger.info("서버 파일 삭제작업 끝");
	}
}
