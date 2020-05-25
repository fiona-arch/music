package cn.tedu.music.controller;

import cn.tedu.music.controller.ex.*;
import cn.tedu.music.service.ex.*;
import cn.tedu.music.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class BaseController {
    /**
     * 从session中获取用户id
     * @param session
     * @return
     */
    protected Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 从session中获取用户名
     * @param session
     * @return
     */
    protected String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }

    /**
     * 代表用户操作成功
     */
    public static final Integer SUCCESS=2000;

    public static final Integer MAX_AVATAR_SIZE=2*1024*1024;
    public static final String AVATAR_DIR="upload";
    public static final List<String> AVATAR_CONTENT_TYPE=new ArrayList<>();
    static {
        AVATAR_CONTENT_TYPE.add("image/jpeg");
        AVATAR_CONTENT_TYPE.add("image/gif");
        AVATAR_CONTENT_TYPE.add("image/png");
    }


    @ExceptionHandler({ServiceException.class,FileUploadException.class})
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
        }else if(e instanceof FileEmptyException){
            result.setState(6001);
        }else if(e instanceof FileSizeException){
            result.setState(6002);
        }else if(e instanceof FileTypeException){
            result.setState(6003);
        }else if(e instanceof FileUploadStateException){
            result.setState(6004);
        }else if(e instanceof FileUploadIOException){
            result.setState(6005);
        }else if(e instanceof AddressNotFoundException){
            result.setState(6006);
        }else if(e instanceof AccessDeniedException){
            result.setState(6007);
        }else if(e instanceof DeleteException){
            result.setState(6008);
        }
        return result;
    }
}
