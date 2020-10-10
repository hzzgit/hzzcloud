package com.hzz.hzzcloud.test.康神作业.socket文件传输;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：hzz
 * @description：服务器
 * @date ：2020/9/29 10:09
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
             server = new ServerSocket(9999);
            System.out.println("开始运行服务端监听");
            while (true) {

                Socket sc = server.accept();

                DataInputStream din = new DataInputStream(sc.getInputStream());

                DataOutputStream dout = new DataOutputStream(sc.getOutputStream());

                System.out.println("客户端ip地址是：" + sc.getInetAddress());

                System.out.println("客户端端口号是：" + sc.getPort());

                System.out.println("本地端口号是：" + sc.getLocalPort());

                System.out.println("客户端消息是：" + din.readUTF());

                dout.writeUTF("已收到你发来的消息!!");

                din.close();//关闭输入流

                dout.close(); //关闭输出流

                sc.close();//关闭当前发送来的客户端和服务器的连接



            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                server.close();//可以注释掉,关闭服务器
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
