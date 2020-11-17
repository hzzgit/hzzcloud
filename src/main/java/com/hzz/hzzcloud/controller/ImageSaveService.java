package com.hzz.hzzcloud.controller;


import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//文件上传基础service
@Service
@Slf4j
public class ImageSaveService {

    private String driverImg = "image";

    public JsonMessage saveDriverimg(HttpServletRequest request) {
        return saveimg(request, driverImg);
    }


    @Autowired
    private DataSource dataSource;

    private void save() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicle values (1,2,3)");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private JsonMessage saveimg(HttpServletRequest request, String driverImg) {
        try {
            return saveDriverImg(request, driverImg);
        } catch (Exception e) {
            log.error("上传图片失败", e);
            return new JsonMessage(false, "上传失败");
        }
    }


    //这边的照片名称全部都是driverImg,多张就是driverImg1，driverImg2，driverImg3等
    private JsonMessage saveDriverImg(HttpServletRequest request, String driverImg) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile(driverImg);
        String modulename = String.valueOf(multipartRequest.getParameter("modulename"));
        String path = "D:\\" + modulename;
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

        return new JsonMessage(true, modulename + "上传成功");
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

    public static void main(String[] args) {
        File file = new File("D:\\桌面备份\\备份文件\\要移动的文件\\redis连接信息.json");
        InputStream is = null;
        try {
            is = new FileInputStream(file);


            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[128];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name="我";
        byte[] bytes = name.getBytes();
        System.out.println(bytes.length);
        Integer integer=new Integer(21);

    }
}
