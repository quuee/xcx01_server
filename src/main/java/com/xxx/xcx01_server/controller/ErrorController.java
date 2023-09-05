package com.xxx.xcx01_server.controller;

import com.xxx.xcx01_server.common.exception.ErrorCode;
import com.xxx.xcx01_server.common.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ErrorController {

    @RequestMapping("/error/throw")
    public Result error1(){
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
