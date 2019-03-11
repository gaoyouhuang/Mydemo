package com.example.scs.myapplication.bean;

public class EventbusMessage {
    public EventbusMessage(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    private String msg;
    private int type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
