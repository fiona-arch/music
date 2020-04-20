package cn.tedu.music.controller;

import cn.tedu.music.entity.Address;
import cn.tedu.music.service.IAddressService;
import cn.tedu.music.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService service;

    @RequestMapping("add_new")
    public JsonResult<Void> addNew(Address address, HttpSession session){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.addNew(address,uid,username);
        return new JsonResult<>(SUCCESS);
    }

    @GetMapping("/")
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getUidFromSession(session);
        List<Address> list=service.getByUid(uid);
        return new JsonResult<>(SUCCESS,list);
    }

    @GetMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(HttpSession session,@PathVariable("aid") Integer aid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.setDefault(aid,uid,username);
        return new JsonResult<>(SUCCESS);
    }
    @GetMapping("{aid}/delete")
    public JsonResult<Void> delete(HttpSession session,@PathVariable("aid") Integer aid){
        Integer uid=getUidFromSession(session);
        String username=getUsernameFromSession(session);
        service.delete(aid,uid,username);
        return new JsonResult<>(SUCCESS);
    }

}
