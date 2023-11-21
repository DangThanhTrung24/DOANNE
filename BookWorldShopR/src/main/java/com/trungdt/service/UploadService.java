package com.trungdt.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	// admin up hình 
	File save(MultipartFile file, String folder);
}
