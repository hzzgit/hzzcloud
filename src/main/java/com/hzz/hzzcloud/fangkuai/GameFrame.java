package com.hzz.hzzcloud.fangkuai;

import com.hzz.hzzcloud.fangkuai.GamePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GameFrame extends JFrame implements ActionListener{
    private int widthFrame = 500;
    private int heightFrame = 600;
    private JMenu menuone = new JMenu("游戏");//创建一个菜单
    private JMenuItem newGame = menuone.add("重新开始");//创建一个内置菜单选项
    private JMenuItem exitGame = menuone.add("游戏退出");
    private JMenuItem stopGame = menuone.add("游戏暂停");
    private JMenuItem goOnGame = menuone.add("游戏继续");

    private JMenu menutwo = new JMenu("帮助");//创建第二个菜单
    private JMenuItem aboutGame = menutwo.add("关于游戏");
    GamePanel gamepanel = new GamePanel();

    public GameFrame()//构造函数
    {
        addKeyListener(gamepanel);
        newGame.addActionListener(this);
        exitGame.addActionListener(this);
        stopGame.addActionListener(this);
        goOnGame.addActionListener(this);
        aboutGame.addActionListener(this);

        this.add(gamepanel);

        JMenuBar menu = new JMenuBar();
        menu.add(menuone);
        menu.add(menutwo);
        this.setJMenuBar(menu);

        this.setTitle("俄罗斯方块");
        this.setBounds(50, 10, widthFrame, heightFrame);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == newGame)//游戏重新开始
        {
            gamepanel.NewGame();
        }
        if(e.getSource() == exitGame)//游戏退出
        {
            System.exit(0);
        }
        if(e.getSource() == stopGame)//游戏暂停
        {
            gamepanel.StopGame();
        }
        if(e.getSource() == goOnGame)//游戏继续
        {
            gamepanel.ContinueGame();
        }
        if(e.getSource() == aboutGame)//关于游戏信息
        {
            JOptionPane.showMessageDialog(null, "左右键移动，向上建旋转", "提示", JOptionPane.OK_OPTION);
        }
    }


    public static void main(String[] args) {
        new GameFrame();
    }
}