package cn.tedu.music.UserMapper;

import cn.tedu.music.entity.User;
import cn.tedu.music.mapper.UserMapper;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.service.ex.ServiceException;
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
}