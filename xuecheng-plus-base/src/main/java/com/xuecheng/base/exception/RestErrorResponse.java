package com.xuecheng.base.exception;

import java.io.Serializable;

/**
 * @author : yin
 * @Date: 2023/4/26
 * @Description:TODO
 */
public class RestErrorResponse  implements Serializable {
    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

}
