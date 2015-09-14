package com.myapps.fileserver;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.myapps.fileserver.common.BaseController;
import com.myapps.fileserver.common.Constant;


@Controller
public class RootController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public String fileServer(@RequestParam("file") MultipartFile file, HttpServletRequest req) {
		logger.info("fileServer");
		
		FileInfoDomain fileInfo = null;
		
		// 클라이언트 정보
		String clientIp = req.getLocalAddr();
		String clientName = req.getLocalName();
		
		// 원본 파일정보
		String originalFileName = file.getOriginalFilename();
		long size = file.getSize();
		int hashCode = file.hashCode();
		String contentType = file.getContentType();
		
		// '년도/월'의 형태로 Path명을 생성한다.
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		String dir = Constant.Directory.ROOT_DIR + "/" + cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1) +"/";
		
		// 파일의 HashCode로 저장할 파일명을 생성한다.
		String fileName = Integer.toString(hashCode);
		
		// 파일을 저장한다.
		long newFileSize = 0;
		String newFileDate = null;
		
		try {
			FileWriter fw = new FileWriter(dir, fileName, file.getBytes()); 
			newFileSize = fw.getSize();
			newFileDate = fw.getDate();
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		
		// 원본 파일의 파일 사이즈와 저장된 파일의 파일 사이즈를 비교한다.
		if (size == newFileSize) {
			
		} else {
			
		}
		
		return gson.toJson(fileInfo);
	}
}