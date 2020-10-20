package com.lh.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.lh.springcloud.entities.CommonResult;
import com.lh.springcloud.entities.Payment;

public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(4444, "按客户自定义,global handlerException1----1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(4444, "按客户自定义,global handlerException2----2");
    }
}
