package com.nebula.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NoticeListener implements ApplicationListener<NoticeEvent> {

    @Async
    @Override
    public void onApplicationEvent(NoticeEvent event) {
        log.info("listener get event, sleep 2 second...");
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        log.info("event message is : {}", event.getMessage());
    }

}
