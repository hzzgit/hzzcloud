package com.hzz.hzzcloud.工具.快速读取resources中的文件;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/11 11:33
 */
@Slf4j
public class FileLoaderUtil {

    /**
     * 获取到静态资源的输入
     *
     * @param id
     * @return
     */
    public static InputStream getFile(String id) {
        byte[] file = getFilebyte(id);
        byte[] result = Arrays.copyOf(file, file.length);
        return new ByteArrayInputStream(result);
    }

    /**
     * 获取到静态资源的路径
     *
     * @param url
     * @return
     */
    public static String getFilePath(String url) throws FileNotFoundException {
        String filepath = url;
        InputStream fileis = null;
        try {
            fileis = new FileInputStream(url);
        } catch (FileNotFoundException var12) {
            fileis = ClassLoader.getSystemResourceAsStream(url);
            if (fileis == null) {
                String path = getWebRootPath(url);
                fileis = new FileInputStream(path);
                filepath = path;
            } else {
                URL systemResource = ClassLoader.getSystemResource(url);
                String path = systemResource.getPath();
                filepath = path;
            }
        }
        return filepath;
    }


    public static byte[] getFilebyte(String url) {
        InputStream fileis = null;
        ByteArrayOutputStream baos = null;

        try {
            try {
                fileis = new FileInputStream(url);
            } catch (FileNotFoundException var12) {
                fileis = ClassLoader.getSystemResourceAsStream(url);
                if (fileis == null) {
                    String path = getWebRootPath(url);
                    fileis = new FileInputStream(path);
                }
            }

            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len;
            while ((len = ((InputStream) fileis).read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }

            baos.flush();
            byte[] var6 = baos.toByteArray();
            return var6;
        } catch (FileNotFoundException var13) {
            log.error(var13.getMessage(), var13);
        } catch (IOException var14) {
            log.error(var14.getMessage(), var14);
        } finally {
            closeQuietly((Closeable) fileis);
            closeQuietly(baos);
        }

        log.error(fileis + "这个路径文件没有找到,请查询");
        return null;
    }

    private static void closeQuietly(final Closeable closeable) {
        // no need to log a NullPointerException here
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception exc) {
            log.error("Unable to close resource: " + exc,
                    exc);
        }
    }

    private static String getWebRootPath(String filePath) {
        try {
            String path = FileLoaderUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("WEB-INF/classes/", "");
            path = path.replace("file:/", "");
            return path + filePath;
        } catch (URISyntaxException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(1);
    }
}
