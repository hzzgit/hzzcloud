package com.hzz.hzzcloud.config;

import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonMessage processValidException(MethodArgumentNotValidException ex){
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String errname="";
        for (ObjectError allError : allErrors) {
            if(allError instanceof FieldError){
                FieldError fieldError= (FieldError) allError;
                errname+= "传入的参数["+fieldError.getField()+"],异常原因["+fieldError.getDefaultMessage()+"];";
            }else{
                errname+=allError.toString()+";";
            }
        }
        return new JsonMessage(false,errname);
    }

}