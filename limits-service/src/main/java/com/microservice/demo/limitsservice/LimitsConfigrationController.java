package com.microservice.demo.limitsservice;

import com.microservice.demo.limitsservice.bean.LimitsConfigration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigrationController {
    @Autowired
    private Configration configration;

    @GetMapping("/limits")
    public LimitsConfigration retriveLimitsFromConfigration(){
        return new LimitsConfigration(configration.getMaximum(),configration.getMinimum());
    }
}
