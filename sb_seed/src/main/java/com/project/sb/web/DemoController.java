package com.project.sb.web;

import com.project.sb.aop.valid.DemoReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author sml
 * created on  2018/11/15
 */
@RestController
@Api(value = "demo",tags="demo_module")
public class DemoController {

    @GetMapping("/demo/valid")
    public String demoValid(@Valid DemoReq req) {
        return req.getCode() + "," + req.getName();
    }

}
