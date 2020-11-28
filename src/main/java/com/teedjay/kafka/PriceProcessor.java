package com.teedjay.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class PriceProcessor {

    @Inject
    @Channel("test-processor-output")
    Emitter<String> emitter;

    AtomicLong count = new AtomicLong();

    @Incoming("test-processor-input")
    public CompletionStage<Void> process(Message<String> data) {
        String value = "" + count.incrementAndGet() + ":" + data.getPayload();
        System.out.println(value);
        return emitter.send(value);
    }

}
