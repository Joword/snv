package com.snv.common;

/**
 * @author Joword
 * @date: 2023/3/9 18:07
 * @version: 1.0
 * @description: 返回结果接口状态码
 */
public interface ResultCode {
    Integer RESULT_SUCC = 1000;
    /*服务异常*/
    Integer SERVER_EXCEPTION = 1100;
    Integer SOCKET_EXCEPTION = 1200;

    /*用户相关的返回码*/
    Integer USERNAME_ISEXTRA = 2001;//用户名已经存在
    Integer USERNAME_ERROR = 2002;//用户名错误
    Integer PASSWORD_ERROR = 2003;//密码错误
    Integer LOGINTOKEN_ERROR = 2004;//用户登录凭证过期或不存在
    Integer REGISTERCODE_ISEXTRA = 2005;//注册验证已发送过,且在一分钟后才能再次发送
    Integer SENDEMAIL_ERROR = 2006;//发送邮件失败
    Integer EMAIL_ISEXTRA = 2007;//注册邮箱已存在
    Integer REGISTERCODE_ERROR = 2008;//注册验证码错误
    Integer EMAILORPASSWORD_ERROR = 2009;//邮箱账号或密码错误
    Integer EMAILUSER_ISEXTRA = 2010;//邮箱用户不存在
    Integer EMAILCODE_HADSEND = 2011;//邮件已发送
    Integer MANUAL_ISEXTRA = 2012;//非解读
    Integer VARIANTID_ISEXTRA = 2013;//VARIANTID不存在
    Integer RSID_ISEXTRA = 2014;//RSID不存在
    Integer HGVSC_ISEXTRA = 2015;//RSID不存在
    Integer REGISTER_ERROR = 2016;//用户注册凭证过期或不存在
    //email标题内容有误
    Integer EMAIL_MESSAGE_ERROR = 2018;
    //非开发者用户
    Integer ISNOT_DEVELOPER = 2017;
    /**
     * 解读人员不再使用
     */
    Integer PARSER_REMOVED = 2100;
    /**
     * 开发人员不再使用
     */
    Integer DEVELOPER_REMOVED = 2101;

    /*系统内部服务异常*/
    Integer IMAGE_BIG = 3001;
    Integer DATA_EXCEPTION = 3002;
    Integer GENE_ISNULL = 3003;
    Integer RSID_EXCEPTION = 3004;

    Integer GROUP_ISEXTRA = 4001; //组名已存在
    Integer GROUPNUM_ISEXTRA = 4002; //组员人数超出最大限定值
    Integer GENE_NONEXIST = 4003; //基因不在系统列表中

    /*数据库数据异常：5000-5999*/
    Integer DATA_NONEXIST = 5000;
    Integer DATA_ISEXTRA = 5001;

    /*参考文献爬虫异常*/
    Integer SELENIUM_CRAWLER_EXCEPTION = 6001;

    Integer POPULATION_FREQUENCY = 7001;

    /*总结报告参数*/
    Integer SUMMARY_REPORT_EXCEPTION = 8000;
    Integer SUMMARY_REPORT_NULL = 8010;
}
