package cn.iald.platform.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.constants.SysConstants;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.utils.ResponseUtils;
import cn.iald.platform.utils.UUIDGenerator;
import cn.iald.platform.utils.UploadFileUtils;
import cn.iald.platform.vo.FileVo;
import cn.iald.platform.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 上传文件Controller
 * @author: wangyc
 * @create: 2020-12-17 10:00
 **/
@Slf4j
@RestController
public class UploadFileController {

	@Value("${imgServeUrl:}")
	private String imgServeUrl;

	/**
	 * web端图片上传
	 *
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/imgUpload")
	public ResponseVo imgUpload(MultipartFile file, HttpServletRequest request) throws IOException {
		log.info("图片开始上传");
		if (file == null) {
			return ResponseUtils.error("上传图片不能为空");
		}
		// 获得上传文件名字
		String fileName = file.getOriginalFilename();
		if (StrUtil.isBlank(fileName)) {
			throw new BusinessException("上传图片不能为空");
		}
		// 指定上传文件目录
		String toPath = request.getSession().getServletContext().getRealPath(SysConstants.TEMP_FILE_PATH);
		// uuid重命名
		String newFileName = new UUIDGenerator().generate().toString() + fileName.substring(fileName.lastIndexOf("."));
		// 上传文件到临时文件夹
		UploadFileUtils.uploadFile(file, newFileName, toPath);
		// 上传文件到远程服务器
		String resStr = UploadFileUtils.uploadHttpFile(toPath, newFileName, imgServeUrl + SysConstants.UPLOAD_URL);
		// 删除临时文件
		UploadFileUtils.deleteFile(toPath, newFileName);
		if (resStr.indexOf(SysConstants.SPLIT_STR) > -1) {
			resStr = resStr.substring((resStr.indexOf(":") + 1), resStr.indexOf("</h1>"));
		}
		return ResponseUtils.success(resStr.trim());
	}

	/**
	 * 多张图片上传
	 *
	 * @param fileList
	 * @param request
	 * @return
	 */
	@RequestMapping("/imgsUpload")
	public ResponseVo imgsUpload(MultipartFile[] fileList, HttpServletRequest request) throws IOException {
		log.info("多张图片开始上传");
		if (ArrayUtil.isEmpty(fileList)) {
			return ResponseUtils.error("上传图片不能为空");
		}
		List<FileVo> fileVoList = new ArrayList<>();
		for (MultipartFile file : fileList) {
			// 获得上传文件名字
			String fileName = file.getOriginalFilename();
			if (StrUtil.isBlank(fileName)) {
				throw new BusinessException("上传图片不能为空");
			}
			// 指定上传文件目录
			String toPath = request.getSession().getServletContext().getRealPath(SysConstants.TEMP_FILE_PATH);
			// uuid重命名
			String newFileName = new UUIDGenerator().generate().toString() + fileName.substring(fileName.lastIndexOf("."));
			// 上传文件到临时文件夹
			UploadFileUtils.uploadFile(file, newFileName, toPath);
			// 上传文件到远程服务器
			String resStr = UploadFileUtils.uploadHttpFile(toPath, newFileName, imgServeUrl + SysConstants.UPLOAD_URL);
			// 删除临时文件
			UploadFileUtils.deleteFile(toPath, newFileName);
			if (resStr.indexOf(SysConstants.SPLIT_STR) > -1) {
				resStr = resStr.substring((resStr.indexOf(":") + 1), resStr.indexOf("</h1>"));
				fileVoList.add(new FileVo(fileName, resStr.trim()));
			}
		}
		return ResponseUtils.success(fileVoList);
	}
}
