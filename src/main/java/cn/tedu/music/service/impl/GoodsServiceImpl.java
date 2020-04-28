package cn.tedu.music.service.impl;

import cn.tedu.music.entity.Goods;
import cn.tedu.music.mapper.GoodsMapper;
import cn.tedu.music.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper mapper;
    @Override
    public List<Goods> getHotList() {
        return findHotList();
    }

    @Override
    public List<Goods> getNewArrive() {
        return findNewArrive();
    }

    @Override
    public Goods getById(Long id) {
        return findById(id);
    }

    private List<Goods> findHotList(){
        return mapper.findHotList();
    }
    private List<Goods> findNewArrive(){
        return mapper.findNewArrive();
    }
    private Goods findById(Long id){
        return mapper.findById(id);
    }
}
