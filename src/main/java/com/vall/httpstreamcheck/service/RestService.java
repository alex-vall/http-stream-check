package com.vall.httpstreamcheck.service;

import com.vall.httpstreamcheck.model.RestResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;
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
    public void generateAsync(final ResponseBodyEmitter responseBodyEmitter, long count) {
        try {
            for (int i = 0; i < count; i++) {
                final RestResponse restResponse = generate(id.getAndIncrement());
                responseBodyEmitter.send(restResponse, MediaType.APPLICATION_JSON);
            }
            responseBodyEmitter.complete();
        } catch (IOException e) {
            log.error("Catch called with e: ", e);
//            responseBodyEmitter.completeWithError(e);
            responseBodyEmitter.complete();
        }
    }

    public void getRestResponseItems(long count, final ResponseBodyEmitter responseBodyEmitter) {

        final Flux<RestResponse> flux = Flux.range(0, Math.toIntExact(count))
                .map(i -> generate(id.getAndIncrement()))
                .doOnComplete(responseBodyEmitter::complete);

//        flux.toStream().forEach(i -> emmitResponse(responseBodyEmitter, i));
        flux.subscribe(i -> emmitResponse(responseBodyEmitter, i));
    }

    private void emmitResponse(ResponseBodyEmitter emitter, RestResponse response) {

        try {
            emitter.send(response);
        } catch (IOException e) {
            log.error("Send object failed: ", e);
            emitter.completeWithError(e);
        }
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
