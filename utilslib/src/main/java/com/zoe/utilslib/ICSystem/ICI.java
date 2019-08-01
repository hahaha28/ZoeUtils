package com.zoe.utilslib.ICSystem;

import org.litepal.crud.LitePalSupport;

/**
 *      JavaBean类
 *      用来存储ICI的消息
 */
public class ICI extends LitePalSupport {

    /**
     * 消息发给谁
     */
    private String to;

    /**
     * 消息内容
     */
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

