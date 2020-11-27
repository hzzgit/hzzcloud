//package com.hzz.hzzcloud.util;
//
//import java.io.ByteArrayInputStream;
//import java.io.File;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.druid.util.StringUtils;
//import com.aliyun.oss.ClientException;
//import com.aliyun.oss.OSSClient;
//import com.aliyun.oss.OSSException;
//import com.aliyun.oss.model.GetObjectRequest;
//import com.asphalt.common.LoggerProxy;
//
///**
// * 文件上传工具类
// *
// * @author issuser
// * @date   2016-10-22
// */
//@Component
//public class FileOssUploadUtils {
//
//    private static Logger logger= LoggerFactory.getLogger(FileOssUploadUtils.class);
//
///*	private static String ossUrl = PropertiesUtil.GetValueByKey("application.properties", "asphalt.oss_url");
//	private static String bucketName = PropertiesUtil.GetValueByKey("application.properties", "asphalt.bucket_name");
//	private static String accessKeyId = PropertiesUtil.GetValueByKey("application.properties", "asphalt.access_key_id");
//	private static String accessKeySecret = PropertiesUtil.GetValueByKey("application.properties", "asphalt.access_key_secret");*/
//
//    @Value("${asphalt.oss_url}")
//    private String ossUrl;
//    @Value("${asphalt.bucket_name}")
//    private String bucketName;
//    @Value("${asphalt.access_key_id}")
//    private String accessKeyId;
//    @Value("${asphalt.access_key_secret}")
//    private String accessKeySecret;
//
//
//    //Constructs a client instance with your account for accessing OSS
//    private static OSSClient client = null;
//    public OSSClient getInstance()
//    {
//        if(null == client)
//        {
//            client = new OSSClient(ossUrl, accessKeyId, accessKeySecret);
//        }
//        return client;
//    }
//
//    /**
//     * 文件上传
//     * @param uploadFile
//     * @return
//     */
//    public boolean upload(File uploadFile) {
//        try {
//            if(uploadFile == null){
//                return false;
//            }
//
//            getInstance().putObject(bucketName, uploadFile.getName(), uploadFile);
//            logger.info("FileUploadUtils#upload success: bucketName="+bucketName+", filename="+uploadFile.getName());
//            return true;
//        } catch (OSSException oe) {
//            logger.error("OSSException", oe);
//        } catch (ClientException ce) {
//            logger.error("ClientException", ce);
//        }
//        return false;
//    }
//
//
//    /**
//     * 文件上传
//     * @param parentFolder 要上传的目录
//     * @param filename	   文件名称
//     * @param uploadFile
//     * @return
//     */
//    public boolean upload(String parentFolder, String filename, File uploadFile) {
//        try {
//            if(uploadFile == null || StringUtils.isEmpty(filename)){
//                return false;
//            }
//
//            getInstance().putObject(bucketName, parentFolder + "/" +filename, uploadFile);
//
//            logger.info("FileUploadUtils#upload success: bucketName="+bucketName+", parentFolder="+ parentFolder +", filename=" + filename);
//
//            return true;
//        } catch (OSSException oe) {
//            logger.error("OSSException" ,oe);
//        } catch (ClientException ce) {
//            logger.error("ClientException" ,ce);
//        }
//        return false;
//    }
//
//    /**
//     * 下載文件(oss文件下載)
//     * @param file	下載文件的名字(文件名称或文件路径+名称)
//     * @param path	下載文件的存放地址
//     * @return
//     */
//    public boolean download(String filename, String path){
//        boolean flag = false;
//        try{
//            getInstance().getObject(new GetObjectRequest(bucketName, filename), new File(path));
//        }catch(Exception e){
//            logger.error("FileUploadUtils#downloadFile occur error: filename="+filename+", path="+path, e);
//            return flag;
//        }
//
//        logger.info("FileUploadUtils#downloadFile success: filename="+filename+", path="+path);
//        return true;
//    }
//
//
//    /**
//     * 创建文件夹
//     * @param folderName
//     * @return
//     */
//    public boolean createFolder(String folderName){
//
//        try {
//            if(StringUtils.isEmpty(folderName)){
//                return false;
//            }
//
//            if(!folderName.endsWith("/")){
//                folderName = folderName + "/";
//            }
//
//            //Create an empty folder without request body, note that the key must be suffixed with a slash
//            getInstance().putObject(bucketName, folderName, new ByteArrayInputStream(new byte[0]));
//            logger.info("FileUploadUtils#createFolder success: folder name is " + folderName);
//
//            return true;
//        } catch (OSSException oe) {
//            logger.error("OSSException" ,oe);
//        } catch (ClientException ce) {
//            logger.error("ClientException" ,ce);
//        }
//        return false;
//    }
//
//
//    /**
//     * 删除文件
//     *
//     * @param filename   文件名字(全名-路径+文件名)
//     * @return
//     */
//    public boolean deleteFile(String filename){
//
//        try {
//            if(StringUtils.isEmpty(filename)){
//                return false;
//            }
//            //OSSObject object = client.getObject(bucketName, filename);
//
//            //client.deleteObject(bucketName, object.getKey());
//            getInstance().deleteObject(bucketName, filename);
//
//            return true;
//        } catch (OSSException oe) {
//            logger.error("OSSException" ,oe);
//        } catch (ClientException ce) {
//            logger.error("ClientException" ,ce);
//        }
//        return false;
//    }
//
//
//    /**
//     * 删除文件
//     *
//     * @param filename   文件夹名称
//     * @return
//     */
//    public boolean deleteFolder(String foldername){
//
//        try {
//            if(StringUtils.isEmpty(foldername)){
//                return false;
//            }
//
//            if(!foldername.endsWith("/")){
//                foldername = foldername + "/";
//            }
//            //OSSObject object = client.getObject(bucketName, filename);
//
//            //client.deleteObject(bucketName, object.getKey());
//            getInstance().deleteObject(bucketName, foldername);
//
//            return true;
//        } catch (OSSException oe) {
//            logger.error("OSSException" ,oe);
//        } catch (ClientException ce) {
//            logger.error("ClientException" ,ce);
//        }
//        return false;
//    }
//
//    /**
//     * 打印上
//     * @param uploadId
//     */
//	/*private static void listAllParts(String uploadId) {
//	    logger.info("Listing all parts......");
//	    ListPartsRequest listPartsRequest = new ListPartsRequest(bucketName, fileName, uploadId);
//	    PartListing partListing = client.listParts(listPartsRequest);
//
//	    int partCount = partListing.getParts().size();
//	    for (int i = 0; i < partCount; i++) {
//	        PartSummary partSummary = partListing.getParts().get(i);
//	        logger.info("\tPart#" + partSummary.getPartNumber() + ", ETag=" + partSummary.getETag());
//	    }
//	}*/
//}