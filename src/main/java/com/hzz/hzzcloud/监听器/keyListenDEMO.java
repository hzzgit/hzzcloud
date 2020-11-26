package com.hzz.hzzcloud.监听器;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyListenDEMO implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("键盘类型");
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("键盘按下");
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("不知道是什么");
    }
}
