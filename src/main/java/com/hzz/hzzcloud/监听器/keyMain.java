package com.hzz.hzzcloud.监听器;

import javax.swing.*;
import java.awt.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/23 14:03
 */
public class keyMain  extends JFrame {
    public keyMain() throws HeadlessException {
        keyListenDEMO k =new keyListenDEMO();
        this.addKeyListener(k);

    }

    public static void main(String[] args) {

       keyMain keyMain=new keyMain();

        System.out.println(1);

    }
}
