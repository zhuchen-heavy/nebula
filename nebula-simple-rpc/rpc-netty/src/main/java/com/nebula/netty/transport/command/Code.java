package com.nebula.netty.transport.command;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public enum  Code {

    SUCCESS(0,"SUCCESS"),

    NO_PROVIDER(-2,"NO_PROVIDER"),

    UNKNOWN_ERROR(-1,"UNKNOWN_ERROR");

    private static Map<Integer, Code> codeMap = new ConcurrentHashMap<>();

    private int code;

    private String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    static {
        for (Code code : Code.values()) {
            codeMap.put(code.code, code);
        }
    }

    public static Code valueOf(int code) {
        return codeMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMessage(Object... args) {
        if (args.length < 1) {
            return message;
        }
        return String.format(message, args);
    }

}
