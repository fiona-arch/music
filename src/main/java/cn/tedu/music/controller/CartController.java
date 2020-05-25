package cn.tedu.music.controller;

import cn.tedu.music.entity.Cart;
import cn.tedu.music.entity.CartVO;
import cn.tedu.music.service.ICartService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/carts")
@RestController
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;

    @PostMapping("/add_to_cart")
    public JsonResult<Void> addtoCart(HttpSession session, Cart cart){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        cartService.addToCart(cart,uid,username);
        return new JsonResult<>(SUCCESS);
    }

    @PostMapping("/add_num")
    public JsonResult<Integer> addNum(Integer cid,HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        Integer num=cartService.addNum(cid,uid,username);
        return new JsonResult<>(SUCCESS,num);
    }

    @GetMapping("/get_by_cids")
    public JsonResult<List<CartVO>> getByCids(Integer []cids,HttpSession session){
        Integer uid=getUidFromSession(session);
        List<CartVO> cartVOS=cartService.getByCids(cids,uid);
        return new JsonResult<>(SUCCESS,cartVOS);
    }
 }
