package cn.tedu.music.controller;

import cn.tedu.music.entity.Goods;
import cn.tedu.music.service.IGoodsService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController{
    @Autowired
    private IGoodsService service;
    @RequestMapping("hot")
    public JsonResult<List<Goods>> getHotList(){
        List<Goods> list=service.getHotList();
        return new JsonResult<>(SUCCESS,list);
    }

    @RequestMapping("new")
    public JsonResult<List<Goods>> getNewArrive(){
        List<Goods> list=service.getNewArrive();
        return new JsonResult<>(SUCCESS,list);
    }

    @GetMapping("{id}/details")
    public JsonResult<Goods> getById(@PathVariable("id") Long id){
        Goods goods=service.getById(id);
        return new JsonResult<>(SUCCESS,goods);
    }
}
