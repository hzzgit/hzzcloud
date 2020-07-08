package com.hzz.hzzcloud.controller;


import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/FileAction")
public class 将其作为各模块的libs和包及配置文件的文件服务器Action {

    @Autowired
    private ImageSaveService imageSaveService;


    @ResponseBody
    @RequestMapping("/upload.action")
    public JsonMessage upload(HttpServletRequest request) {
        JsonMessage jsonMessage = imageSaveService.saveDriverimg(request);
        return jsonMessage;
    }

    @ResponseBody
    @RequestMapping("/searchmodulelist.action")
    public JsonMessage searchmodulelist(@RequestParam(name = "modulename") String modulename,HttpServletRequest request) {
        String path="D:"+File.separator+modulename;
        List<String> strings = scanFilesWithRecursion(path, null);
        return new JsonMessage(true,strings);
    }
    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     *
     * @return ArrayList<Object>
     * @throws FileNotFoundException
     */
    public  List<String> scanFilesWithRecursion(String folderPath, String searchfiletype) {

        try {
            List<String> scanFiles = new ArrayList();
            File directory = new File(folderPath);
            if (!directory.isDirectory()) {
                throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
            }
            if (directory.isDirectory()) {
                File[] filelist = directory.listFiles();
                for (int i = 0; i < filelist.length; i++) {
                    /**如果当前是文件夹，进入递归扫描文件夹**/
                    if (filelist[i].isDirectory()) {
                        /**递归扫描下面的文件夹**/

                        scanFiles.addAll(scanFilesWithRecursion(filelist[i].getAbsolutePath(),searchfiletype));
                    }
                    /**非文件夹**/
                    else {
                        String filename=filelist[i].getName();
                        String filetype = filename.substring(filename.lastIndexOf("."));
                        if(searchfiletype==null){
                            scanFiles.add(filelist[i].getAbsolutePath());
                        }else{
                            if(("."+searchfiletype).equalsIgnoreCase(filetype)){
                                scanFiles.add(filelist[i].getAbsolutePath());
                            }
                        }

                    }
                }
            }
            return scanFiles;
        } catch (FileNotFoundException e) {
            return null;
        }
    }


    @RequestMapping(value = "/readImageFile1", method = RequestMethod.POST)
    @ResponseBody
    public void getUrlFile(
            @RequestParam(name = "filename") String filename,
            HttpServletRequest request, HttpServletResponse response) {
        String serverUrl = filename;
        File file = new File(serverUrl);
        // 后缀名
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            int length = inputStream.read(data);
            inputStream.close();
            //setContentType("text/plain; charset=utf-8"); 文本
            response.setContentType("text.jar;charset=utf-8");
            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
