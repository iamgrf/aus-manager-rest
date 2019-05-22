package com.aus.interceptor;

import com.aus.util.ExceptionTraceUtil;
import com.aus.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public java.util.Map<String, Object> allException(HttpServletRequest request, Exception exception){
        LOGGER.error("服务异常-> " + ExceptionTraceUtil.getTrace(exception));
        return MsgUtil.fail("服务异常");
    }

    /**
     * 参数异常
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public java.util.Map<String, Object> httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException exception){
        LOGGER.error("参数格式错误-> " + exception);
        return MsgUtil.fail("参数格式错误");
    }

    /**
     * 参数不合法
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public java.util.Map<String, Object> httpMessageNotReadableException(HttpServletRequest request, MethodArgumentNotValidException exception){
        LOGGER.error("参数不合法-> " + exception);
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        return MsgUtil.fail(fieldErrors.get(0).getDefaultMessage());
    }

}
