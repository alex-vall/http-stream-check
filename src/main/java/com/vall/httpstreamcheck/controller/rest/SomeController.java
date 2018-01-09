package com.vall.httpstreamcheck.controller.rest;

import com.vall.httpstreamcheck.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * Created by Vall on 02.01.2018.
 */

@Controller
public class SomeController {

    private RestService restService;

    @Autowired
    public SomeController(RestService restService) {
        this.restService = restService;
    }

    @RequestMapping("/some")
    public ResponseBodyEmitter handleRequest (@RequestParam(value = "count" , defaultValue = "1") Long count) {

        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < count; i++) {
                try {
                    emitter.send(i + " - ", MediaType.TEXT_PLAIN);
                    emitter.send(restService.generateSync(1));

                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }

}
