package cn.tedu.music.service.impl;

import cn.tedu.music.entity.*;
import cn.tedu.music.mapper.OrderMapper;
import cn.tedu.music.service.IAddressService;
import cn.tedu.music.service.ICartService;
import cn.tedu.music.service.IGoodsService;
import cn.tedu.music.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;
    @Override
    public Order create(Integer aid, Integer[] cids, String username, Integer uid) throws InsertException {
        Date now=new Date();
        Order order=new Order();
        order.setUid(uid);
        Address address=addressService.getByAid(aid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvAddress(address.getProvinceName()+address.getCityName()+address.getAreaName());
        //TODO totalPrice;
        List<CartVO> cartVOS=cartService.getByCids(cids, uid);
        Long totalPrice=0L;
        for(CartVO cartVO:cartVOS){
            Long price = cartVO.getPrice();
            Integer num = cartVO.getNum();
            totalPrice+=(price*num);
        }
        order.setTotalPrice(totalPrice);
        order.setOrderTime(now);
        order.setPayTime(null);
        order.setState(0);
        order.setCreateUser(username);
        order.setCreateTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        insertOrder(order);

        OrderItem orderItem=new OrderItem();
        orderItem.setOid(order.getOid());
        List<CartVO> cartVOList=cartService.getByCids(cids, uid);
        for (CartVO c:cartVOList){
            orderItem.setGid(c.getGid());
            orderItem.setTitle(c.getTitle());
            orderItem.setImage(c.getImage());
            orderItem.setPrice(c.getPrice());
            orderItem.setNum(c.getNum());
            orderItem.setCreateTime(now);
            orderItem.setCreateUser(username);
            orderItem.setModifiedUser(username);
            orderItem.setModifiedTime(now);
        }
        insertOrderItem(orderItem);
        return order;
    }

    private void insertOrder(Order order){
        Integer rows=orderMapper.insertOrder(order);
        if(rows!=1){
            throw new InsertException("创建订单失败!插入订单数据异常");
        }
    }

    private void insertOrderItem(OrderItem orderItem){
        Integer rows=orderMapper.insertOrderItem(orderItem);

        if(rows!=1){
            throw new InsertException("创建订单失败!插入订单数据异常");
        }
    }
}
