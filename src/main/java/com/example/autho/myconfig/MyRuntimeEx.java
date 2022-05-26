package com.example.autho.myconfig;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
public class MyRuntimeEx extends RuntimeException implements Serializable {

    protected Object data;
    protected String code;
    private String msg;
    public MyRuntimeEx(Object object) {
//        super(object.toString());
        JSONObject jsonObject = JSONObject.parseObject(object.toString());
        jsonObject.put("code",404);
        supper(jsonObject);
       this.data=object;
    }

    public static Object supper(Object o) {
        return o;
    }

    public class supper extends Throwable {
        public supper(Object s) {

        }
    }

    public MyRuntimeEx(String message,Object o) {
        fillInStackTrace();
        data = o;
        msg = message;
    }


    public MyRuntimeEx(String errCode, String errMsg) {
        this.code = errCode;
        this.msg = errMsg;
    }
}
