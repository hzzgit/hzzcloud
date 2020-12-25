package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.ltmonitor.util.StringUtil;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Data
public class GpsMessage {
    private static final Logger logger = LoggerFactory.getLogger(GpsMessage.class);

    /**
     * 区域代码，车辆所属区域代码，例如:福州是3501
     * http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2018/35.html
     * 参考国统局的，把后面的0去掉就是了
     */
    private int code;
    /**
     * 车牌号大小,车牌号(GB2312编码)
     */
    private String plateNo;
    /**
     * 时间,YY-MM-DD-hh-mm-ss（GMT+8时间，本标准中之后涉及的时间均采用此时区）
     */
    private Date time;
    /**
     * 经度,以度为单位的经度值乘以10的6次方，精确到百万 分之一度
     */

    private double longitude;
    /**
     * 纬度,以度为单位的纬度值乘以10的6次方，精确到百万分之一度
     */

    private double latitude;
    /**
     * 海拔高度，单位为米（m）
     */

    private int altitude;
    /**
     * 速度,1/10km/h
     */
    private short speed;
    /**
     * 方向,0～359，正北为0，顺时针
     */

    private short course;
    /**
     * 里程
     */
    private int mileage;
    /**
     * 状态
     * 0	0：ACC关
     * 1：ACC开
     * 1	0：未定位
     * 1：定位
     * 2	0：北纬
     * 1：南纬
     * 3	0：东经
     * 1：西经
     * 4	0：运营状态
     * 1：停运状态
     * 5	0：经纬度未经保密插件加密
     * 1：经纬度已经保密插件加密
     * 6~7	保留
     * 8~9	【Bit8 Bit9】00：空车、01：半载、10：保留、11：满载
     * 10	0：车辆油路正常
     * 1：车辆油路断开
     * 11	0：车辆电路正常
     * 1：车辆电路断开
     * 12	0：车门解锁
     * 1：车门加锁
     * 13	0：车门1解锁
     * 1：车门1加锁
     * 14	0：车门2解锁
     * 1：车门2加锁
     * 15	0：车门3解锁
     * 1：车门3加锁
     * 16	0：车门4解锁
     * 1：车门4加锁
     * 17	0：车门5解锁
     * 1：车门5加锁
     * 18	0：非GPS定位
     * 1：GPS定位
     * 19	0：非北斗定位
     * 1：北斗定位
     * 20	0：非俄罗斯GLONASS定位
     * 1：俄罗斯GLONASS定位
     * 21	0：非欧盟GALILEO定位
     * 1：欧盟GALILEO定位
     * 22	0：非基站定位
     * 1：基站定位
     * 23	0：非Wifi定位
     * 1：Wifi定位
     * 24~63	保留
     */
    private long status;
    /**
     * 0	1：紧急报警
     * 1	1：超速报警
     * 2	1：疲劳驾驶
     * 3	1：预警
     * 4	1：GNSS模块发生故障
     * 5	1：GNSS天线未接或被剪断
     * 6	1：GNSS天线短路
     * 7	1：终端主电源欠压
     * 8	1：终端主电源掉电
     * 9	1：终端LCD或显示器故障
     * 10	1：TTS模块故障
     * 11	1：摄像头故障
     * 12	1：道路运输证IC卡模块故障
     * 13	1：超速预警
     * 14	1：疲劳驾驶预警
     * 15	1：禁行报警
     * 16	保留
     * 17	保留
     * 18	1：当天累计驾驶超时
     * 19	1：超时停车
     * 20	1：进出区域
     * 21	1：进出路线
     * 22	1：路段行使时间不足/过长
     * 23	1：路线偏离报警
     * 24	1：车辆VSS故障
     * 25	1：车辆油量异常
     * 26	1：车辆被盗
     * 27	1：车辆非法点火
     * 28	1：车辆非法位移
     * 29	1：碰撞侧翻报警
     * 30	1：侧翻预警
     * 31~63	保留
     * 报警标志
     */
    private long alarmFlag;
    /**
     * 车辆类型
     * 所有车辆类型	0
     * 省际客运班车	11
     * 市际客运班车	12
     * 旅游客运车辆	13
     * 县际客车	14
     * 县内客车	15
     * 危险货物运输车辆	20
     * 重型载货汽车	31
     * 半挂牵引车	32
     * 普通货车	33
     * 出租汽车	41
     * 公交车	43
     * 警车	51
     * 船	53
     * 其他	99
     */
    private int type;

    private byte[] data;

    //创建时间，5月28号之后加入
    private Date createDate;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public short getCourse() {
        return course;
    }

