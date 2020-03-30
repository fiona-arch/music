package cn.tedu.music.service.impl;

import cn.tedu.music.entity.User;
import cn.tedu.music.mapper.UserMapper;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.service.ex.UsernameDuplicateException;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result=userMapper.findUserByUsername(username);
        if(result!=null){
            throw new UsernameDuplicateException("您尝试的用户名已被注册,请更换用户名");
        }
        System.err.println("reg()->password:"+user.getPassword());
        //得到盐值
        String salt=UUID.randomUUID().toString().toUpperCase();
        System.err.println("reg()->salt:"+salt);
        // 加密密码
        String md5Password=getMd5Password(user.getPassword(),salt);
        System.err.println("reg()->md5Password:"+md5Password);
        //盐值和密码封装进User对象中
        user.setSalt(salt);
        user.setPassword(md5Password);
        user.setIsDeleted(0);
        //封装日志数据
        user.setCreateUser(username);
        Date date=new Date();
        user.setCreateTime(date);
        user.setModifiesUser(username);
        user.setModifiedTime(date);

        //插入用户对象
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new RuntimeException("出现未知错误!注册失败!请联系系统管理员");
        }
    }

    /**
     * 对密码进行加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密码
     */
    private String getMd5Password(String password,String salt){
        //规则 对password + salt 进行三重加密
        String str=password+salt;
        for(int i=0;i<3;i++){
            str=DigestUtils.md5DigestAsHex(str.getBytes());
        }
        return str;
    }
}
