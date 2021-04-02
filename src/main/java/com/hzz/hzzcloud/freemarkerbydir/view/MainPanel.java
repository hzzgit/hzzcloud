package com.hzz.hzzcloud.freemarkerbydir.view;

import com.hzz.hzzcloud.freemarkerbydir.listener.AutoCreateViewListener;
import lombok.Data;

import javax.swing.*;
import java.awt.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/24 14:10
 */
@Data
public class MainPanel extends JPanel {

    // 开始生成的按钮
    private JButton btnone = new JButton("生成代码");


    private JTextField database = new JTextField("subiaodb");
    private JTextField tablename = new JTextField("autovoiceconfig");

    private JLabel databaseLabel=new JLabel("数据库:");
    private JLabel tablenameLabel=new JLabel("表名:");

    private JLabel depLabel=new JLabel("机构权限:");
    private JLabel depveLabel=new JLabel("机构车辆权限:");

    private JRadioButton dep=new JRadioButton();
    private JRadioButton depve=new JRadioButton();

    private AutoCreateViewListener autoCreateViewListener=new AutoCreateViewListener(this);

    public MainPanel(){
        this.setLayout(null );
        btnone.setBounds(100, 300, 100, 40);
        btnone.setActionCommand("begin");
        btnone.addActionListener(autoCreateViewListener);
        this.add(btnone);

        databaseLabel.setBounds(80, 100, 80, 20);
        this.add(databaseLabel);

        database.setBounds(180, 100, 150, 20);
        database.setFont(new Font("宋体", Font.PLAIN, 15));
        this.add(database);


        tablenameLabel.setBounds(80, 200, 80, 20);
        this.add(tablenameLabel);

        tablename.setBounds(180, 200, 150, 20);
        tablename.setFont(new Font("宋体", Font.PLAIN, 15));
        this.add(tablename);

        depLabel.setBounds(50, 240, 80, 20);
        this.add(depLabel);
        dep.setBounds(180, 240, 150, 20);
        this.add(dep);

        depveLabel.setBounds(50, 280, 80, 20);
        this.add(depveLabel);
        depve.setBounds(180, 280, 150, 20);

        this.add(depve);
    }
}
