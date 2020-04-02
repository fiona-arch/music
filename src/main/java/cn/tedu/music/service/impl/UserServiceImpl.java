package cn.tedu.music.service.impl;

import cn.tedu.music.entity.User;
import cn.tedu.music.mapper.UserMapper;
import cn.tedu.music.service.IUserService;
import cn.tedu.music.service.ex.NullUserException;
import cn.tedu.music.service.ex.UpdateException;
import cn.tedu.music.service.ex.UsernameDuplicateException;
import cn.tedu.music.service.ex.WrongPasswordException;
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

    @Override
    public User login(String username, String password) throws NullUserException, WrongPasswordException {
        //查找数据库中是否存在对应用户
        User user=userMapper.findUserByUsername(username);
        //不存在 抛出查无此人异常
        if(user==null){
            throw new NullUserException("登录失败!查无此人");
        }
        //isDeleted值为1 代表已删除 也抛出查无此人异常
        Integer isDeleted=user.getIsDeleted();
        if(isDeleted==1){
            throw new NullUserException("登录失败!查无此人");
        }
        //获取数据中用的盐值
        String salt=user.getSalt();
        //将数据库中的盐值和用户输入的密码进行加密
        String md5Password=getMd5Password(password,salt);
        //数据库中的密码和加密后的密码比较
        //比较结果不一致 抛出密码错误异常
        if(!user.getPassword().equals(md5Password)){
           throw new WrongPasswordException("登录失败!密码错误");
        }
        //密码盐值和删除标记是隐藏数据 不应返回给客户端
        user.setPassword(null);
        user.setSalt(null);
        user.setIsDeleted(null);
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword)throws NullUserException,WrongPasswordException, UpdateException {
        //根据id查对应的用户
        User user=userMapper.findById(uid);
        //用户为null 抛出异常
        if(user==null){
            throw new NullUserException("修改失败!用户不存在");
        }
        //isDeleted值为1 代表删除 抛出异常
        if(user.getIsDeleted()!=0){
            throw new NullUserException("修改失败!用户名存在");
        }
        //获取盐值
        String salt=user.getSalt();
        //用户提交的旧密码和盐值进行加密
        String oldMd5Password=getMd5Password(oldPassword,salt);
        //数据库中的旧密码和加密过后的新密码进行比较,比较结果不一致 抛出异常
        if(!user.getPassword().equals(oldMd5Password)){
            throw new WrongPasswordException("密码验证不通过,禁止修改");
        }
        //新密码加密
        String newMd5Password=getMd5Password(newPassword,salt);
        Date now=new Date();
        userMapper.updatePassword(uid,newMd5Password,username,now);
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
