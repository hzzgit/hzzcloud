package com.hzz.hzzcloud.controller;

import com.alibaba.fastjson.JSON;
import com.hzz.hzzcloud.aop.UserAccess;
import com.hzz.hzzcloud.controller.vo.PageVo;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;
import com.hzz.hzzjdbc.jdbcutil.vo.PaginateResult;
import com.hzz.hzzjdbc.submeter.util.JdbcSearchSqlUtil;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import net.fxft.ascswebcommon.web.util.MaptoBeanUtil;
import net.fxft.common.util.MapUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/test")

public class VehicleAction extends GeneAction {

    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    private MysqlDao mysqlDao;

    public static void main(String[] args) {
        Number altitude;
        short a = 1;
        altitude = a;
        System.out.println(altitude);
    }

    //    @PostConstruct
    private void init() {
        new Thread(() -> {
            while (true) {
                try {
                    String name = applicationContext.getEnvironment().getRequiredProperty("name");
                    System.out.println("动态刷新值：" + name);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 我就是测试下
     */
    @Value("${config.jwtSecret:}")
    private String jwtSecret;

    @Autowired
    protected HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/abc.action")
    @UserAccess(desc = "测试")
    public JsonMessage abc() {
        String userName = request.getHeader("userName") + "5" + jwtSecret;
        return new JsonMessage(true, userName);
    }

    @ResponseBody
    @RequestMapping("/list.action")
    public PaginateResult list(
           @Validated @RequestBody PageVo pageVo
    ) {
        Map<String, Object> stringObjectMap = MaptoBeanUtil.objToMap(pageVo);
        String sql = "select * from vehicle where 1=1";
        JdbcSearchSqlUtil jdbcSearchSqlUtil=new JdbcSearchSqlUtil(sql,stringObjectMap);
        jdbcSearchSqlUtil.append(" and plateNo like ? ","plateno","%","%");
        jdbcSearchSqlUtil.append(" and simNo like ? ","simno","%","%");
        PaginateResult paginateResult = mysqlDao.queryPage(jdbcSearchSqlUtil.getSql(),pageVo.getPage(),pageVo.getPagesize(),
                jdbcSearchSqlUtil.getSearchParams().toArray());
        return paginateResult;
    }


    /**
     * 下载视频文件
     */
    @RequestMapping("/downvideo.action")
    public void downvideo(@RequestParam(required = true) String downname, HttpServletResponse response) throws FileNotFoundException {

        String downpath = downname;
        File file = new File(downpath);
        if (file.exists()) {
            ServletOutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int ken = 0;
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition",
                        "attachment;filename=" + downname);

                while (ken > -1) {
                    ken = fileInputStream.read(bytes);
                    outputStream.write(bytes);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException("文件" + downname + "未找到");
        }
    }


}
