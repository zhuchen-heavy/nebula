package com.nebula.nebula.path;

/**
 * <p>
 *  各种获取classpath的方式
 * </p>
 * @author: zhu.chen
 * @date: 2020/8/5
 * @version: v1.0.0
 */
public class ClassPathHelper {

    public static void main(String[] args) {
        // 通过TCCL来获取classpath
        String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        // /D:/git_workplace/nebula/nebula-interview/target/classes/
        System.out.println(classPath);
    }

}
