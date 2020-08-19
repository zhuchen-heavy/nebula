package com.nebula.design.pipeline;

import java.util.List;

/**
 * <p>
 * 常规实现
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/19
 * @version: v1.0.0
 */
public abstract class OrdinaryPipeline<T> implements Pipeline<T> {

    private final String name;

    private Pipeline<T> next;

    public OrdinaryPipeline(String name) {
        this.name = name;
    }

    public void setNext(Pipeline<T> next) {
        this.next = next;
    }

    @Override
    public abstract void process(PipelineContext ctx, T t);

    @Override
    public final void forward(PipelineContext ctx, T t) {
        if (next != null) {
            next.process(ctx, t);
        }
    }

    @Override
    public String toString() {
        if (next != null) {
            return name + "->" + next.toString();
        }
        return name;
    }

    /**
     * 得到实例
     * @param pipelines
     * @param <T>
     * @return
     */
    public static <T> Pipeline<T> getInstance(List<OrdinaryPipeline<T>> pipelines) {
        if (pipelines == null || pipelines.isEmpty()) {
            throw new IllegalArgumentException("empty pipelines!");
        }
        OrdinaryPipeline<T>[] a = (OrdinaryPipeline<T>[]) pipelines.toArray();
        OrdinaryPipeline<T> p = a[0];
        for (int i = 1; i < a.length; ++i) {
            p.setNext(a[i]);
            p = a[i];
        }
        // 设置尾节点为null
        p.setNext(null);
        // 返回头节点
        return a[0];
    }

}
