package com.mall.web.security.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.mall.web.security.JwtService;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * <pre>
 * JwtService's Factory
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 22:24
 */
public class JwtServiceFactory {

    /**
     * system internal public key
     */
    private final static String SYS_DEFAULT_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiJhQDtqpwMoCal7SkFvgJAznAqgo8AE3eMQfDZN+CjR9Vu3JyA/U6CLrtDYtbHTBOiyGjS/Fmjm8dGth4V2/oJolf0pRQVd51gLNqz/6nVjgvvroWfgMPpTThPlFkfPIDXsOsyvgcgloMpN06/3oV+R81Hu2unixvdO5837HJA61IvkzxW9kaXyyeTv1hHxsZTKc397Hr1l0d6eF7f+elkyijmVGXCoFHRyaS6DW/tFMznNFievWmizKmMdIswY41je6Qgm1tjADRyNPBsII4P2mIiGJ/hrNkvz8s4QuL+y1NkviqfO6UiY0ueWCBAzOOpGrBrfU1m0kfi1owgqfdQIDAQAB";

    /**
     * system internal private key
     */
    private final static String SYS_DEFAULT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCImFAO2qnAygJqXtKQW+AkDOcCqCjwATd4xB8Nk34KNH1W7cnID9ToIuu0Ni1sdME6LIaNL8WaObx0a2HhXb+gmiV/SlFBV3nWAs2rP/qdWOC++uhZ+Aw+lNOE+UWR88gNew6zK+ByCWgyk3Tr/ehX5HzUe7a6eLG907nzfsckDrUi+TPFb2RpfLJ5O/WEfGxlMpzf3sevWXR3p4Xt/56WTKKOZUZcKgUdHJpLoNb+0UzOc0WJ69aaLMqYx0izBjjWN7pCCbW2MANHI08Gwgjg/aYiIYn+Gs2S/PyzhC4v7LU2S+Kp87pSJjS55YIEDM46kasGt9TWbSR+LWjCCp91AgMBAAECggEATzJwiIxDjCIUEHR2BXkflgddEW9KfQ1Xik+76XUwyN3yJiqsqbB0Mzu8Cbq3z4BKYF5cjRO7sFX1bFKuZ6qhZo65WvpN7mK3o1vwv2/S8NQNSu3TNgbZopdLpHea3KrJvPcyaLPx6GyH23sfeYy33+38an5oTemYtIVD9Wf2MnLq4r32r8ZpLn2+Fcz+E7k2rTgK6AOEXq2CPB3nQylMHKZ2L9P1DKw7aCR7W4l4kK16Efo7tJumgNOxkVBNK58cls4mMXrhyHH0Du3+t/45hE81CHGsdLq/yzzm6XqwqmzuOn4th3OH+lBc4v483OLw5fIKDjoKk23rgNb1bNZQ6QKBgQD4o5GeUuvQ1YHwdPiNnazsoYD3/z1N63kbIqKs51LcFj5QXRH1MWUGxN55Kd0yJQZM/E1cPhA4fCUNgYg3A5cQD7JAFxPOV6Y7LqMumFEaqC0unUyhw0E/v86R7//j4Y9YWefozIV/T32TDABOCT9ZL16erdZ1A4kgl3cnnMUnjwKBgQCMo5BtsxpSUMNVdOABjwsKK6TeloFZoJ1ckYgQyhcXUmac9ctsAPoOHuV8GO7UJRZshcJ8Y/6vGvTs/Ej0vijqdPudLUHngjF3kRiQmxbDYhvEJmzg9udgDXCdoINRsi+LnvVBvWiFbjWDAJ7arWS8VK6z5kQ7A+uweeO+A6CmuwKBgQDCg/ydFxQjeZVBT9FPwSYSrbreyVDlj61cHDmV+Rbq1YAEkPtGjlER/Zxqv+SroSmPuuaunmNEPWMjcM3m9CkPSKhT2XoGOK30qOTCxCjXKPDIAgl8l+uI+v6ZT2AqoEDrhtKWXsKlucNs8bufUT69AAEPNMKNIqn51GVEmTHoYwKBgGTMGgi1tCjJH6bQ9HfJiNUKQMkTv7N4LTCaUZYO8YvSkB7w/5HyBUY3mKxfxlSgHDk9iQ3UMUZZIK/1hWh8HiEkJO4a8MA3jLKweJWzwhXY7COikqiN7XjnSQ6Aa34TIoaEMC2kzhPL0GiK+k+qtKdSXkAryj0t6y95vOOEABFfAoGAR5m62tKiZWRTKJQ30hWw82Lv3fR318OWhWkmuaGwASvOglkM4s+REsMU7geYK34ZNvLKHilq9dwvtGhNAEn9EMQJfDAjZZnD2RCVpG/fyx4VI8HMMtp5JA36+pGGX4rVkR6KkbcZuNkSwoJjNZlHcme7hsHjglxgrN5ETDD/Uxk=";


    public static JwtService createJwtService(String publicKey, String privateKey, JwtServiceGenerateType type) throws Exception{

        switch (type) {
            case SYS:
                return createJwtServiceByString(SYS_DEFAULT_PUBLIC_KEY, SYS_DEFAULT_PRIVATE_KEY);
            case STR:
                return createJwtServiceByString(publicKey, privateKey);
            case FILE:
                return createJwtServiceByFile(publicKey, privateKey);
            default:
                throw new UnsupportedOperationException("this way can't create JwtService");
        }
    }

    /**
     * generate JwtService by public key String and private key String
     * @param publicKeyStr
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    private static JwtService createJwtServiceByString(String publicKeyStr, String privateKeyStr) throws Exception {

        // 从公钥和私钥字符串转为公钥和私钥后转为对应的加密算法对象
        Base64.Decoder decoder = Base64.getDecoder();
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(decoder.decode(publicKeyStr));
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decoder.decode(privateKeyStr));
        KeyFactory keyFactory  = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(bobPubKeySpec);
        RSAPrivateKey privateKey  = (RSAPrivateKey)keyFactory.generatePrivate(priPKCS8);
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        return new JwtService(algorithm);
    }

    /**
     * generate JwtService by public key file and private key file
     * @param publicKeyPath
     * @param privateKeyPath
     * @return
     */
    private static JwtService createJwtServiceByFile(String publicKeyPath, String privateKeyPath) throws Exception {
        // TODO 待实现
        return null;
    }



    /**
     * 创建 JwtService 的方式
     */
    public enum JwtServiceGenerateType{

        /**
         * system internal
         */
        SYS,

        /**
         * outsite String
         */
        STR,

        /**
         * file model
         */
        FILE
    }
}
