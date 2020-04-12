package cn.tedu.music.controller;

import cn.tedu.music.controller.ex.*;
import cn.tedu.music.entity.User;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        Integer uid= getUidFromSession(session);
        String username= getUsernameFromSession(session);
        service.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("get_info")
    public JsonResult<User> getInfo(HttpSession session){
        Integer uid=getUidFromSession(session);
        User user=service.getById(uid);
        return new JsonResult<User>(SUCCESS,user);
    }

    @RequestMapping("change_info")
    public JsonResult<Void> changeInfo(HttpSession session,User user){
        Integer uid= getUidFromSession(session);
        String username= getUsernameFromSession(session);
        user.setUid(uid);
        user.setUsername(username);
        service.changeInfo(user);
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            throw new FileEmptyException("上传头像为空,请重新选择");
        }
        if(file.getSize()>MAX_AVATAR_SIZE){
            throw new FileSizeException("您选择的文件超出限制!请重新选择");
        }
        //文件类型

        String contentType=file.getContentType();
        if (!AVATAR_CONTENT_TYPE.contains(contentType)) {
            throw new FileTypeException("上传文件的类型不符和要求,请重新选择");
        }

        //文件夹
        String dirPath=request.getServletContext().getRealPath(AVATAR_DIR);
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //文件名
        String originalFilename=file.getOriginalFilename();
        String suffix="";
        int index=originalFilename.lastIndexOf(".");
        if(index!=-1){
            suffix=originalFilename.substring(index);
        }
        String filename= UUID.randomUUID().toString()+suffix;

        //文件保存在服务器
        File dest=new File(dir,filename);
        try {
            file.transferTo(dest);
        }catch(IllegalStateException e){
            throw new FileUploadStateException("上传失败!您选择的文件可能被移动");
        }catch (IOException e) {
           throw new FileUploadIOException("上传失败!读写文件发生了未知错误");
        }
        String avatar="/"+ AVATAR_DIR+"/"+filename;
        Integer uid=getUidFromSession(request.getSession());
        String username=getUsernameFromSession(request.getSession());
        service.changeAvatar(uid,username,avatar);
        return new JsonResult<>(SUCCESS,avatar);
    }
}
