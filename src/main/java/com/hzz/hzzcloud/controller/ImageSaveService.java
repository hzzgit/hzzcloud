package com.hzz.hzzcloud.controller;


import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

//文件上传基础service
@Service
@Slf4j
public class ImageSaveService {

    private String driverImg = "image";

    public JsonMessage saveDriverimg(HttpServletRequest request){
       return saveimg(request,driverImg);
    }


    private JsonMessage saveimg(HttpServletRequest request, String driverImg){
        try {
            return  saveDriverImg(request,driverImg);
        } catch (Exception e) {
            log.error("上传图片失败",e);
            return new JsonMessage(false,"上传失败");
        }
    }


    //这边的照片名称全部都是driverImg,多张就是driverImg1，driverImg2，driverImg3等
    private JsonMessage saveDriverImg(HttpServletRequest request,  String driverImg) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile(driverImg);
        String modulename = String.valueOf(multipartRequest.getParameter("modulename"));
        String path="D:\\"+modulename;
        String driverImgUrl = null;
        if (file != null) {
            String uploadFileName = file.getOriginalFilename();
            //   String fileFormat = uploadFileName.split("\\.")[1];
            //获取最后一个.的位置
            int lastIndexOf = uploadFileName.lastIndexOf(".");
            //获取文件的后缀名 .jpg
            String fileFormat = uploadFileName.substring(lastIndexOf);

            InputStream is = null;
            is = file.getInputStream();

            byte[] btImg = readInputStream(is);// 得到图片的二进制数据
            try {
                //如果文件夹不存在就创建文件夹
                File folder = new File(path);
                if (!folder.exists() && !folder.isDirectory()) {
                    folder.mkdirs();
                }
                File newFile = new File(path + File.separator + uploadFileName);
                FileOutputStream fops = new FileOutputStream(newFile);
                fops.write(btImg);
                fops.flush();
                fops.close();

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        return new JsonMessage(true,modulename+"上传成功")  ;
    }


    private byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
