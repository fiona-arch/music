package cn.tedu.music.mapper;

import cn.tedu.music.entity.User;

/**
 * 处理用户数据的持久层接口
 */
public interface UserMapper {
    /**
     * 根据用户名查找用户数据
     * @param username 用户名
     * @return 返回匹配的用户数据,无匹配的数据返回null
     */
    User findUserByUsername(String username);


    /**
     * 插入新数据
     * @param user 用户对象
     * @return  1代表插入成功 否则插入失败
     */
    Integer insert(User user);
}
