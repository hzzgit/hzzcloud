package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 11:36
 */
public class 菱形 {
    public static void main(String[] args) {
        菱形 a = new 菱形();
        a.菱形(15);
        a.正三(15);
        a.倒三角(15);
    }

    /**
     *
     * @param allco
     */
    public void 菱形(int allco ){
           String data="";
        for (int i = -allco; i <= allco; i++) {
            int co=Math.abs(i);

            int co1=0;
            for (int j = 0; j <allco ; j++) {
                if(j<co){
                    data+=" ";
                }else{
                    data+="●";
                    co1+=1;
                }
            }
            for (int j = 1; j <co1 ; j++) {
                data+="●";
            }

            data+="\n";
        }
        System.out.println(data);
    }

    public void 正三(  int allco){
         String data="";
        for (int i = allco; i>= 0; i--) {
            int co=Math.abs(i);

            int co1=0;
            for (int j = 0; j <allco ; j++) {
                if(j<co){
                    data+=" ";
                }else{
                    data+="●";
                    co1+=1;
                }
            }
            for (int j = 1; j <co1 ; j++) {
                data+="●";
            }

            data+="\n";
        }
        System.out.println(data);
    }

    public void 倒三角(int allco){
           String data="";
        for (int i = 0; i <= allco; i++) {
            int co=Math.abs(i);
            int co1=0;
            for (int j = 0; j <allco ; j++) {
                if(j<co){
                    data+=" ";
                }else{
                    data+="●";
                    co1+=1;
                }
            }
            for (int j = 1; j <co1 ; j++) {
                data+="●";
            }

            data+="\n";
        }
        System.out.println(data);
    }
}
