package com.teedjay.kafka;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class MessageGenerator {

    private Random random = new Random();
    private AtomicLong count = new AtomicLong();

    @Outgoing("generator")
    public Multi<String> generate() {
        return Multi.createFrom().ticks().every(Duration.ofMillis(1000))
                .onOverflow().drop()
                .map(tick -> "" + count.incrementAndGet() + " => " + random.nextInt(100));
    }

}
