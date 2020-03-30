package cn.tedu.music.service;

import cn.tedu.music.entity.User;

/**
 * 处理数据业务层的接口
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户对象
     */
    void reg(User user);

}
