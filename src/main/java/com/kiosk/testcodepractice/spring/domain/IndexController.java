package com.kiosk.testcodepractice.spring.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String createOrder(){
        return "index2";
    }
}
