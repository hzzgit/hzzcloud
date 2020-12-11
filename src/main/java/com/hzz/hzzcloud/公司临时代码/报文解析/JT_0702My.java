package com.hzz.hzzcloud.公司临时代码.报文解析;

import com.ltmonitor.jt808.protocol.IMessageBody;
import com.ltmonitor.jt808.protocol.JT_0702_old;
import com.ltmonitor.jt808.protocol.MyBuffer;

import java.io.UnsupportedEncodingException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 15:31
 */
public class JT_0702My implements IMessageBody {
    private byte cardState;
    private String operTime;
    private byte readResult;
    private byte driverNameLength;
    private String driverName;
    private String certificationCode;
    private byte agencyNameLength;
    private String agencyName;
    private String validateDate;
    private String driverIdCard;
    private JT_0702_old oldDriverData;

    public JT_0702My() {
    }

    public int getMsgType() {
        return 1794;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("JT_0702{");
        sb.append("cardState=").append(this.cardState);
        sb.append(", operTime='").append(this.operTime).append('\'');
        sb.append(", readResult=").append(this.readResult);
        sb.append(", driverNameLength=").append(this.driverNameLength);
        sb.append(", driverName='").append(this.driverName).append('\'');
        sb.append(", certificationCode='").append(this.certificationCode).append('\'');
        sb.append(", agencyNameLength=").append(this.agencyNameLength);
        sb.append(", agencyName='").append(this.agencyName).append('\'');
        sb.append(", validateDate='").append(this.validateDate).append('\'');
        sb.append(", driverIdCard='").append(this.driverIdCard).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public final byte[] WriteToBytes(int version) {
        MyBuffer buff = new MyBuffer();
        try {
            buff.put(cardState);
            buff.put(Byte.parseByte(this.getOperTime().substring(2, 4), 16));
            buff.put(Byte.parseByte(this.getOperTime().substring(5, 7), 16));
            buff.put(Byte.parseByte(this.getOperTime().substring(8, 10), 16));
            buff.put(Byte.parseByte(this.getOperTime().substring(11, 13), 16));
            buff.put(Byte.parseByte(this.getOperTime().substring(14, 16), 16));
            buff.put(Byte.parseByte(this.getOperTime().substring(17, 19), 16));
            buff.put(readResult);
            buff.put((byte)driverName.getBytes("gbk").length);
            buff.put(driverName);
            byte[] gbks = certificationCode.getBytes("gbk");
//            if (gbks.length < 20) {
//                byte[] newgbk = new byte[20];
//                System.arraycopy(gbks, 0, newgbk, 20 - gbks.length, gbks.length);
//                gbks = newgbk;
//            }

            if (gbks.length < 20) {
                byte[] newgbk = new byte[20];
                System.arraycopy(gbks, 0, newgbk, 0, gbks.length);
                gbks = newgbk;
            }
            buff.put(gbks);
            buff.put((byte)agencyName.getBytes("gbk").length);
            buff.put(agencyName);
            buff.put(Byte.parseByte(this.getValidateDate().substring(0, 2), 16));
            buff.put(Byte.parseByte(this.getValidateDate().substring(2, 4), 16));
            buff.put(Byte.parseByte(this.getValidateDate().substring(4, 6), 16));
            buff.put(Byte.parseByte(this.getValidateDate().substring(6, 8), 16));

            byte[] gbks2 = driverIdCard.getBytes("gbk");
            if (gbks2.length < 20) {
                byte[] newgbk = new byte[20];
                System.arraycopy(gbks2, 0, newgbk, 20 - gbks2.length, gbks2.length);
                gbks2 = newgbk;
            }
            buff.put(gbks2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return buff.array();
    }

    public final void ReadFromBytes(int version, byte[] bytes) {
        try {
            MyBuffer buff = new MyBuffer(bytes);
            this.cardState = buff.get();
            if (this.cardState > 2) {
                this.driverNameLength = this.cardState;
                this.cardState = 0;
                this.driverName = buff.getString(this.driverNameLength);
                this.driverIdCard = buff.getString(20);
                this.certificationCode = buff.getString(40);
                this.agencyNameLength = buff.get();
                this.agencyName = buff.getString(this.agencyNameLength);
            } else if (this.cardState == 1) {
                this.operTime = buff.getBcdDateString();
                this.readResult = buff.get();
                if (this.readResult == 0) {
                    this.driverNameLength = buff.get();
                    this.driverName = buff.getString(this.driverNameLength);
                    this.certificationCode = new String(buff.gets(20),"GBK");
                    this.agencyNameLength = buff.get();
                    this.agencyName = buff.getString(this.agencyNameLength);
                    this.validateDate = buff.getBcdString(4);
                    if (this.isV2019(version)) {
                        this.driverIdCard =  new String(buff.gets(20),"GBK");
                    }
                }
            } else if (this.cardState == 2) {
                this.operTime = buff.getBcdDateString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public byte getCardState() {
        return this.cardState;
    }

    public void setCardState(byte cardState) {
        this.cardState = cardState;
    }

    public String getOperTime() {
        return this.operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public byte getReadResult() {
        return this.readResult;
    }

    public void setReadResult(byte readResult) {
        this.readResult = readResult;
    }

    public byte getDriverNameLength() {
        return this.driverNameLength;
    }

    public void setDriverNameLength(byte driverNameLength) {
        this.driverNameLength = driverNameLength;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getCertificationCode() {
        return this.certificationCode;
    }

    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode;
    }

    public byte getAgencyNameLength() {
        return this.agencyNameLength;
    }

    public void setAgencyNameLength(byte agencyNameLength) {
        this.agencyNameLength = agencyNameLength;
    }

    public String getAgencyName() {
        return this.agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getValidateDate() {
        return this.validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }

    public String getDriverIdCard() {
        return this.driverIdCard;
    }

    public void setDriverIdCard(String driverIdCard) {
        this.driverIdCard = driverIdCard;
    }

    public JT_0702_old getOldDriverData() {
        return this.oldDriverData;
    }

    public void setOldDriverData(JT_0702_old oldDriverData) {
        this.oldDriverData = oldDriverData;
    }
}
