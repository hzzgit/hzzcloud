package com.hzz.hzzcloud.test.读取亿万数据文件并且排序.一行一行读取排序;

import com.hzz.hzzcloud.test.读取亿万数据文件并且排序.KeyWordsCount;
import com.hzz.hzzcloud.test.读取亿万数据文件并且排序.SoutDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/16 15:18
 */
public class ReadFile {
    public static void main(String[] args) throws IOException {
        URL systemResource = ClassLoader.getSystemResource("sort.txt");
        String path = systemResource.getPath();
        Resource resource = new ClassPathResource("sort.txt");
        File file=resource.getFile();

        int THREADCO=50;
        ExecutorService executorService = Executors.newFixedThreadPool(THREADCO);

        int coThread=0;
        ReadThread[] readThreads=new ReadThread[THREADCO];
        for (int i = 0; i <THREADCO ; i++) {
            ReadThread readThread=new ReadThread(100);
            executorService.submit(readThread);
            readThreads[i]=readThread;
        }


        KeyWordsCount countObject = KeyWordsCount.getCountObject();

        try {
            InputStreamReader inputStream=new InputStreamReader(new FileInputStream(file));
            BufferedReader br=new BufferedReader(inputStream);
            String line = null;
            int inte=0;
            while ((line = br.readLine()) != null) {
                inte+=1;
                System.out.println("读取了"+inte+"行");

                SoutDto soutDto = SortList.get(line);

                if(soutDto!=null){
                    if(soutDto.getSortcol()==3){
                        countObject.addCount(1);
                    }
                    int i = coThread % THREADCO;
                    readThreads[i].addqueue(soutDto);
                    coThread=coThread+1;
                }

            }
            for (ReadThread readThread : readThreads) {
                readThread.isread=false;
            }
            br.close();

            try {
                executorService.shutdown();
                executorService.awaitTermination(10000, TimeUnit.SECONDS);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<SoutDto> list=new ArrayList<>();
            for (ReadThread readThread : readThreads) {
                list.addAll(readThread.list);

            }
            Collections.sort(list, new Comparator<SoutDto>() {
                @Override
                public int compare(SoutDto o1, SoutDto o2) {
                    return o1.getSortcol()-o2.getSortcol();
                }
            });

            System.out.println(list.size());

            System.out.println("查询到关键字的次数:"+KeyWordsCount.getCountObject().getCount());

            System.out.println(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
// 1,3,4,2,9,6,5,7