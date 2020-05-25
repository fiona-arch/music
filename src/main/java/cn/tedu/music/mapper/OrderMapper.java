package cn.tedu.music.mapper;

import cn.tedu.music.entity.Order;
import cn.tedu.music.entity.OrderItem;

public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);
}
