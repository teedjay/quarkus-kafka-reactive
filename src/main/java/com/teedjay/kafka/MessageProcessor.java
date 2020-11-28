package com.teedjay.kafka;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class MessageProcessor {

    AtomicLong count = new AtomicLong();

    @Incoming("test-input")
    @Outgoing("test-output")
    @Broadcast
    @Blocking
    public String process(String data) {
        String value = "" + count.incrementAndGet() + ":" + data;
        System.out.println(value);
        return value;
    }

}
