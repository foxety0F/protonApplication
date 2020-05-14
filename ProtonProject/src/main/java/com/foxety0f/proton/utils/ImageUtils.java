package com.foxety0f.proton.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.foxety0f.proton.common.domain.LoadedFiles;

public class ImageUtils {

	public String imagesPath = "images/";

	public void loadFileFromFE(MultipartFile file, String fileName, Integer id) {

	}

	public void loadFilesFromFE(MultipartFile[] files) {

	}

	public void loadFileFromByteArray(byte[] byteArray) {

	}

	public void loadFilesFromDataBase(List<LoadedFiles> files) {
		
		
		for (LoadedFiles file : files) {
			File fl = new File(imagesPath + "/" + file.getFileName());
			try {
				OutputStream os = new FileOutputStream(fl);
				os.write(file.getByteArray());
				os.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
