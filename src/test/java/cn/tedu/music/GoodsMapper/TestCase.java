package cn.tedu.music.GoodsMapper;

import cn.tedu.music.entity.Goods;
import cn.tedu.music.mapper.GoodsMapper;
import cn.tedu.music.service.IGoodsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCase {
    @Autowired
    GoodsMapper mapper;

    @Autowired
    IGoodsService service;
    @Test
    public void testFindHotList(){
        List<Goods> list=mapper.findHotList();
        for(Goods item:list){
            System.err.println(item);
        }
    }
    @Test
    public void testGetHotList(){
        List<Goods> list=service.getHotList();
        for(Goods item:list){
            System.err.println(item);
        }
    }

    @Test
    public void testFindNewArrive(){
        List<Goods> list=mapper.findNewArrive();
        for(Goods item:list){
            System.err.println(item);
        }
    }

    @Test
    public void testGetNewArrive(){
        List<Goods> list=service.getNewArrive();
        for(Goods item:list){
            System.err.println(item);
        }
    }

    @Test
    public void testFindById(){
        Long id=100000403L;
        Goods goods=mapper.findById(id);
        System.err.println(goods);
    }

    @Test
    public void testGetById(){
        Long id=100000403L;
        Goods goods=service.getById(id);
        System.err.println(goods);
    }
}
