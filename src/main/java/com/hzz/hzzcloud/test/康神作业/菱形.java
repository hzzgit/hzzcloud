package com.hzz.hzzcloud.test.康神作业;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 11:36
 */
public class 菱形 {
    public static void main(String[] args) {
        菱形 a = new 菱形();
        a.菱形(7,true);
        a.菱形(7,false);
        a.正三(7);
        a.倒三角(7);
        a.正直角(7,true);
        a.正直角(7,false);
        a.直角(7,true);
        a.直角(7,false);
    }

    /**
     * @param allco
     */
    public void 菱形(int allco,boolean issame) {
        String data = "";
        for (int i = -allco; i <= allco; i++) {
            int co = Math.abs(i);
            String xing="●";
            if(!issame){
                if(i%2==0){
                    xing = "●";
                }else{
                    xing = "*";
                }
            }

            int co1 = 0;
            for (int j = 0; j < allco; j++) {
                if (j < co) {
                    data += " ";
                } else {
                    data +=xing;
                    co1 += 1;
                }
            }
            for (int j = 1; j < co1; j++) {
                data += xing;
            }

            data += "\n";
        }
        System.out.println(data);
    }

    public void 正三(int allco) {
        String data = "";
        for (int i = allco; i >= 0; i--) {
            int co = Math.abs(i);

            int co1 = 0;
            for (int j = 0; j < allco; j++) {
                if (j < co) {
                    data += " ";
                } else {
                    data += "●";
                    co1 += 1;
                }
            }
            for (int j = 1; j < co1; j++) {
                data += "●";
            }

            data += "\n";
        }
        System.out.println(data);
    }

    public void 直角(int allco,boolean issame) {
        String data = "";
        for (int i = allco; i >= 0; i--) {
            int co = Math.abs(i);
            String xing="●";
            if(!issame){
                if(i%2==0){
                    xing = "●";
                }else{
                    xing = "*";
                }
            }
            int co1 = 0;
            for (int j = 0; j < allco; j++) {
                if (j < co) {
                        data += xing;
                } else {
                    data += "";
                    co1 += 1;
                }
            }
//            for (int j = 1; j < co1; j++) {
//                data += "●";
//            }

            data += "\n";
        }
        System.out.println(data);
    }


    public void 正直角(int allco,boolean issame) {
        String data = "";
        for (int i = 0; i <= allco; i++) {
            int co = Math.abs(i);
            String xing="●";
            if(!issame){
                if(i%2==0){
                    xing = "●";
                }else{
                    xing = "*";
                }
            }
            int co1 = 0;
            for (int j = 0; j < allco; j++) {
                if (j < co) {
                    data += xing;
                } else {
                    data += "";
                    co1 += 1;
                }
            }
//            for (int j = 1; j < co1; j++) {
//                data += "●";
//            }

            data += "\n";
        }
        System.out.println(data);
    }

    public void 倒三角(int allco) {
        String data = "";
        for (int i = 0; i <= allco; i++) {
            int co = Math.abs(i);
            int co1 = 0;
            for (int j = 0; j < allco; j++) {
                if (j < co) {
                    data += " ";
                } else {
                    data += "●";
                    co1 += 1;
                }
            }
            for (int j = 1; j < co1; j++) {
                data += "●";
            }

            data += "\n";
        }
        System.out.println(data);
    }
}
