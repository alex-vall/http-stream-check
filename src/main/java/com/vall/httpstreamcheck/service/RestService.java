package com.vall.httpstreamcheck.service;

import com.vall.httpstreamcheck.model.RestResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 *
 * Created by Vall on 02.01.2018.
 */

@Service
public class RestService {

    private AtomicLong id = new AtomicLong(1000);
    private static final Log log = LogFactory.getLog(RestService.class);

    public List<RestResponse> generateSync(long count) {
        return LongStream.range(0, count)
                .map(i -> id.getAndIncrement())
                .mapToObj(this::generate)
        .collect(Collectors.toList());
    }

    @Async
    public CompletableFuture<ResponseBodyEmitter> generateAsync(final ResponseBodyEmitter responseBodyEmitter, long count) {
        LongStream.range(0, count)
                .map(i -> id.getAndIncrement())
                .mapToObj(this::generate)
                .forEach(i -> {
                    try {
                        responseBodyEmitter.send(i, MediaType.APPLICATION_JSON);
                    } catch (IOException e) {
                        e.printStackTrace();
                        responseBodyEmitter.completeWithError(e);
                    }
                });

        responseBodyEmitter.complete();
        return CompletableFuture.completedFuture(responseBodyEmitter);
    }

    private RestResponse generate(long id) {
        try {
            log.info(String.format("Generate with id[%d]", id));
            Thread.sleep(500);
        } catch (InterruptedException ignored) {

        }
        return new RestResponse(id, String.format("Fake payload for %d", id));
    }

}
