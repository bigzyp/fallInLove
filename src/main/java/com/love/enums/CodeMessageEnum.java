package com.love.enums;

/**
 * @Author: lixin
 * @Description: 响应码枚举
 * @Date: 2018/7/20 10:44
 */
public enum CodeMessageEnum {

    REQUEST_SUCCESS("200", "服务成功返回请求数据"),

    UPDATE_SUCCESS("201", "新建或修改数据成功"),

    ASYNC_TASK("202", "异步任务"),

    DELETE_SUCCESS("204", "删除数据成功"),

    REQUEST_ERROR("400", "请求错误"),

    NOT_AUTHORITY("401", "用户没有权限"),

    RELATIONS_EXIST("402", "请求删除的资源被其他资源使用，无法执行删除操作"),

    ACCESS_BARRED("403", "禁止访问"),

    ALREADY_SIGNED("505", "已报名该课程"),

    LIMIT_RECORD("602", "禁止报名"),

    OVER_LIMIT("603", "超过报名人数限制"),

    IS_STUDY_CARD_OPEN("604", "未开启学习卡激活权限"),

    DOWN_EXCEL_IS_NULL("607", "没有有效的导出内容"),

    STUDY_CARD_NUMBER_LIMIT("609", "超过学习卡单次最大生成数量"),

    ALL_STUDY_CARD_NUMBER_LIMIT("610", "超出学习卡总数限制"),

    STUDY_CARD_IS_OVERDUE("611", "该学习卡已过期"),

    STUDY_CARD_NOT_START("612", "该学习卡未生效"),

    STUDY_CARD_IS_INVALID("614", "此学习卡已失效"),

    STUDY_CARD_IS_NOT_EXIST("615", "此学习卡不存在"),
    SIGN_DATE_IS_ERROR("3020", "该时间不能签到"),
    TEACHER_SIGN_IS_ERROR("3021", "教师签到失败"),

    RECORDS_NOT_EXIST("404", "记录不存在"),

    FORMAT_NOT_AVAILABLE("406", "格式不可得"),

    RESOURCES_HAS_DELETE("410", "资源已被删除"),

    VALIDATION_ERROR("422", "请求参数错误"),

    VALIDATION_COURSE_ERROR("423", "开通课程验证错误"),

    SERVICE_ERROR("500", "服务器错误"),

    GATEWAY_ERROR("502", "网关错误"),

    SERVICE_NOT_AVAILABLE("503", "服务不可用"),

    GATEWAY_TIMEOUT("504", "网关超时"),

    SCORE_LESS("518", "积分不足"),

    MEMORY_LIMIT("519", "网校存储容量达到上限"),

    PERMISSION_REQUIRED("777", "菜单权限不足"),

    COMMMON_BLACK("1403", "答疑禁止提问和回复"),

    DIRECTORY_EXCEED_LIMIT("4221", "当前目录下文件夹达到上限"),
    FILE_EXCEED_LIMIT("4222", "当前目录下文件达到上限"),
    ENTITY_TOO_LARGE("413", "文件过大"),

    AUTHORITY_PRIVATE("4011", "只允许操作所在网校的签到规则"),
    SIGN_TIME_OVERLAP("4221", "签到时间段存在重叠")
    ;

    private String code;

    private String msg;

    private CodeMessageEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getcode(String msg) {
        for (CodeMessageEnum codeMsg : CodeMessageEnum.values()) {
            if (codeMsg.msg.equals(msg)) {
                return codeMsg.code;
            }
        }
        return null;
    }

    public static String getMsg(String code) {
        for (CodeMessageEnum codeMsg : CodeMessageEnum.values()) {
            if (codeMsg.code.equals(code)) {
                return codeMsg.msg;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
