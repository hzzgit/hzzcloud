package com.hzz.hzzcloud.freemarkerbydir.dto;

import lombok.Data;

/**
 * @author ：hzz
 * @description：用于记录所有要复制模板的文件
 * @date ：2020/11/26 14:45
 */
@Data
public class PathVoByDir {
    private String tempName;
    private String tempPath;
    private String classPath;
}
