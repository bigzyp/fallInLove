package com.love.enums;

/**
 * @Author: Lee CE
 * @Description: 自定义运行时异常，抛出不符合业务需求的结果异常
 * @Date: 2018/7/12 15:04
 * @Modified:
 */
public class LoveRuntimeException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        /**
         * 错误编码
         */
        private String errorCode;

        /**
         * 消息是否为属性文件中的Key
         */
        private boolean propertiesKey = true;

        /**
         * 构造一个基本异常.
         *
         * @param message 信息描述
         */
        public LoveRuntimeException(String message)
        {
            super(message);
        }

        /**
         * 构造一个基本异常.
         *
         * @param errorCode 错误编码
         * @param message 信息描述
         */
        public LoveRuntimeException(String errorCode, String message)
        {
            this(errorCode, message, true);
        }

        /**
         * 构造一个基本异常.
         *
         * @param codeMessageEnum 错误枚举
         */
        public LoveRuntimeException(CodeMessageEnum codeMessageEnum)
    {
        this(codeMessageEnum.getCode(), codeMessageEnum.getMsg(), true);
    }

        /**
         * 构造一个基本异常.
         *
         * @param errorCode 错误编码
         * @param message 信息描述
         */
        public LoveRuntimeException(String errorCode, String message, Throwable cause)
        {
            this(errorCode, message, cause, true);
        }

        /**
         * 构造一个基本异常.
         *
         * @param errorCode 错误编码
         * @param message 信息描述
         * @param propertiesKey 消息是否为属性文件中的Key
         */
        public LoveRuntimeException(String errorCode, String message, boolean propertiesKey)
        {
            super(message);
            this.setErrorCode(errorCode);
            this.setPropertiesKey(propertiesKey);
        }

        /**
         * 构造一个基本异常.
         *
         * @param errorCode 错误编码
         * @param message 信息描述
         */
        public LoveRuntimeException(String errorCode, String message, Throwable cause, boolean propertiesKey)
        {
            super(message, cause);
            this.setErrorCode(errorCode);
            this.setPropertiesKey(propertiesKey);
        }

        /**
         * 构造一个基本异常.
         *
         * @param message 信息描述
         * @param cause 根异常类（可以存入任何异常）
         */
        public LoveRuntimeException(String message, Throwable cause)
        {
            super(message, cause);
        }

        public String getErrorCode()
        {
            return errorCode;
        }

        public void setErrorCode(String errorCode)
        {
            this.errorCode = errorCode;
        }

        public boolean isPropertiesKey()
        {
            return propertiesKey;
        }

        public void setPropertiesKey(boolean propertiesKey)
        {
            this.propertiesKey = propertiesKey;
        }
}
