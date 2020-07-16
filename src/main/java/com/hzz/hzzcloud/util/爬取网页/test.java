package com.hzz.hzzcloud.util.爬取网页;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;


public class test {

    /**
     *
     * @param url 访问路径
     * @return
     */
    public  Document getDocument (String url){
        try {
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {

        test t = new test();
        Document doc = t.getDocument("https://blog.csdn.net/sinat_35626559/article/details/78285007");
        Elements span = doc.select("span");
        Iterator<Element> iterator = span.iterator();
        while (iterator.hasNext()){

            Element next = iterator.next();
            String text = next.text();
            if(text.indexOf("7月")>-1){
                System.out.println(text);
            }
        }


    }
}