package com.andy.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin("*")
public class IndexController {
    @RequestMapping(value ="/api", method = RequestMethod.GET)
    String getIndexPage(){
        return "index.html";

    }
    @RequestMapping(method = RequestMethod.GET)
    String getIndexPage1(){
        return "index.html";

    }


}
