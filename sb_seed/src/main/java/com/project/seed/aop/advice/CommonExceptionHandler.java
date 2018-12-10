package com.project.seed.aop.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用@ControllerAdvice和@ExceptionHandler定义一个统一异常处理类
 *
 * @ControllerAdvice：控制器增强，使@ExceptionHandler、@InitBinder、@ModelAttribute注解的方法应用到所有的 @RequestMapping注解的方法。
 *
  @ExceptionHandler：异常处理器，此注解的作用是当出现其定义的异常时进行处理的方法

 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
 
    /**
     *  拦截Exception类的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(Exception e){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "9999");
        result.put("respMsg", e.getMessage());
        //正常开发中，可创建一个统一响应实体，如CommonResp
        return result; 
    }

    /**
     * 对于返回页面时，返回ModelAndView即可，如
     * 由于工作中都是才有前后端分离开发模式，所以一般上都没有直接返回资源页的需求了，
     * 一般上都是返回固定的响应格式，如respCode、respMsg、data，
     * 前端通过判断respCode的值进行业务判断，是弹窗还是跳转页面。
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());

        String DEFAULT_ERROR_VIEW ="error_page";

        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    /**
     * 大家看到在校验不通过时，返回的异常信息是不友好的，此时可利用统一异常处理，
     * 对校验异常进行特殊处理，特别说明下，对于异常处理类,
     * 共有以下几种情况(被@RequestBody和@RequestParam注解的请求实体，校验异常类是不同的)
     * @param ex
     * @return
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleBindException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }

    @ExceptionHandler(BindException.class)
    public Map<String,Object> handleBindException(BindException ex) {
        //校验 除了 requestbody 注解方式的参数校验 对应的 bindingresult 为 BeanPropertyBindingResult
        FieldError fieldError = ex.getBindingResult().getFieldError();
        log.info("必填校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("respCode", "01002");
        result.put("respMsg", fieldError.getDefaultMessage());
        return result;
    }


    /**
     *
     *
     */
}