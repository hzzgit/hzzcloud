package com.hzz.hzzcloud.freemarkerbydir.view;

import javax.swing.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/24 14:08
 */
public class AutoCreateView extends JFrame {

    private MainPanel mainPanel=new MainPanel();

    public AutoCreateView (){
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // 设置上下面板及起始位置及大小
        mainPanel.setBounds(0, 0, 400, 400);
        // 添加面板
        this.add(mainPanel);
        // 窗口居中
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        AutoCreateView autoCreateView = new AutoCreateView();
    }
}
