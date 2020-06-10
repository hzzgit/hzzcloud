package com.hzz.hzzcloud.test;


import com.ltmonitor.jt808.protocol.IMessageBody;
import com.ltmonitor.jt808.protocol.MyBuffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JT_8607 implements IMessageBody {
    private byte routesCount;
    private ArrayList<Integer> routeIDs;

    public JT_8607() {
    }

    public final byte getRoutesCount() {
        return this.routesCount;
    }

    public final void setRoutesCount(byte value) {
        this.routesCount = value;
    }

    public final ArrayList<Integer> getRouteIDs() {
        return this.routeIDs;
    }

    public final void setRouteIDs(ArrayList<Integer> value) {
        this.routeIDs = value;
    }

    public int getMsgType() {
        return 34311;
    }

    public final byte[] WriteToBytes(int version) {
        MyBuffer bytes = new MyBuffer();
        bytes.put(this.getRoutesCount());
        if (this.getRoutesCount() > 0 && this.getRouteIDs() != null && this.getRouteIDs().size() > 0) {
            Iterator var3 = this.getRouteIDs().iterator();

            while(var3.hasNext()) {
                int areaId = (Integer)var3.next();
                bytes.put((byte)(areaId >> 24));
                bytes.put((byte)(areaId >> 16));
                bytes.put((byte)(areaId >> 8));
                bytes.put((byte)areaId);
            }
        }

        return bytes.array();
    }


    public final byte[] WriteToBytes2(int version) {
        MyBuffer bytes = new MyBuffer();
        bytes.put(this.getRoutesCount());
        if (this.getRoutesCount() > 0 && this.getRouteIDs() != null && this.getRouteIDs().size() > 0) {
            Iterator var3 = this.getRouteIDs().iterator();

            while(var3.hasNext()) {
                int areaId = (Integer)var3.next();
                bytes.put(areaId);
            }
        }

        return bytes.array();
    }
    public final void ReadFromBytes(int version, byte[] bytes) {
        this.setRoutesCount(bytes[0]);
        this.setRouteIDs(new ArrayList(this.getRoutesCount()));

        for(int pos = 1; pos < bytes.length; pos += 4) {
            int areaId = (bytes[pos] << 24) + (bytes[pos + 1] << 16) + bytes[pos + 2] << 8 + bytes[pos + 3];
            this.getRouteIDs().add(areaId);
        }

    }

    public static void main(String[] args) {
        JT_8607 jt_8607=new JT_8607();
        jt_8607.setRoutesCount((byte) 1);
        List<Integer> routeIdS=  new ArrayList<>();
        routeIdS.add(22222);
        jt_8607.setRouteIDs((ArrayList<Integer>) routeIdS);
        byte[] bytes = jt_8607.WriteToBytes(1);
        byte[] bytes2 = jt_8607.WriteToBytes2(1);
        System.out.println(bytes2==bytes);

        long a=12331313;
        byte b= (byte) a;
        System.out.println(b);
        MyBuffer bytes22= new MyBuffer();
        bytes22.put(a);
        System.out.println(bytes22.array());
    }
}
