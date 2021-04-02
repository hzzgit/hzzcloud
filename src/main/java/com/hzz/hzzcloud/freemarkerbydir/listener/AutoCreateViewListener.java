package com.hzz.hzzcloud.freemarkerbydir.listener;

import com.hzz.hzzcloud.freemarkerbydir.FreeMarkbydirExcuter;
import com.hzz.hzzcloud.freemarkerbydir.view.MainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/24 14:19
 */
public class AutoCreateViewListener implements ActionListener {
    FreeMarkbydirExcuter freeMarkbydirExcuter=new FreeMarkbydirExcuter();
    private MainPanel mainPanel;

    public AutoCreateViewListener(MainPanel mainPanel){
        this.mainPanel=mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("begin")) {
            System.out.println("开始生成");
            String database = mainPanel.getDatabase().getText();
            String tablename = mainPanel.getTablename().getText();

            boolean depselected = mainPanel.getDep().isSelected();
            boolean depVeselected = mainPanel.getDepve().isSelected();
            freeMarkbydirExcuter.readTable(database, tablename,
                    depVeselected,depselected);
        }
    }
}