    public void setCourse(short course) {
        this.course = course;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public long getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(long alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    //将数据转成字符串进行存储
    public String tojson() {
        String value = TimeUtils.timetotimeStamp(time) / 1000 + ","
                + longitude + "," + latitude + "," + altitude + ","
                + speed + "," + course + "," + mileage + "," + status + "," + alarmFlag + "," + type +","+TimeUtils.timetotimeStamp(new Date()) / 1000;
        return value;
    }

    //字符串转对象
    public void jsontodata(String value) {
        if (!StringUtil.isNullOrEmpty(value)) {
            String vals[] = value.split(",");
            Long timeval = ConverterUtils.toLong(vals[0]) * 1000;
            time = new Date(timeval);
            longitude = ConverterUtils.toDouble(vals[1]);
            latitude = ConverterUtils.toDouble(vals[2]);
            altitude = ConverterUtils.toInt(vals[3]);
            speed = Short.parseShort(vals[4]);
            course = Short.parseShort(vals[5]);
            mileage = ConverterUtils.toInt(vals[6]);
            status = ConverterUtils.toLong(vals[7]);
            alarmFlag = ConverterUtils.toLong(vals[8]);
            type = ConverterUtils.toInt(vals[9]);
            if(vals.length>10){
                Long createtimeval = ConverterUtils.toLong(vals[10]) * 1000;
                createDate = new Date(createtimeval);
            }
        }
    }

    /**
     * 读取字节流，解析出数据
     *
     * @param msg
     */
    public void readFromBytes(byte[] msg) {
        int pos = 0;
        data = new byte[msg.length];
        System.arraycopy(msg, 0, data, 0, msg.length);

        setCode(ByteUtil.byteToShort(msg, pos));
        pos += 2;
        int len = msg[pos] & 0xFF;
        pos += 1;
        String plateNo = "";
        try {
            plateNo = new String(msg, pos, len, "gbk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setPlateNo(plateNo);
        pos += len;
        short year = ByteUtil.byteToShort(msg, pos);
        pos += 2;
        int month = msg[pos];
        pos += 1;
        int day = msg[pos];
        pos += 1;
        int times = ByteUtil.byteToInt(msg, pos);
        pos += 4;
        setTime(getTime(year, month, day, times));
        setLatitude((double) ByteUtil.byteToInt(msg, pos) / 1000000);
        pos += 4;
        setLongitude((double) ByteUtil.byteToInt(msg, pos) / 1000000);
        pos += 4;
        setAltitude(ByteUtil.byteToInt(msg, pos));
        pos += 4;
        setSpeed(ByteUtil.byteToShort(msg, pos));
        pos += 2;
        setCourse(ByteUtil.byteToShort(msg, pos));
        pos += 2;
        setMileage(ByteUtil.byteToInt(msg, pos));
        pos += 4;
        setStatus(ByteUtil.byteToLong(msg, pos));
        pos += 8;
        setAlarmFlag(ByteUtil.byteToLong(msg, pos));
        pos += 8;
        setType(msg[pos]);
        pos += 1;
        byte ext = msg[pos];
        pos += 1;
        byte xor = GetCheckXor(msg, 3, len + 2);
       // logger.debug("[" + time.toString() + "] " + plateNo + ": " + latitude + "-" + longitude);
        //System.out.println(toString());
    }

    private Date getTime(int year, int month, int day, int time) {
        //System.out.println("year = [" + year + "], month = [" + month + "], day = [" + day + "], time = [" + time + "]");
        int hour = time / 1000 / 3600;
        int minute = (time / 1000 % 3600) / 60;
        int second = (time / 1000 % 3600) % 60;
        String d = String.format("%s-%02d-%02d %02d:%02d:%02d", year, month, day, hour, minute, second);
        return DateUtil.parse(d, DatePattern.NORM_DATETIME_FORMAT);
    }

    /**
     * 获取校验和
     *
     * @param data
     * @param pos
     * @param len
     * @return
     */
    private byte GetCheckXor(byte[] data, int pos, int len) {
        byte A = 0;
        for (int i = pos; i < len; i++) {
            A ^= data[i];
        }
        return A;
    }

    /**
     * 数据转成字节流
     *
     * @return
     */
    public byte[] WriteToBytes() {
        return null;
    }

    /**
     * 7E
     * 00 3B 长度=59
     * 0D AD 区域代码=3501
     * 0A 车牌号大小=10
     * C3 F6 41 43 38 39 32 35 BB C6 车牌号=闽AC8925黄
     * 07 E1 08 01 02 3B 03 B0 时间=2017-08-01 10:23:42
     * 07 15 C1 C2 经度=118.86637
     * 01 91 04 92 纬度=26.281106
     * 00 00 00 1E 高度=30
     * 00 00 速度=0
     * 00 3F 方向=63
     * 00 00 EB 06 里程=60166
     * 00 00 00 00 00 03 00 03状态=196611
     * 00 00 00 00 00 00 00 00报警=0
     * 63 车辆类型=99
     * 3B 校验
     *
     * @param args
     */
    public static void main(String[] args) {
//        byte[] test ={0x7e,0x00,0x3b,0x0d,(byte)0xad,0x0a,(byte)0xC3,(byte)0xF6 ,0x41 ,0x43 ,0x38 ,0x39 ,0x32 ,0x35 ,(byte)0xBB ,(byte)0xC6
//                ,0x07 ,(byte)0xE1 ,0x08 ,0x01 ,0x02 ,0x3B ,0x03 ,(byte)0xB0};
        //byte[] test =ByteUtil.hexStrToBytes("7E 00 3B 0D AD 0A C3 F6 41 43 38 39 32 35 BB C6 07 E1 08 01 02 3B 03 B0 07 15 C1 C2 01 91 04 92  00 00 00 1E 00 00 00 3F 00 00 EB 06 00 00 00 00 00 03 00 03 00 00 00 00 00 00 00 00 63 3B");
        byte[] test = ByteUtil.hexStrToBytes("0D AF 0D C3 F6 42 30 33 30 33 30 44 BB C6 C2 CC 07 E3 07 11 03 AE 89 10 07 13 0E 3A 01 82 F5 46 00 00 00 4E 00 11 00 F2 00 01 0D 6D 00 00 00 00 00 04 00 03 00 00 00 00 00 00 00 00 63 A1 7E");
        GpsMessage gpsMessage = new GpsMessage();
        gpsMessage.readFromBytes(test);
    }

}
