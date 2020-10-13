package com.kcm.common.enums;

/**
 * ClassName: ResultCode
 * @author lucky
 * @date 2020/8/4
 **/
public enum ResultCode {
    /* 成功状态码 */

    SUCCESS(200, "成功"),

    SUCCESS_QUERY(200, "查询成功"),

    SUCCESS_ADD(200, "新增成功"),

    SUCCESS_DELETE(200, "删除成功"),

    SUCCESS_UPDATE(200, "修改成功"),

    SUCCESS_USER_LOGIN(200, "登录成功！"),

    /* 参数错误 1001-1999 */
    ERR_PARAM_IS_INVALID(1001, "参数无效"),

    ERR_PARAM_IS_BLANK(1002, "参数不能为空"),

    ERR_PARAM_TYPE_BIND_ERROR(1003, "参数类型错误"),

    ERR_PARAM_NOT_COMPLETE(1004, "参数缺失"),

    ERR_PAGE_PARAM_NOT_COMPLETE(1005, "分页参数(page、limit)无效"),

    ERR_PAGE_PARAM_VAR_ERROR(1006, "参数无效"),

    /* 用户错误 2001-2999 */
    ERR_USER_NOT_LOGIN(2001, "登录失败！"),

    ERR_USER_BAD_CREDENTIALS(2002, "账户或密码输入错误，请重新登录！"),

    ERR_USER_LOCKED(2003, "账户被锁定，请联系管理员！"),

    ERR_USER_CREDENTIALS_EXPIRED(2004, "密码过期，请联系管理员！"),

    ERR_USER_ACCOUNT_EXPIRED(2005, "账户过期，请联系管理员！"),

    ERR_USER_DISABLED(2006, "账户被禁用，请联系管理员！"),

    ERR_USER_ACCESS_DENIED(2007, "权限不足，请联系管理员！"),

    ERR_USER_AUTHENTICATION(2008, "访问资源不存在！"),

    ERR_USER_INSUFFICIENT_AUTHENTICATION(2009, "访问失败，请先登录！"),

    ERR_USER_SESSION_INFORMATION_EXPIRED_STRATEGY(2010, "账户已在另一台机器登录，建议修改登录密码！"),

    /* 数据库错误 3001-3999 */
    ERR_SQL_INSERT_ERROR(3001, "数据库插入操作失败"),

    ERR_SQL_UPDATE_ERROR(3002, "数据库更新操作失败"),

    ERR_SQL_SELECT_ERROR(3003, "数据库查询失败"),

    ERR_SQL_RESULT_NULL(3004, "空结果集"),

    ERR_SQL_TRANS_ERROR(3005, "数据库事务错误"),

    ERR_SQL_VIOLATION_ONLY_CONSTRAINT(3006, "违反唯一约束条件"),

    ERR_DATA_EXISTS(4001, "数据已存在"),

    /* 业务约束 5000-5999*/
    ERR_BUSINESS_CONSTRAINTS_GE2(5001, "业务限制，该类型数据不能超过2条"),

    ERR_BUSINESS_REPEAT_SUBMIT(5002, "业务重复提交"),
    //TODO
    ERR_BUSINESS_CONSTRAINTS_NOR_EQ(5003, "...");

    private final Integer code;
    private final String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
