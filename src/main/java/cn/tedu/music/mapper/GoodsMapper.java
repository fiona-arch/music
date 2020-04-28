package cn.tedu.music.mapper;

import cn.tedu.music.entity.Goods;

import java.util.List;

/**
 * 处理商品数据的持久层接口
 */
public interface GoodsMapper {
    /**
     * 查找热销商品
     * @return 商品数据集合
     */
        List<Goods> findHotList();

    /**
     * 查找新到好货
     * @return
     */
    List<Goods> findNewArrive();

    /**
     * 查找商品详情
     * @param id
     * @return
     */
    Goods findById(Long id);

}
