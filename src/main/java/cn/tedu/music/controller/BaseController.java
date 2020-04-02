package cn.tedu.music.controller;

import cn.tedu.music.service.ex.*;
import cn.tedu.music.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {

    /**
     * 代表用户操作成功
     */
    public static final Integer SUCCESS=2000;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>();
        result.setMessage(e.getMessage());
        if(e instanceof UsernameDuplicateException){
            result.setState(4001);
        }else if(e instanceof ServiceException){
            result.setState(4002);
        }else if(e instanceof WrongPasswordException){
            result.setState(5001);
        }else if(e instanceof NullUserException){
            result.setState(5002);
        }else if(e instanceof UpdateException){
            result.setState(6000);
        }
        return result;
    }
}
