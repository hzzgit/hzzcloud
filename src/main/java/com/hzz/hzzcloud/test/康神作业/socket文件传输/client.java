package com.hzz.hzzcloud.test.康神作业.socket文件传输;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author ：hzz
 * @description：客户端
 * @date ：2020/9/29 10:10
 */
public class client {
    public static void main(String[] args) {

        try {
            Socket sc = new Socket(InetAddress.getLocalHost(), 9999);//客户端连接服务端

            DataInputStream din = new DataInputStream(sc.getInputStream());

            DataOutputStream dout = new DataOutputStream(sc.getOutputStream());

            dout.writeUTF("hao");//发送内容

            System.out.println(din.readUTF());

            din.close();

            dout.close();

            sc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

