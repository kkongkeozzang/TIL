package kh.spring.controller;

import java.io.DataOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kh.spring.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private FileService fService;

	@RequestMapping("download")
	public void download(HttpServletResponse response, String oriName, String sysName) throws Exception {

		String realPath = session.getServletContext().getRealPath("upload"); 	 // 파일 위치 경로를 획득
		try(
				DataOutputStream dos = new DataOutputStream(response.getOutputStream());
				){
			
			byte[] fileContents = fService.getFileContents(realPath,sysName,oriName);					 // 대상 파일을 적재할 메모리 공간 확보
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename="+ fService.getEncodedOriName(oriName));

			dos.write(fileContents);
			dos.flush();
		}
	}
}
