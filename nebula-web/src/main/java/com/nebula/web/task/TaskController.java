package com.nebula.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping("/task")
    public String task() throws ExecutionException, InterruptedException {
        asyncTask.dealNoReturnTask();
        Future<String> f = asyncTask.dealHaveReturnTask(5);
        log.info("主线程执行finished");
        log.info(f.get());
        return "ok";
    }

    @GetMapping("/task1")
    public String task1() throws ExecutionException, InterruptedException {
        publisher.publishEvent(new NoticeEvent("大家好啊"));
        return "ok";
    }

}
