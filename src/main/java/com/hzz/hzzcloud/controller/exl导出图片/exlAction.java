package com.hzz.hzzcloud.controller.exl导出图片;

import com.hzz.hzzcloud.controller.exl导出图片.vo.Student;
import net.fxft.ascswebcommon.util.easyexcel.EasyExceClasslUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/3/3 17:00
 */
@Controller
@RequestMapping("exl")
public class exlAction {
    @Autowired
    private EasyExceClasslUtil easyExceClasslUtil;

    @RequestMapping("export")
    public void exlexport(HttpServletRequest request, HttpServletResponse response){
        List<Student> studentList=new ArrayList<>();
        Student student = new Student();
        student.setLatitude("122");
        student.setImage1("C:\\Users\\fxft\\Desktop\\222.png");
        for (int i = 0; i < 1000; i++) {
            studentList.add(student);
        }

        easyExceClasslUtil.exlport("TE",request,response,studentList,Student.class);
    }
}
