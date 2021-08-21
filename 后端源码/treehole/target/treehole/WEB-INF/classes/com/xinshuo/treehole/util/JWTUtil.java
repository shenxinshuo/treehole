package com.xinshuo.treehole.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * jwt工具类
 */
public class JWTUtil {
    //过期时间30分钟
    private static final long EXPIRE_TIME = 30*60*60*100;
    //秘钥
    private static final String SECRET = "shiro+jwt";

    /**
     * 生成token 30分钟后过期
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //附带username信息
            return JWT.create()
                    .withClaim("username", username)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的jwt并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token
     * @param username
     * @return
     */
    public static boolean verify(String token , String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证token
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *获得token中的信息，无需secret解密也能得到
     * @param token
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try{
            DecodedJWT decode = JWT.decode(token);
            return decode.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
            return null;
        }
    }
}
