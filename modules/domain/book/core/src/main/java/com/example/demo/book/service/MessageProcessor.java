package com.example.demo.book.service;

import com.example.demo.api.event.Event;
import com.example.demo.common.error.ErrorCode;
import com.example.demo.common.exception.PlatformException;
import com.example.demo.domain.book.model.Book;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

// Uncomment after introducing SQS
//@EnableBinding(Sink.class)
public class MessageProcessor {

    @StreamListener(target = Sink.INPUT)
    public void process(Event<String, Book> event) {

        switch (event.getEventType()) {

        default:
            String errorMessage = "Incorrect event type: " + event.getEventType() + ", expected a CREATE or DELETE event";
            throw new PlatformException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
