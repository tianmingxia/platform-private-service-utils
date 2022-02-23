package cn.iald.platform.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.iald.platform.base.vo.FileVO;
import cn.iald.platform.base.vo.ResponseVO;
import cn.iald.platform.constant.FileConstant;
import cn.iald.platform.exception.BusinessException;
import cn.iald.platform.util.MessageUtil;
import cn.iald.platform.util.ResponseUtil;
import cn.iald.platform.util.UUIDGenerator;
import cn.iald.platform.util.UploadFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 上传文件Controller
 *
 * @author wangyc
 * @date 2020-12-17 10:00:00
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
    public ResponseVO imgUpload(MultipartFile file, HttpServletRequest request) {
        log.info("图片开始上传");
        if (file == null) {
            throw new BusinessException(MessageUtil.getMessage("web.file.none", request));
        }
        // 获得上传文件名字
        String fileName = file.getOriginalFilename();
        if (StrUtil.isBlank(fileName)) {
            throw new BusinessException(MessageUtil.getMessage("web.file.none", request));
        }
        // 指定上传文件目录
        String toPath = request.getSession().getServletContext().getRealPath(FileConstant.TEMP_FILE_PATH);
        // uuid重命名
        String newFileName = new UUIDGenerator().generate().toString()
                + fileName.substring(fileName.lastIndexOf("."));
        // 上传文件到临时文件夹
        UploadFileUtil.uploadFile(file, newFileName, toPath);
        // 上传文件到远程服务器
        String resStr = UploadFileUtil.uploadHttpFile(toPath, newFileName, imgServeUrl + FileConstant.UPLOAD_URL);
        // 删除临时文件
        UploadFileUtil.deleteFile(toPath, newFileName);
        if (resStr.indexOf(FileConstant.SPLIT_STR) > -1) {
            resStr = resStr.substring((resStr.indexOf(":") + 1), resStr.indexOf("</h1>"));
        }
        return ResponseUtil.success(resStr.trim());
    }

    /**
     * 多张图片上传
     *
     * @param fileList
     * @param request
     * @return
     */
    @RequestMapping("/imgsUpload")
    public ResponseVO imgsUpload(MultipartFile[] fileList, HttpServletRequest request) {
        log.info("多张图片开始上传");
        if (ArrayUtil.isEmpty(fileList)) {
            throw new BusinessException(MessageUtil.getMessage("web.file.none", request));
        }
        List<FileVO> fileVoList = new ArrayList<>();
        for (MultipartFile file : fileList) {
            // 获得上传文件名字
            String fileName = file.getOriginalFilename();
            if (StrUtil.isBlank(fileName)) {
                throw new BusinessException(MessageUtil.getMessage("web.file.none", request));
            }
            // 指定上传文件目录
            String toPath = request.getSession().getServletContext().getRealPath(FileConstant.TEMP_FILE_PATH);
            // uuid重命名
            String newFileName = new UUIDGenerator().generate().toString()
                    + fileName.substring(fileName.lastIndexOf("."));
            // 上传文件到临时文件夹
            UploadFileUtil.uploadFile(file, newFileName, toPath);
            // 上传文件到远程服务器
            String resStr = UploadFileUtil.uploadHttpFile(toPath, newFileName, imgServeUrl + FileConstant.UPLOAD_URL);
            // 删除临时文件
            UploadFileUtil.deleteFile(toPath, newFileName);
            if (resStr.indexOf(FileConstant.SPLIT_STR) > -1) {
                resStr = resStr.substring((resStr.indexOf(":") + 1), resStr.indexOf("</h1>"));
                fileVoList.add(new FileVO(fileName, resStr.trim()));
            }
        }
        return ResponseUtil.success(fileVoList);
    }
}
