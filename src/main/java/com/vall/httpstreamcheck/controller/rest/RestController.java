package com.vall.httpstreamcheck.controller.rest;

import com.vall.httpstreamcheck.model.RestResponse;
import com.vall.httpstreamcheck.service.RestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 *
 * Created by Vall on 02.01.2018.
 */

@Controller
@RequestMapping("/regular")
public class RestController {

    private RestService restService;

    private final static Log log = LogFactory.getLog(RestController.class);

    @Autowired
    public RestController(RestService restService) {
        this.restService = restService;
    }

    @ResponseBody
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public List<RestResponse> generate(@RequestParam(value = "count" , defaultValue = "1") Long count) {

        Assert.isTrue(count > 0, "count should > 0");

        return restService.generateSync(count);
    }

    @RequestMapping(value = "/generateStream")
    public ResponseBodyEmitter generateStream(@RequestParam(value = "count" , defaultValue = "1") Long count) {

        Assert.isTrue(count > 0, "count should > 0");

//        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        final ResponseBodyEmitter emitter = new SseEmitter(-1L);

        restService.generateAsync(emitter, count);

        log.info("Return emmiter from controller");

        return emitter;
    }


}
