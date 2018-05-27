package com.vall.httpstreamcheck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 *
 * Created by Vall on 04.01.2018.
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/viewAngular", method = RequestMethod.GET)
    public String indexAngular() {
        return "index-angular";
    }

}
