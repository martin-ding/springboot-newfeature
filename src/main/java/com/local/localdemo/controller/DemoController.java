package com.local.localdemo.controller;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.CorrelationIdFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@Slf4j
public class DemoController {
    class AA {
        String name;

        public String getName() {
            return name;
        }
    }
    @GetMapping("/demo")
    public String test() throws Exception {
        log.info("test");

//        CorrelationIdFormatter formatter = CorrelationIdFormatter.of("traceId(32),spanId(16)");
//        Map<String, String> mdc = Map.of("traceId", "01234567890123456789012345678901", "spanId", "0123456789012345");
//        System.out.println(formatter.format(mdc::get));


//        throw new Exception("xxx");
        AA aa = null;
        ObjectUtils.allNotNull(aa, aa.getName());
        return "xxx";
    }


}
