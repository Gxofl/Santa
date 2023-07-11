package com.spring.ex;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class MyBean implements InitializingBean {

	@Autowired
	ServletContext servletContext;

	@PostConstruct
	public void init() {
		System.out.println("------init()------");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("-----afterPropertiesSet()-----");
		
		String[] column = {"products", "users", "board", "mountain", "qna", "stamp"};
		
		for(int i=0; i<column.length; i++) {
			String path = "/resources/images/" + column[i];
			String uploadPath = servletContext.getRealPath(path);
			
			File destination = new File(uploadPath);
			
			/* 사용자 OS 확인 */
			//mkdir 사용하면 더 완성도 있을 듯
			String osName = System.getProperty("os.name").toLowerCase();
			System.out.println("OS name : " + osName);
			
			String str = "";
			if (osName.contains("win")) 
			{
				System.out.println("사용자 OS - Window ");
				str = "C:/tempUpload/" + column[i] ;
			} 
			
			else if (osName.contains("mac"))   {
				System.out.println("사용자 OS - MAC ");
				str = "/Users/ol7roeo/Documents/tempUpload/" + column[i]; 
			} 
			
			File destination_local = new File(str); // 임시 폴더
			
			FileUtils.copyDirectory(destination_local, destination);
		}
	}

}//MyBean