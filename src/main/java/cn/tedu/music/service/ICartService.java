package cn.tedu.music.service;
/**
 * 处理购物车的业务层接口
 */

import cn.tedu.music.entity.Cart;
import cn.tedu.music.entity.CartVO;
import cn.tedu.music.service.ex.AccessDeniedException;
import cn.tedu.music.service.ex.CartNotFoundException;
import cn.tedu.music.service.ex.InsertException;
import cn.tedu.music.service.ex.UpdateException;

import java.util.List;

public interface ICartService {
    /**
     * 商品添加到购物车
     * @param cart 商品信息
     * @param uid 用户id
     * @param username 用户名
     * @throws AccessDeniedException 拒绝访问他人数据
     * @throws UpdateException 更新异常
     * @throws InsertException 插入异常
     */
    void addToCart(Cart cart,Integer uid,String username) throws UpdateException, InsertException;

    /**
     * 商品购物车增加数量
     * @param cid 购物车id
     * @param uid 用户Id
     * @param username 用户名
     * @throws AccessDeniedException
     * @throws CartNotFoundException
     * @throws UpdateException
     */
    Integer addNum(Integer cid,Integer uid,String username) throws AccessDeniedException, CartNotFoundException,UpdateException;

    /**
     * 显示订单列表
     * @param cids
     * @return
     */
    List<CartVO> getByCids(Integer []cids,Integer uid);
}
