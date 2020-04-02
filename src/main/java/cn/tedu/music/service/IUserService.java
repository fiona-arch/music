package cn.tedu.music.service;

import cn.tedu.music.entity.User;
import cn.tedu.music.service.ex.*;

/**
 * 处理数据业务层的接口
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户对象
     */
    void reg(User user) throws InsertException, UsernameDuplicateException;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return 用户对象
     * @throws NullUserException 查无此人异常
     * @throws WrongPasswordException 密码错误异常
     */
    User login(String username,String password) throws NullUserException, WrongPasswordException;

    /**
     * 修改密码
     * @param uid
     * @param username
     * @param oldPassword
     * @param newPassword
     */
    void changePassword(Integer uid,String username,String oldPassword,String newPassword)throws NullUserException,WrongPasswordException, UpdateException;
}
