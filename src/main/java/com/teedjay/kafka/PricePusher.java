package com.teedjay.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/push")
public class PricePusher {

    @Inject
    @Channel("test-pusher")
    Emitter<String> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String push() {
        for(int i=0; i<1000; i++) {
            emitter.send("nummer=" + i).toCompletableFuture().join();
        }
        return "sendt 1000";
    }

}
