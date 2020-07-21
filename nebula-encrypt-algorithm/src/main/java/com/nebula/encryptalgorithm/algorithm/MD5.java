package com.nebula.encryptalgorithm.algorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * MD5算法实现，基于spring的实现方式来实现，spring的util类DigestUtils也是这么实现的。
 * </p>
 * @author: chen.zhu
 * @date: 2019/3/28
 */
public class MD5 {

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static char[] encodeByteToHex(byte[] bytes) throws NoSuchAlgorithmException {
        // 生成MD5摘要 -- MessageDigest.getInstance：返回实现指定摘要算法的 MessageDigest 对象。
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        // 更新摘要 -- messageDigest.update：使用指定的byte数组更新摘要。
        messageDigest.update(bytes);
        // 生成加密后的自己数组   messageDigest.digest：通过执行诸如填充之类的最终操作完成哈希计算。在调用此方法之后，摘要被重置。
        byte[] md = messageDigest.digest();
        // 生成32位长度的MD5十六进制的字符串
        char[] chars = new char[32];
        // 因为1个byte对应2个十六进制字符串，所以i相隔2，取的时候取1个byte，存2个十六进制字符串。
        for (int i = 0; i < chars.length; i = i + 2) {
            // byte是一个一个取
            byte b = md[i / 2];
            // b >>> 0x4：表示的是无符号右移4位，高位补0。(b >>> 0x4) & 0xf：&运算，0x4 = 4(十进制)，0xf = 1111(二进制)
            // 取byte的高4位，转换为十六进制字符。
            chars[i] = HEX_CHAR[(b >>> 0x4) & 0xf];
            // 取byte的低4位，转换为十六进制字符。
            chars[i + 1] = HEX_CHAR[b & 0xf];
        }
        return chars;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 8d672fa974 cec824f0d5 f67d864dd4 7e ----> 32位长度的md5字符串
        System.out.println(new String(encodeByteToHex("asdadadada".getBytes())));
    }

}
