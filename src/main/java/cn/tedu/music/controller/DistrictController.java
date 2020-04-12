package cn.tedu.music.controller;

import cn.tedu.music.entity.District;
import cn.tedu.music.service.IDistrictService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService service;
    @GetMapping("/")
    public JsonResult<List<District>>  getByParent(String parent){
        List<District> list=service.getByParent(parent);
        return new JsonResult<>(SUCCESS,list);
    }
}
