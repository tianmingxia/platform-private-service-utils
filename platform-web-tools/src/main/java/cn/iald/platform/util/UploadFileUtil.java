package cn.iald.platform.util;

import cn.hutool.http.*;
import cn.iald.platform.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 文件上传工具类
 *
 * @author wangyc
 * @date 2020-12-17 11:01
 **/
@Slf4j
public class UploadFileUtil {

    /**
     * 保存文件
     *
     * @param file
     * @param toFileName
     * @param toPath
     * @throws IllegalStateException
     * @throws IOException
     */
    public static void uploadFile(MultipartFile file, String toFileName, String toPath) {
        try {
            File targetFile = new File(toPath, toFileName);
            //没有文件目录则新建
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException(e.getMessage());
        }
    }


    /**
     * 上传图片到远程服务器
     *
     * @param filePath    图片地址
     * @param fileName    图片名称
     * @param imgServeUrl 远程地址
     * @return
     */
    public static String uploadHttpFile(String filePath, String fileName, String imgServeUrl) {
        File file = new File(filePath + File.separator + fileName);
        if (file.isFile()) {
            HashMap<String, Object> paramMap = new HashMap<>(16);
            paramMap.put("file", file);
            HttpResponse httpResponse = HttpRequest.post(imgServeUrl).form(paramMap).timeout(15 * 1000)
                    .header(Header.CONTENT_TYPE, ContentType.MULTIPART.getValue())
                    .execute();
            String resStr = httpResponse.body();
            if (httpResponse != null && httpResponse.getStatus() == HttpStatus.HTTP_OK) {
                log.info("返回信息：" + resStr);
                return resStr;
            } else {
                throw new BusinessException("network anomaly[" + httpResponse.getStatus() + "]:" + resStr);
            }
        }
        throw new BusinessException("File upload exception");
    }

    /**
     * 删除文件
     *
     * @param filePath 文件所在文件夹
     * @param fileName 文件名
     */
    public static void deleteFile(String filePath, String fileName) {
        File file = new File(filePath + File.separator + fileName);
        if (file.isFile()) {
            file.delete();
        }
    }
}
