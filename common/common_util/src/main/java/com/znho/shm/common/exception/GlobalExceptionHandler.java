package com.znho.shm.common.exception;

import com.znho.shm.common.result.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.fail();
    }

    @ExceptionHandler(ShmException.class)
    @ResponseBody
    public R error(ShmException e) {
        e.printStackTrace();
        return R.fail();
    }
}
