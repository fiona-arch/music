package cn.tedu.music.mapper;

import cn.tedu.music.entity.Cart;
import cn.tedu.music.entity.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入新的购物车数据
     * @param cart 购物车数据
     * @return 1代表新增成功  否则代表新增失败
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车中的商品数量
     * @param cid 购物车id
     * @param num 购物车商品数量
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 1代表修改成功  否则修改失败
     */
    Integer updateNum(@Param("cid") Integer cid, @Param("num")Integer num,
                      @Param("modifiedUser")String modifiedUser, @Param("modifiedTime")Date modifiedTime);

    /**
     * 根据uid和gid查询购物车中商品数据
     * @param uid 用户id
     * @param gid 商品id
     * @return 返回匹配的购物车数据
     */
    Cart findByUidAndGid(@Param("uid")Integer uid,@Param("gid")Long gid);

    /**
     *
     * 查询购物车数据
     * @param cid 购物车id
     * @return 包含购物车中商品数量 用户id
     */
    Cart findByCid(Integer cid);

    /**
     * 查询订单列表
     * @param cids
     * @return 订单列表
     */
    List<CartVO> findByCids(Integer []cids);
}
