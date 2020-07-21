package com.nebula.encryptalgorithm.test1;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
//        File classpath = new File("/Users/zhuchen/software/idea_workspace/encrypt-algorithm/hello.class");
//        String helloClassPath = "/Users/zhuchen/software/idea_workspace/encrypt-algorithm/target/classes/com/zhuchen/encryptalgorithm/test1/hello.class";
//        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(helloClassPath));
////        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(classpath));
////        int data = 0;
////        while ((data = bis.read()) != -1) {
////            bos.write(data);
////        }
////        bos.flush();
////        bos.close();
//
//        File txtpath = new File("/Users/zhuchen/software/idea_workspace/encrypt-algorithm/src/main/resources/hello.txt");
//        System.out.println(txtpath.exists());
//        BufferedOutputStream bos1 = new BufferedOutputStream(new FileOutputStream(txtpath));
//        int data1 = 0;
//        while ((data1 = bis.read()) != -1) {
//            bos1.write(data1
//                    ^ 0xff);
//        }
//
//        bis.close();
//        bos1.flush();
//        bos1.close();
//        int a = 1;
//        System.out.println(String.valueOf(1).getBytes().length);
        System.out.println((-1L << 64L) ^ -1L);
        // 9223372036 854776000
    }

}
