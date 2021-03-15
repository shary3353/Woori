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

	public ProductDTO regist() { //물품등록하기
		String savePath = "D:/MVC/Woori/WebContent/img";//1. 저장할 폴더 지정
		int maxSize = 10*1024*1024;//2. 사이즈 지정
		ProductDTO dto = null; //초기값- null
		
		//파일 저장(MultipartRequest) 
		try {
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize,"UTF-8"); //파일저장 끝
			dto = new ProductDTO(); //dto 객체 생성
			//String idx = multi.getParameter("idx");//수정시 고칠것
			String p_name = multi.getParameter("p_name"); // enctype="multipart/form-data" 로 보내기때문에 req로는 받을 수 없다. -- multi로 받을 것 
			int c_idx = Integer.parseInt(multi.getParameter("c_idx"));
			int p_price =  Integer.parseInt(multi.getParameter("p_price"));
			String p_content = multi.getParameter("p_content");
			String sid = multi.getParameter("sid");
			System.out.println("등록할 물품정보: "+p_name+"/"+c_idx+"/"+p_price+"/"+p_content+"/"+sid); //값확인
			/*
			if(idx != null) {//수정시 고칠것
				dto.setIdx(Integer.parseInt(idx));
			}
			*/
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
				File oriFile = new File(savePath+oriFileName); //pathname에 해당되는 파일의 File 객체를 생성
				File newFile = new File(savePath+newFileName); //
				oriFile.renameTo(newFile); //원래 파일 이름 변경 -- 새이름
				dto.setOriFileName(oriFileName); //dto에 파일 이름 넣어줌.
				dto.setNewFileName(newFileName);				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	

}
