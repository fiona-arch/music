package cn.tedu.music.controller;

import cn.tedu.music.entity.User;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.service.ex.InsertException;
import cn.tedu.music.service.ex.UsernameDuplicateException;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService service;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        JsonResult<Void> result=new JsonResult<>();
        service.reg(user);
        result.setState(1);
        return result;
    }
}
