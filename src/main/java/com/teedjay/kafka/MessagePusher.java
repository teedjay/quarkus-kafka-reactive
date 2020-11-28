package com.teedjay.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.IntStream;

@Path("/push")
public class MessagePusher {

    @Inject
    @Channel("pusher")
    Emitter<String> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String push() {
        int count = 9;
        IntStream.range(0, count).forEach(i -> emitter.send("[pusher:" + i + "]").toCompletableFuture().join());
        return "Just sent %d messages%n".formatted(count);
    }

}
