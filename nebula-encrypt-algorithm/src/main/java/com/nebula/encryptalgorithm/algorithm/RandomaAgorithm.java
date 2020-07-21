package com.nebula.encryptalgorithm.algorithm;


import java.security.SecureRandom;
import java.util.Random;

/**
 * <p>
 * 实现随机数算法
 * </p>
 * @author zhu.chen
 * @date 2019-03-28
 */
public class RandomaAgorithm {

    /**
     * <p>
     * 1：使用Math.random()来实现，返回0~9之间的整数
     * </p>
     */
    public static int mathRandom(){
        // Math.random()：返回的是0(包含)到1(不包含)之间的double值
        double random = Math.random();
        int i = (int) (random *  10);
        return i;
    }

    /**
     * <p>
     * 2：使用Random()来实现，返回0~9之间的整数
     * </p>
     */
    public static int random(){
        Random random = new Random();
        return random.nextInt(10);
    }

    /**
     * <p>
     * 3：使用SecureRandom()来实现，返回0~9之间的整数
     * </p>
     */
    public static int secureRandom(){
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(10);
    }

    /**
     * 算法题：已经有1~7的随机数 [1,7]
     */
    public static int randomSeven(){
        SecureRandom secureRandom = new SecureRandom();
        int value = secureRandom.nextInt(7) + 1;
        return value;
    }

    // 若利用1~7要生成1~10的随机数，只需要在其基础上+0~4 [0,4)
    // [1,7] + [0,4) = [1,11) ----> 取int即为：[1,10]
    public static int randomFour(){
        SecureRandom secureRandom = new SecureRandom();
        int value = secureRandom.nextInt(4);
        return value;
    }


    // main方法是虚拟机的入口方法
    public static void main(String[] args) {
        //System.out.println(mathRandom());
        //System.out.println(random());
        //System.out.println(secureRandom());
        //System.out.println(randomSeven());
        System.out.println(randomSeven() + randomFour());
    }

}
