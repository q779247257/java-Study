package com.qfedu.convert;

import org.apache.ibatis.binding.BindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@ControllerAdvice
public class CommonException {

    // 异常处理方法
    @ExceptionHandler(ConstraintViolationException.class)
    public String constraintViolationExceptione(ConstraintViolationException e){
        Iterator<ConstraintViolation<?>>
                iterator = e.getConstraintViolations().iterator();
        String message = null;
        if (iterator.hasNext()) {
            message = iterator.next().getMessage();
            System.out.println(message);
        }
        return "erorr";
    }

    @ExceptionHandler(BindingException.class)
    public String bindException(BindingException e){

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exception(Exception e){
        System.out.println(e.getMessage());
        return "error";

    }
}
