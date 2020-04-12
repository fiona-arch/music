package cn.tedu.music.service;

import cn.tedu.music.entity.Address;
import cn.tedu.music.service.ex.AddressCountLimitException;
import cn.tedu.music.service.ex.InsertException;

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
}
