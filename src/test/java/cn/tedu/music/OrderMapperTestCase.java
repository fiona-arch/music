package cn.tedu.music;

import cn.tedu.music.entity.Order;
import cn.tedu.music.entity.OrderItem;
import cn.tedu.music.mapper.OrderMapper;
import cn.tedu.music.service.impl.IOderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderMapperTestCase {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IOderService orderService;

    @Test
    public void testInsertOrder() {
        Order order = new Order();
        order.setUid(1);
        Integer rows = orderMapper.insertOrder(order);
        System.err.println("rows=" + rows);
    }

    @Test
    public void testInsertOrderItem() {
        OrderItem item = new OrderItem();
        item.setNum(5);
        Integer rows = orderMapper.insertOrderItem(item);
        System.err.println("rows=" + rows);
    }



    @Test
    public void create() {
        Integer aid = 20;
        Integer[] cids = {7, 9, 12, 13};
        Integer uid = 7;
        String username = "超级管理员";
        Order order = orderService.create(aid, cids,username,uid);
        System.err.println(order);
    }
}