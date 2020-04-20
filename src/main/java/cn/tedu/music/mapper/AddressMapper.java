package cn.tedu.music.mapper;

import cn.tedu.music.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {

    /**
     * 新增地址数据
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 根据地址id删除地址
     * @param aid
     * @return 1代表删除成功  否则代表删除失败
     */
    Integer deleteByAid(Integer aid);

    /**
     * 将该用户所有的地址设为非默认
     * @param uid
     * @return 受影响的行数  大于0即操作成功  否则即操作失败
     */
    Integer updateNonDefault(Integer uid);


    /**
     * 将用户选定的地址设为默认
     * @param aid
     * @return 返回1代表操作成功  否则操作失败
     */
    Integer updateDefault(Integer aid, @Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);




    /**
     * 查询当前用户有多少条地址数据
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);


    /**
     * 查看地址列表
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);


    /**
     * 根据地址id查询该地址是否为该用户数据
     * @param aid
     * @return 地址信息 含用户id  isDefault
     */
    Address findByAid(Integer aid);


    /**
     * 查询最新修改的地址id
     * @param uid 用户id
     * @return 最新修改 的地址
     */
    Address findLastModifiedAddress(Integer uid);

}
