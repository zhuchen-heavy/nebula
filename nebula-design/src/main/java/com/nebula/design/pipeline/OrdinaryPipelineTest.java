package com.nebula.design.pipeline;

import java.util.Arrays;

public class OrdinaryPipelineTest {

    public static void main(String[] args) {
        Pipeline<?> pipeline =
                OrdinaryPipeline.getInstance(
                        Arrays.asList(new DemoPipeline("1"), new DemoPipeline("2"), new DemoPipeline("3")));
        System.out.println(pipeline.toString());
    }

    private static final class DemoPipeline extends OrdinaryPipeline<String> {

        public DemoPipeline(String name) {
            super(name);
        }

        @Override
        public void process(PipelineContext ctx, String s) {

        }
    }

}
