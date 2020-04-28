package cn.tedu.music.service;

import cn.tedu.music.entity.Goods;

import java.util.List;

/**
 * 处理商品的业务层接口
 */
public interface IGoodsService {
    /**
     * 查找热销商品
     * @return
     */
    List<Goods> getHotList();

    /**
     * 查找新到好货
     * @return
     */
    List<Goods> getNewArrive();

    /**
     * 查找商品详情
     * @param id
     * @return
     */
    Goods getById(Long id);
}
