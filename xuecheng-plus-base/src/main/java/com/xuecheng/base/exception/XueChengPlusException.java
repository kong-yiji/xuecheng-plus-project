package com.xuecheng.base.exception;

/**
 * @author : yin
 * @Date: 2023/4/26
 * @Description:TODO
 */
public class XueChengPlusException extends RuntimeException {
    private String errMsg;
    public XueChengPlusException(){super();}
    public XueChengPlusException(String errMsg){
        this.errMsg=errMsg;}
    public String getErrMsg(){return errMsg;};
    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    public static void cast(String errMsg){
        throw new XueChengPlusException(errMsg);
    }


}
