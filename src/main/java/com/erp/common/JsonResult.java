package com.erp.common;


import lombok.Data;

@Data
public class JsonResult {

    private Object data;
    private int state;
    private String msg;


    public JsonResult(Object  data, int state , String msg) {
        this.data=data;
        this.state=state;
        this.msg=msg;
    }

}
