package com.nebula.web.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

@Slf4j
public class NoticeEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param message the object on which the event initially occurred (never {@code null})
     */
    public NoticeEvent(String message) {
        super(message);
        this.message = message;
        log.info("add event success! message: {}", message);
    }

    public String getMessage() {
        return message;
    }
}
