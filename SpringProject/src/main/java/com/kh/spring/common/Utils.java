package com.kh.spring.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;


public class Utils { // Util클래스의 메서드들은 static으로 작성

	// 변경된 이름을 돌려주면서, 원본파일을 변경된 파일이름으로 서버에 저장시키는 메소드
	static public String saveFile(MultipartFile upfile, String savePath) throws IllegalStateException, IOException {
		String originName = upfile.getOriginalFilename(); // "user.jpg"
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int random = (int)(Math.random() * 90000 + 10000);
		
		String ext = originName.substring(originName.lastIndexOf(".")); // 확장자
		
		String changeName = currentTime+random+ext;
		
		upfile.transferTo(new File(savePath+changeName)); // 메모리상에 존재했던 파일을 실제 경로에 저장시켜주는 코드
		
		return changeName;
	}
}
