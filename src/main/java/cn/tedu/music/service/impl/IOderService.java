package cn.tedu.music.service.impl;

import cn.tedu.music.entity.Order;
import cn.tedu.music.service.ex.InsertException;

public interface IOderService {
    /**
     * 创建订单
     * @param aid 地址id
     * @param cids 购物车勾选的id
     * @param username 用户名
     * @param uid 用户id
     * @return 创建的订单
     * @throws InsertException
     */
    Order create(Integer aid,Integer []cids,String username,Integer uid) throws InsertException;



}
