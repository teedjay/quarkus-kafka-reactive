package com.teedjay.kafka;

import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class MessageProcessor {

    AtomicLong count = new AtomicLong();
    Random random = new Random();

    @Incoming("test-input")
    @Outgoing("test-output")
    @Blocking
    public String process(String data) {
        String value = "[processor:" + count.incrementAndGet() + "]:" + data;
        System.out.println(value);
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            System.out.println("wait failed : " + e.getMessage());
        }
        return value;
    }

}
