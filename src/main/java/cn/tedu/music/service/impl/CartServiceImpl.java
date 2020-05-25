package cn.tedu.music.service.impl;

import cn.tedu.music.entity.Cart;
import cn.tedu.music.entity.CartVO;
import cn.tedu.music.mapper.CartMapper;
import cn.tedu.music.service.ICartService;
import cn.tedu.music.service.ex.AccessDeniedException;
import cn.tedu.music.service.ex.CartNotFoundException;
import cn.tedu.music.service.ex.InsertException;
import cn.tedu.music.service.ex.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 处理购物车数据的业务层实现类
 */
@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper mapper;


    @Override
    public void addToCart(Cart cart, Integer uid, String username) throws UpdateException, InsertException {
        //根据cid和uid查询购物车商品是否存在
        Cart result=findByUidAndGid(uid,cart.getGid());
        //不存在插入新数据
        Date now=new Date();
        if(result==null){
            cart.setUid(uid);
            cart.setCreateUser(username);
            cart.setCreateTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            insert(cart);
            return;
        }
        //一致增加商品数量
        Integer cid=result.getCid();
        Integer num=result.getNum()+cart.getNum();
        updateNum(cid,username,now,num);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) throws AccessDeniedException, CartNotFoundException, UpdateException {
        //查询购物车数据
        Cart cart=findByCid(cid);
        //不存在  抛异常
        if(cart==null){
            throw new CartNotFoundException("商品数量增加失败!购物车数据不存在");
        }
        //检查数据归属
        if(!cart.getUid().equals(uid)){
            throw new AccessDeniedException("商品数量增加失败!不能操作他人数据");
        }
        //商品数量加一
        Integer oldNum=cart.getNum();
        updateNum(cid,username,new Date(),oldNum+1);
        return oldNum+1;
    }

    @Override
    public List<CartVO> getByCids(Integer[] cids,Integer uid) {
        List<CartVO> cartVOS=findByCids(cids);
        Iterator<CartVO> its=cartVOS.iterator();
        while(its.hasNext()){
            if(!its.next().getUid().equals(uid)){
                its.remove();
            }
        }
        return cartVOS;
    }

    private void insert(Cart cart){
        Integer rows=mapper.insert(cart);
        if(rows!=1){
            throw new InsertException("商品添加购物车失败!发生了位置错误");
        }
    }

    private void updateNum(Integer cid, String modifiedUser, Date modifiedTime,Integer num){
        Integer rows=mapper.updateNum(cid,num,modifiedUser,modifiedTime);
        if(rows!=1){
            throw new UpdateException("修改购物车商品数量失败,发生了未知错误");
        }
    }

    private Cart findByUidAndGid(Integer uid,Long gid){
        return mapper.findByUidAndGid(uid,gid);
    }

    private Cart findByCid(Integer cid){
        return mapper.findByCid(cid);
    }

    private List<CartVO> findByCids(Integer []cids){
        return mapper.findByCids(cids);
    }
}
