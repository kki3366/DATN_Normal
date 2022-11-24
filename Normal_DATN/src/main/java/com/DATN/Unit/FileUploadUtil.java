package com.DATN.Unit;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	
	private static String generateRandomName(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz!@$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	private String getFileNameForEntity;
	
	public void saveFile(MultipartFile file, ServletContext app) throws IllegalStateException, IOException {
		if(file != null) {
			String getFile = file.getOriginalFilename();
			String getExtension = FilenameUtils.getExtension(getFile);
			String filenamnRandom = generateRandomName(20);
			getFileNameForEntity = filenamnRandom;
			String filename = filenamnRandom + "." + getExtension;
			
			File saveFile = new File(app.getRealPath("/imgProducts/" + filename));
			file.transferTo(saveFile);
		}else {
			File getDefaulImg = new File(app.getRealPath("/defaulImgProducts/default.png"));
			String getExtenstionOfDefault = FilenameUtils.getExtension(getDefaulImg.getName());
			String rdFileName = generateRandomName(20);
			getFileNameForEntity = rdFileName;
			String fileDefault = rdFileName + "." + getExtenstionOfDefault;
			File saveFile = new File(app.getRealPath("/imgProducts/" + fileDefault));
			FileUtils.copyFile(getDefaulImg, saveFile);
//			System.err.println(getExtenstionOfDefault);
//			System.err.println("đang null");
		}
	}
	
	
	public void saveFileNewVer(String filename, MultipartFile multipartFile,ServletContext app) throws IOException {
		Path test = Paths.get(app.getRealPath("/imgProducts"));
		try (InputStream ips = multipartFile.getInputStream()){
			Path filePath = test.resolve(filename);
			Files.copy(ips, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			throw new IOException("lỗi",e);
		}
	}
	
	public void deleteFileByName(String filename,ServletContext app) throws IOException {
		File getFiletoDelete = new File(app.getRealPath("/imgProducts/" + filename + ".png"));
		if(getFiletoDelete.exists()) {
			FileUtils.delete(getFiletoDelete);
		}
	}
	

	public String getGetFileNameForEntity() {
		return getFileNameForEntity;
	}
}
