package cn.tedu.music.UserMapper;

import cn.tedu.music.entity.User;
import cn.tedu.music.mapper.UserMapper;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.service.ex.NullUserException;
import cn.tedu.music.service.ex.ServiceException;
import cn.tedu.music.service.ex.UpdateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestCase {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IUserService service;

    @Test
    public void testFindUserByUsername(){
        User user=userMapper.findUserByUsername("测试");
        System.err.println(user);
    }

    @Test
    public void testInsert(){
        User user=new User();
        user.setUsername("测试2号");
        user.setPassword("我是1号");
        Integer rows=userMapper.insert(user);
        System.err.println(rows);
    }

    @Test
    public void testReg(){
        try {
            User user=new User();
            user.setUsername("测试3号");
            user.setPassword("我是3号");
            service.reg(user);
            System.err.println("看看结果吧");
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getClass().getName());
        }
    }

    @Test
    public void testLogin(){
        try {
            String username="root";
            String password="9966";
            service.login(username,password);
            System.err.println("看看结果吧");
        } catch (ServiceException e) {
            System.err.println(e.getMessage());
            System.err.println(e.getClass().getName());
        }
    }
    @Test
    public void testUpdatePassword(){
        Date now=new Date();
        Integer rows=userMapper.updatePassword(7,"555555","2017-5-12",now);
        System.err.println(rows);
    }

    @Test
    public void testFindById(){
        User user=userMapper.findById(7);
        System.err.println(user);
    }

    @Test
    public void testChangePassword(){
        try{
           service.changePassword(1,"root","1234","4321");
            System.err.println("测试完了");
        }catch (ServiceException e){
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testUpdateInfo(){
        User user=new User();
        user.setUid(6);
        user.setPhone("15800380003");
        user.setEmail("158858@qq.com");
        user.setGender(0);
        Integer rows=userMapper.updateInfo(user);
        System.err.println(rows);
    }

    @Test
    public void testChangeInfo(){
        try {
            User user=new User();
            user.setUid(8);
            user.setPhone("15800380003");
            user.setEmail("158858@qq.com");
            user.setGender(1);
            service.changeInfo(user);
        } catch (NullUserException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void testUpdateAvatar(){
        Date now=new Date();
        Integer rows=userMapper.updateAvatar("这是一个头像","root",now,6);
        System.err.println(rows);
    }

    @Test
    public void testChangeAvatar(){
        try {
            String username="秦始皇";
            Integer uid=33;
            String avatar="又是一个头像";
            service.changeAvatar(uid,username,avatar);
            System.err.println("OK");
        } catch (NullUserException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }
    }
}
