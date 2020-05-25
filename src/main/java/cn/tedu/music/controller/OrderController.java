package cn.tedu.music.controller;

import cn.tedu.music.entity.Order;
import cn.tedu.music.service.impl.IOderService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController{

    @Autowired
    private IOderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> create(Integer []cids, HttpSession session,Integer aid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Order order=orderService.create(aid,cids,username,uid);
        return new JsonResult<>(SUCCESS,order);
    }
}
