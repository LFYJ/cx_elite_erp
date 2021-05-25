package com.erp.common;

/**
 * 返回状态
 */
public class ExecuteResultState {


    /**
     *  成功
     */
    public static final int SUCCEED = 200;
    /**
     *  失败
     */
    public static final int FAILURE = 201;
    /**
     *  数据校验失败
     */
    public static final int DATA_VERIFY_FAILURE = 202;
    /**
     *  信息验证失败
     */
    public static final int DATA_VERIFY_FORMAT = 203;

    /**
     *  token
     */
    public static final int TOKEN_VERIFY_FORMAT = 401;

    /**
     *  验证通过
     */
    public static final int FEEDBACK_PASS = 1;
    /**
     *  验证失败
     */
    public static final int FEEDBACK_REJECT = 2;
    /**
     *  反馈未处理
     */
    public static final int FEEDBACK_UNTREATED = 0;


    /**
     *  普通用户
     */
    public static final int USER_COMMON = 0;
    /**
     *  超级管理员
     */
    public static final int USER_ADMINISTRATOR = 1;


}
