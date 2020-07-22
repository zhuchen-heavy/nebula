package com.nebula.netty.serialize.impl;

/**
 * @author: zhu.chen
 * @date: 2020/7/22
 * @version: v1.0.0
 */
public class ObjectArraysSerializer extends ObjectSerializer {

    @Override
    public byte type() {
        return Types.TYPE_OBJECT_ARRAY;
    }

    @Override
    public Class getSerializeClass() {
        return new Object[]{}.getClass();
    }

}
