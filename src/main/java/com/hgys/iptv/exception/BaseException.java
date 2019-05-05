package com.hgys.iptv.exception;

import com.hgys.iptv.model.enums.ResultEnum;
import lombok.Data;

/**
 * Description:
 *
 * @author yangpeng
 */
@Data
public class BaseException extends RuntimeException {

    private ResultEnum error		= ResultEnum.SYSTEM_INTERNAL_ERROR;
    // 存在在IError中没定义的异常信息，比如批量提交数据，其中一条数据出错了，报的异常信息放在extMessage中。
    private String		extMessage	= null;


    public BaseException() {
    }


    public BaseException(String message) {
        super(message);
        this.extMessage = message;
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.extMessage = message;
    }


    public BaseException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.error = resultEnum;
    }


    public BaseException(String msg, ResultEnum resultEnum) {
        super(msg);
        this.error = resultEnum;
        this.extMessage = msg;
    }


    public ResultEnum getError() {
        return error;
    }


    public void setError(ResultEnum error) {
        this.error = error;
    }


    public String getExtMessage() {
        return extMessage;
    }


    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

}