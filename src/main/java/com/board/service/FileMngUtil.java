package com.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map.Entry;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.board.VO.FileVO;

@Component("fileMngUtil")
public class FileMngUtil {
	private static final String SAVE_PATH = "W:\\eGovFrameDev-3.9.0-64bit\\workspace\\picture";

	public List<FileVO> uploadFiles(Map<String, MultipartFile> files) {
		List<FileVO> fileList = new ArrayList<FileVO>();
		FileVO fileVO;
		MultipartFile file;
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
		}
		return fileList;
	}

	public FileVO uploadFile(MultipartFile file) {
		FileVO fileVO = new FileVO();
		UUID saveName = UUID.randomUUID();
		String originalName = file.getOriginalFilename();
		long size = file.getSize();

		if (originalName.length() != 0 && originalName != null) {
			String ext = originalName.substring(originalName.lastIndexOf("."), originalName.length());
			String dataName = saveName.toString().substring(0, 10) + ext;
			String realPath = SAVE_PATH + File.separator + dataName;
			fileVO.setOriginalName(originalName);
			fileVO.setRealPath(dataName);
			fileVO.setSize(size);
			fileVO.setParseSize(parseSize(size));

			System.out.println("uploadFile : " + fileVO.toString());

			try {
				byte[] data = file.getBytes();
				FileOutputStream fos = new FileOutputStream(realPath);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileVO;
	}

	public void delteFiles(List<FileVO> fileList) {
		for (FileVO f : fileList) {
			deleteFile(f);
		}
	}

	public void deleteFile(FileVO fileVO) {
		String saveFilePath = SAVE_PATH + File.separator + fileVO.getRealPath();
		File file = new File(saveFilePath);

		if (file.exists()) {
			if (file.delete()) {
				System.out.println("...delete" + saveFilePath + "...");
			} else {
				System.out.println("...delete 실패 " + saveFilePath + "...");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다." + saveFilePath + "");
		}
	}

	public void downloadFile(FileVO file, HttpServletResponse response) {
		String dataName = file.getRealPath();
		String saveFilePath = SAVE_PATH + File.separator + dataName;
		String ext = dataName.substring(dataName.lastIndexOf("."), dataName.length());

		File tmpFile = new File(saveFilePath);
		long filelength = tmpFile.length();
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getOriginalName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", ext);
		response.setHeader("Content-Length", "" + filelength);
		response.setHeader("Pragma", "co-cache;");
		response.setHeader("Expires", "-1;");

		try (FileInputStream fis = new FileInputStream(saveFilePath); OutputStream out = response.getOutputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
			fis.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String parseSize(long size) {
		long kb = 1024;
		long mb = 1048576;
		String result = "";

		if (size < 1024) {
			result += (size + "bytes");
		} else if (size >= kb && size < mb) {
			result += String.format("%.1f KB", (double) size / kb);
		} else if (size >= mb) {
			result += String.format("%.1f MB", (double) size / mb);
		}
		return result;
	}
}
