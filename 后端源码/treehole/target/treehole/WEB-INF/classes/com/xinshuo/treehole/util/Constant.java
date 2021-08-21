package com.xinshuo.treehole.util;

public class Constant {
    //请求处理成功
    public static final int HTTP_CODE_OK = 200;
    //请求处理成功，但响应报文没有主题返回
    public static final int HTTP_CODE_NO_CONTENT = 204;
    //客户端进行了范围请求，服务器成功执行请求并返回指定范围的实体内容
    public static final int HTTP_CODE_PARTIAL_CONTENT = 206;
    //永久性重定向，请求的请求已经被分配到新的URL
    public static final int HTTP_CODE_MOVED_PERMANENTLY = 301;
    //临时性重定向
    public static final int HTTP_CODE_FOUND = 302;
    //客户端发送附带条件的请求后，服务器允许请求，但内容并没修改，返回304。即客户端可以使用缓存的内容
    public static final int HTTP_CODE_NOT_MODIFIED = 304;
    //请求报文存在语法错误，需要修正请求报文后再次发送请求
    public static final int HTTP_CODE_BAD_REQUEST = 400;
    //请求资源的访问被服务器拒绝，服务器没必要给出拒绝的理由
    public static final int HTTP_CODE_FORBIDDEN = 403;
    //找不到被请求的资源
    public static final int HTTP_CODE_NOT_FOUND = 404;
    //服务器内部异常
    public static final int HTTP_CODE_INTERNET_SERVER_ERROR = 500;
    //服务器处于超载或者故障状态
    public static final int HTTP_CODE_SERVICE_UNAVAILABLE = 503;

    //redis 点赞时存入redis code为1
    public static final int REDIS_LIKE_CODE = 1;
    //redis 取消点赞时存入redis code为0
    public static final int REDIS_UNLIKE_CODE = 0;
}
