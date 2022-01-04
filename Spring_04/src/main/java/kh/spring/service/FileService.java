package kh.spring.service;

import java.io.File;
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
	
	public List<FilesDTO> getFileList(String realPath, int parentSeq, MultipartFile[] file) throws Exception {
		
		List<FilesDTO> list = new ArrayList<>();
		
		for(MultipartFile mf : file) {
			if(!mf.isEmpty()) {
				
				File realPathFile = new File(realPath);
				if(!realPathFile.exists()) {realPathFile.mkdir();}
				
				String oriName = mf.getOriginalFilename();
				String sysName = UUID.randomUUID()+"_"+oriName;
				
				mf.transferTo(new File(realPath+"/"+sysName)); // 첨부된 파일 폴더에 업로드 하는 부분 
				list.add(new FilesDTO(0, oriName, sysName, parentSeq));  // 첨부된 파일 정보를 DB에 저장하는 부분
			}
		}
		return list;
	}
	
}
