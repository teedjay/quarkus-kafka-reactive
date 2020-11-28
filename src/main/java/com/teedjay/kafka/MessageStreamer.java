package com.teedjay.kafka;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class MessageStreamer {

    AtomicInteger count = new AtomicInteger();

    @Incoming("stream")
    @Outgoing("sse")
    @OnOverflow(OnOverflow.Strategy.LATEST)
    public String process(String data) {
        String value = "[stream:" + count.incrementAndGet() + "] = " + data;
        return value;
    }

}
