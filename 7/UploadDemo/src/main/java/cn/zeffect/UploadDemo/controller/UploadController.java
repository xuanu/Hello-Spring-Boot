package cn.zeffect.UploadDemo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.zeffect.UploadDemo.bean.ResultInfo;

@RestController
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping("/upload.html")
	public ModelAndView upload() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("upload");
		return modelAndView;
	}
	
	@RequestMapping("/uploads.html")
	public ModelAndView uploads() {
		ModelAndView view = new ModelAndView();
		view.setViewName("uploads");
		return view;
	}

	@RequestMapping("upload.action")
	public ResultInfo upload(@RequestParam(name = "file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResultInfo(-1, "上传失败，文件为空！");
		}
		File destFile = getDestFile(file);
		if (destFile.exists()) {
			destFile.delete();
		}
		try {
			file.transferTo(destFile);
			return new ResultInfo(1, "上传成功!");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResultInfo(-1, "上传失败！");
	}

	@RequestMapping("uploads.action")
	public ResultInfo upload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartRequest) request).getFiles("file");
		List<Integer> indexStatus = new ArrayList<Integer>(files.size());
		boolean allStutas = true;
		for (int i = 0; i < files.size(); i++) {
			MultipartFile file = files.get(i);
			if (file.isEmpty()) {
				indexStatus.add(-1);
				allStutas = false;
				continue;
			}
			File destFile = getDestFile(file);
			if (destFile.exists()) {
				destFile.delete();
			}
			boolean saveStatus = false;
			try {
				file.transferTo(destFile);
				saveStatus = true;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				indexStatus.add(saveStatus ? 1 : -1);
				if (!saveStatus) {
					allStutas = false;
				}
			}
		}

		return new ResultInfo(allStutas ? 1 : -1, allStutas ? "上传成功！" : "上传失败！", indexStatus);
	}

	private File getDestFile(MultipartFile file) {
		// 存放应该要有一定的规律，这里就算了。
		String fileName = file.getOriginalFilename();
		File path = null;
		try {
			path = new File(ResourceUtils.getURL("classpath:").getPath());
			//需要注意事项：https://blog.csdn.net/heylun/article/details/78732451
		} catch (FileNotFoundException e) {
			throw new NullPointerException("未找到正确目录！");
		}
		File destDir = new File(path, "static/upload/");
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(destDir, fileName);
	}

}
