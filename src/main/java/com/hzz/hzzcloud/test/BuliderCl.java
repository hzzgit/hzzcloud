package com.hzz.hzzcloud.test;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Builder
@Data
public class BuliderCl {
    private Integer co;
    private String name;
    private Long age;

    public static void main(String[] args) {
        BuliderCl test = BuliderCl.builder().age(new Long(1)).co(21).name("TEST").build();
        BuliderCl te1=BuliderCl.builder().build();
        BuliderCl t2=new BuliderCl(1,"ce",new Long(1));
        System.out.println(1);
        BigInteger a=new BigInteger("11111111111111111111111111111111111111111111111111111111111111111111111111" +
                "231111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    }
}
