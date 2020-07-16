package com.hzz.hzzcloud.util.线程池.分段锁;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/15 14:54
 */
public class Test {

    public static void main(String[] args) {
        Node list[] = new Node[16];
        Node node1 = new Node(1, 1, 2, null);
        Node node2 = new Node(2, 2, 3, null);
        Node node3 = new Node(2, 3, 3, null);
        node2.next = node3;
        Node node4 = new Node(2, 4, 3, node3);
        node3.next = node4;

        list[0] = node1;
        list[1] = node4;

        new Thread(() -> {
            Node node = list[0];
            synchronized (node) {
                System.out.println("读取第0位元素，并加锁");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            Node node = list[0];
            synchronized (node) {
                System.out.println("读取第0位元素，并加锁");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            Node node = list[1];
            synchronized (node) {
                System.out.println("读取第1位元素，并加锁,key为" + node.key);
                while (node.next != null) {
                    node = node.next;
                    System.out.println("下一位的key为:" + node.key);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
