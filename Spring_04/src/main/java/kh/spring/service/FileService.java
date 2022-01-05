package kh.spring.service;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kh.spring.dao.FilesDAO;
import kh.spring.dto.FilesDTO;

@Service
public class FileService {

	@Autowired
	private FilesDAO dao;
	
	public int insert(FilesDTO dto) {
		return dao.insert(dto);
	}
	
	public List<FilesDTO> selectAll(int seq) {
		return dao.selectAll(seq);
	}
	
	public byte[] getFileContents(String realPath, String sysName, String oriName) throws Exception {
		
		File target = new File(realPath+"/"+sysName);
		byte[] fileContents = new byte[(int)target.length()];
		try(DataInputStream dis = new DataInputStream(new FileInputStream(target));  // 대상 파일에 대한 InputStream 개방
				){
			dis.readFully(fileContents);											 // 대상 파일 로딩
			return fileContents;
		}
	}
	
	public String getEncodedOriName(String oriName) throws Exception {
		return oriName = new String(oriName.getBytes(), "ISO-8859-1");
	}
}
