package cn.tedu.music.CartMapper;

import cn.tedu.music.entity.Cart;
import cn.tedu.music.entity.CartVO;
import cn.tedu.music.mapper.CartMapper;
import cn.tedu.music.service.ICartService;
import cn.tedu.music.service.ex.AccessDeniedException;
import cn.tedu.music.service.ex.CartNotFoundException;
import cn.tedu.music.service.ex.InsertException;
import cn.tedu.music.service.ex.UpdateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestCase {
    @Autowired
    CartMapper mapper;

    @Autowired
    ICartService service;

    @Test
    public void testInsert() {
        Cart cart = new Cart();
        cart.setCid(2111);
        cart.setUid(10000);
        cart.setGid(12121212L);
        cart.setNum(1);
        Integer rows = mapper.insert(cart);
        System.err.println(rows);
    }

    @Test
    public void testUpdateNum() {
        Integer cid = 1;
        Integer num = 10;
        String username = "充电器";
        Integer rows = mapper.updateNum(cid, num, username, new Date());
        System.err.println(rows);
    }

    @Test
    public void testFindByUidAndGid() {
        Integer uid = 10000;
        Long gid = 12121212L;
        Cart cart = mapper.findByUidAndGid(uid, gid);
        System.err.println(cart);
    }

    @Test
    public void testAddtToCart() {
        try {
            Integer uid = 1222;
            Cart cart = new Cart();
            cart.setNum(1);
            cart.setGid(123456789L);
            String username = "空调压缩机";
            service.addToCart(cart, uid, username);
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        } catch (InsertException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCid(){
        Integer cid=1;
        Cart cart=mapper.findByCid(cid);
        System.err.println(cart);
    }

    @Test
    public void testAddNum(){
        try {
            Integer cid=2;
            System.err.println(mapper.findByCid(cid));
            Integer uid=1222;
            Integer num=service.addNum(cid,uid,"沈腾");
            System.err.println(num);
        } catch (AccessDeniedException e) {
            System.err.println(e.getMessage());
        } catch (CartNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testFindByCids(){
        Integer []cids={1,2,3};
        List<CartVO> cartVOS=mapper.findByCids(cids);
        for (CartVO c:cartVOS){
            System.err.println(c);
        }
    }

    @Test
    public void testGetByCids(){
        Integer []cids={1,2,3,10};
        List<CartVO> cartVOS=service.getByCids(cids,6);
        for (CartVO c:cartVOS){
            System.err.println(c);
        }
    }
}