package com.love.utils;

import com.love.enums.CodeMessageEnum;
import com.love.enums.LoveRuntimeException;

import java.io.Serializable;

/** @Author: Lee CE @Description: @Date: 2018/7/16 09:07 @Modified: */
public class Result implements Serializable {

  private static final long serialVersionUID = -1L;

  /** 状态码 */
  private Boolean success;

  /** 结果消息 */
  private String message;

  /** 错误码 */
  private String code;

  /** 返回的数据 */
  private Object data;

  private Long timeStamp;

  /**
   * 构造函数
   * @param success
   * @param message
   * @param code
   * @param data
   */
  public Result(Boolean success, String message, String code, Object data) {
    this.success = success;
    this.message = message;
    this.code = code;
    this.data = data;
    this.timeStamp = System.currentTimeMillis();
  }

  /**
   * 构造函数
   * @param data
   */
  public Result(LoveRuntimeException e, Object data) {
    this.success = false;
    this.message = e.getMessage();
    this.code = e.getErrorCode();
    this.data = data;
    this.timeStamp = System.currentTimeMillis();
  }

  /**
   * 构造函数
   * @param success
   * @param code
   * @param data
   */
  public Result(Boolean success, String code, Object data) {
    this.success = success;
    this.code = code;
    this.data = data;
    this.timeStamp = System.currentTimeMillis();
  }

  /**
   * 构造函数
   * @param success
   * @param codeMessageEnum
   * @param data
   */
  public Result(Boolean success, CodeMessageEnum codeMessageEnum, Object data) {
    this.success = success;
    this.code = codeMessageEnum.getCode();
    this.message = codeMessageEnum.getMsg();
    this.data = data;
    this.timeStamp = System.currentTimeMillis();
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
