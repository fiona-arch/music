package cn.tedu.music.service;

import cn.tedu.music.entity.Address;
import cn.tedu.music.service.ex.*;

import java.util.List;

public interface IAddressService {
    /**
     * 用户最大地址收获数量
     */
     public static final Integer ADDRESS_MAX_COUNT=20;

    /**
     * 新增地址信息
     * @param address 收获地址资料
     * @return 1代表新增成功 否则代表新增失败
     * @throws InsertException 插入异常
     * @throws AddressCountLimitException 地址达到上限异常
     */
    void addNew(Address address,Integer uid,String username)throws InsertException,AddressCountLimitException;

    /**
     * 根据用户id查看地址列表
     * @param uid
     * @return
     */
    List<Address> getByUid(Integer uid);

    /**
     * 将地址设为默认
     * @param aid 用户选择的地址
     * @param uid 用户地址
     * @param username 用户名
     * @throws AddressNotFoundException 地址不存在
     * @throws AddressDeniedException 拒绝修改他人信息
     * @throws UpdateException 更新失败
     */
    void setDefault(Integer aid,Integer uid,String username) throws AddressNotFoundException,AddressDeniedException, UpdateException;

    /**
     * 删除地址
     * @param aid
     * @param uid
     * @param username
     * @throws AddressNotFoundException
     * @throws AddressDeniedException
     * @throws DeleteException 删除失败
     */
    void delete(Integer aid,Integer uid,String username)throws AddressNotFoundException,AddressDeniedException,DeleteException;
}
