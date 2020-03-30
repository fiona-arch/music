package cn.tedu.music.controller;

import cn.tedu.music.service.ex.ServiceException;
import cn.tedu.music.service.ex.UsernameDuplicateException;
import cn.tedu.music.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>();
        result.setMessage(e.getMessage());
        if(e instanceof UsernameDuplicateException){
            result.setState(2);
        }else if(e instanceof ServiceException){
            result.setState(3);
        }
        return result;
    }
}
