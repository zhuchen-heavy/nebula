package com.nebula.encryptalgorithm.test;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update("张三".getBytes());
        byte[] md5bytes = md5.digest();
        // YV21 eqMU Upqq D76V s+lb 0w== ---> 24长度的base64字符 MD5生成的是128bit，即为16字节。（base64会进行补位，=号进行补位）
        System.out.println(Base64.encodeBase64String(md5bytes));

        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update("张三".getBytes());
        byte[] shabytes = sha.digest();
        // HYQb wO6Y MJy3 kWZw t/D9 719M NRUH EaQU Be82 M7Vj Is8= ---> 44长度的base64字符 SHA-256生成的是256bit，即为32字节。（base64会进行补位，=号进行补位）
        System.out.println(Base64.encodeBase64String(shabytes));

        System.out.println("-------------------");

        byte[] bytes = Base64.decodeBase64(Base64.encodeBase64String(shabytes));
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i] + " ");
        }
    }

}
