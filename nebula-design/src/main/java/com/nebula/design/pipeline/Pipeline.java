package com.nebula.design.pipeline;

/**
 * <p>
 * 流水线设计模式
 * </p>
 *
 * @author: zhu.chen
 * @date: 2020/8/19
 * @version: v1.0.0
 */
public interface Pipeline<T> {

    /**
     * <p>
     * 处理
     * </p>
     *
     * @param ctx
     * @param t
     */
    void process(PipelineContext ctx, T t);

    /**
     * <p>
     * 转发给下游
     * </p>
     *
     * @param ctx
     * @param t
     */
    void forward(PipelineContext ctx, T t);

}
