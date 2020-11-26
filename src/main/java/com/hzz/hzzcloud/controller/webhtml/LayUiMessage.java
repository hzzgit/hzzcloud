package com.hzz.hzzcloud.controller.webhtml;

import lombok.Data;

@Data
public class LayUiMessage {
    private int code = 0;
    private long count = 0;
    private Object data;
    private String msg = "操作成功";

    public static LayUiMessage search(long count,Object data) {
        LayUiMessage layUiMessage = new LayUiMessage();
        layUiMessage.setCount(count);
        layUiMessage.setData(data);
        return layUiMessage;
    }

    public static LayUiMessage retu(boolean arg) {
        if (arg) {
            return suc();
        } else {
            return err();
        }
    }

    public static LayUiMessage err() {
        LayUiMessage layUiMessage = new LayUiMessage();
        layUiMessage.code = 500;
        layUiMessage.msg = "服务器异常";
        return layUiMessage;
    }

    public static LayUiMessage suc() {
        LayUiMessage layUiMessage = new LayUiMessage();
        return layUiMessage;
    }

}
