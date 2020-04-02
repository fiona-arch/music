package cn.tedu.music.controller;

import cn.tedu.music.entity.User;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private IUserService service;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        service.reg(user);
        JsonResult<Void> result=new JsonResult<>(SUCCESS);
        return result;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user= service.login(username,password);
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        JsonResult<User> result=new JsonResult<>(SUCCESS,user);
        return result;
    }


    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(@RequestParam("old_password") String oldPassword,
                                           @RequestParam("new_password") String newPassword,
                                           HttpSession session){
        Integer uid= (Integer) session.getAttribute("uid");
        String username= (String) session.getAttribute("username");
        service.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(SUCCESS);
    }
}
