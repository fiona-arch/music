package cn.tedu.music.mapper;

import cn.tedu.music.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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

    /**
     * 根据ID查找用户信息  包括密码 盐值 是否标记删除
     * @param uid
     * @return
     */
    User findById(Integer uid);

    /**
     * 修改密码和日志
     * @param uid 用户id
     * @param password 新密码
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updatePassword(@Param("uid") Integer uid,
                           @Param("password") String password,
                           @Param("modifiedUser") String modifiedUser,
                           @Param("modifiedTime")Date modifiedTime);

    /**
     * 修改用户信息 包含phone,email,gender
     * @param user
     * @return 1代表修改成功  否则代表修改失败
     */
    Integer updateInfo(User user);

    /**
     * 根据id修改头像
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return 1代表修改成功  0代表修改失败
     */
    Integer updateAvatar(@Param("avatar") String avatar,@Param("modifiedUser") String modifiedUser,
                         @Param("modifiedTime") Date modifiedTime,@Param("uid")Integer uid);
}
