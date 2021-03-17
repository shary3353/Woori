package com.woori.product.service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.woori.product.dto.ProductDTO;
import com.oreilly.servlet.MultipartRequest;

public class FileService {
	
	//공통
	HttpServletRequest req = null;

	public FileService(HttpServletRequest req) {
		this.req = req;
	}

	public ProductDTO regist() { //물품파일 등록하기
		//String savePath = "D:/MVC/Woori/WebContent/Uploaded_Img/";//1. 저장할 폴더 지정
		String savePath = req.getSession().getServletContext().getRealPath("Uploaded_Img");
		//D:\MVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Woori\Uploaded_Img
		String fullpath = savePath +"\\";
		System.out.println("사진파일저장경로 :" +savePath);
		int maxSize = 10*1024*1024;//2. 사이즈 지정
		ProductDTO dto = null; //초기값- null
		
		//파일 저장(MultipartRequest) 
		try {
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize,"UTF-8"); //파일저장 끝
			dto = new ProductDTO(); //dto 객체 생성
			String p_idx = multi.getParameter("p_idx");//수정시 사용
			String p_name = multi.getParameter("p_name"); // enctype="multipart/form-data" 로 보내기때문에 req로는 받을 수 없다. -- multi로 받을 것 
			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			int p_price =  Integer.parseInt(multi.getParameter("p_price"));
			String p_content = multi.getParameter("p_content");
			String sid = multi.getParameter("sid");
			System.out.println("등록할 물품정보: "+p_name+"/"+c_idx+"/"+p_price+"/"+p_content+"/"+sid+"/ 수정 시p_idx:"+p_idx); //값확인
			
			if(p_idx != null) {//수정시 사용
				dto.setP_idx(Integer.parseInt(p_idx));
			}
			dto.setP_name(p_name);//dto에 넣어줌.
			dto.setC_idx(c_idx);
			dto.setP_price(p_price);
			dto.setP_content(p_content);
			dto.setSid(sid);
			
			String oriFileName = multi.getFilesystemName("photo");//원본파일명 추출
			if(oriFileName != null ) { // 파일 업로드가 되었을 경우에만....
				String ext = oriFileName.substring(oriFileName.lastIndexOf(".")); //확장자
				String newFileName = System.currentTimeMillis()+ext;//새파일명 만들기(원본파일의 확장자 추가)
				//파일 이름 변경
				/*
				File oriFile = new File(savePath+oriFileName); //pathname에 해당되는 파일의 File 객체를 생성
				File newFile = new File(savePath+newFileName); //
				*/
				File oriFile = new File(fullpath+oriFileName); //pathname에 해당되는 파일의 File 객체를 생성
				File newFile = new File(fullpath+newFileName); //
				oriFile.renameTo(newFile); //원래 파일 이름 변경 -- 새이름
				dto.setOriFileName(oriFileName); //dto에 파일 이름 넣어줌.
				dto.setNewFileName(newFileName);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void delete(String delFileName) { //파일삭제
		//String savePath = "D:/MVC/Woori/WebContent/Uploaded_Img/";=
		String savePath = req.getSession().getServletContext().getRealPath("Uploaded_Img");
		//D:\MVC\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Woori\Uploaded_Img
		String fullpath = savePath +"\\";
		File file = new File(fullpath+delFileName);
		if(file.exists()) {//이 파일이 존재 하는지?
			boolean success = file.delete();
			System.out.println("기존 사진 파일 삭제 성공 여부 : "+success);
		}
		
	}
	
	

}
