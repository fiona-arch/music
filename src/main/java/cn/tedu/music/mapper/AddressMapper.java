package cn.tedu.music.mapper;

import cn.tedu.music.entity.Address;

import java.util.List;

public interface AddressMapper {
    /**
     * 查询当前用户有多少条地址数据
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    /**
     * 新增地址数据
     * @param address
     * @return
     */
    Integer insert(Address address);

    /**
     * 查看地址列表
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);
}
