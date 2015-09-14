package com.myapps.fileserver;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileWriter {
	private static final Logger logger = LoggerFactory.getLogger(FileWriter.class);
	
	private Path path = null;
	private long fileSize = 0;
	private String fileDate = null;
	
	
	public FileWriter(String dir, String fileName, byte[] fileBytes) {
		logger.info("FileWriter");
		
		createFile(dir, fileName, fileBytes);
	}
	
	private void createFile(final String dir, final String fileName, final byte[] fileBytes) {
		logger.info("createFile");
		
		path = FileSystems.getDefault().getPath(dir, fileName);
		
		try {
			Files.write(path, fileBytes, StandardOpenOption.CREATE);
			
			// 생성된 파일의 정보를 반환한다.
			fileSize = Files.size(path);
			fileDate = Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS).toString();
		} catch (IOException e) {
			logger.info(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	public long getSize() {
		return this.fileSize;
	}
	
	public String getDate() {
		return this.fileDate;
	}
}
