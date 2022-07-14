package com.api.book.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	//Static path set
	//public final String UPLOAD_DIR="C:\\Users\\kushvina\\eclipse-workspace\\bootrestbook\\src\\main\\resources\\static\\image";
	public final String UPLOAD_DIR=new ClassPathResource("static/image/").getFile().getAbsolutePath();
	//Dynamic path set
	public FileUploadHelper()throws IOException{
		
	}
	public boolean uploadFile(MultipartFile multipart) {
		boolean f= false;
		try {
			//For OLD APIS
			/*InputStream is=multipart.getInputStream();
			byte data[]=new byte[is.available()];
			is.read(data);
			//write
			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+multipart.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();*/
			
			
			Files.copy(multipart.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipart.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			//Files.copy(multipart.getInputStream(), UPLOAD_DIR+File.separator+multipart.getOriginalFilename(), StandardCopyOption.REPLACE_EXISTING);
			f=true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
