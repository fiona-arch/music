package cn.tedu.music.AddresMapper;

import cn.tedu.music.entity.Address;
import cn.tedu.music.mapper.AddressMapper;
import cn.tedu.music.service.IAddressService;
import cn.tedu.music.service.ex.AddressDeniedException;
import cn.tedu.music.service.ex.AddressNotFoundException;
import cn.tedu.music.service.ex.DeleteException;
import cn.tedu.music.service.ex.UpdateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class TestCase {

    @Autowired
    AddressMapper mapper;
    @Autowired
    IAddressService service;

    @Test
    public void testInsert(){
        Address address=new Address();
        address.setUid(101);
        address.setName("宫羽");
        address.setAreaName("廊州");
        address.setModifiedTime(new Date());
        address.setCreateTime(new Date());
        address.setCreateUser("林");
        address.setModifiedUser("lin");
        Integer rows=mapper.insert(address);
        System.err.println(rows);
    }

    @Test
    public void testCountByUid(){
        Integer uid=101;
        Integer count=mapper.countByUid(uid);
        System.err.println(count);
    }

    @Test
    public void testAddNew(){
        try {
            Address address=new Address();
            Integer uid=100;
            address.setUid(uid);
            String username="林";
            address.setName("精卫");
            service.addNew(address,uid,username);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFindByUid(){
        Integer uid=6;
        List<Address> list=mapper.findByUid(uid);
        System.err.println(list);
    }

    @Test
    public void testGetByUid(){
        Integer uid=6;
        List<Address> list=service.getByUid(uid);
        System.err.println(list);
    }


    @Test
    public void testUpdateNonDefault(){
        Integer uid=6;
        Integer rows=mapper.updateNonDefault(uid);
        System.err.println(rows);
    }

    @Test
    public void testFindByAid(){
        Integer aid=32;
        Address address=mapper.findByAid(aid);
        System.err.println(address.getUid());
    }

    @Test
    public void testUpdateDefault(){
        Integer aid=16;
        Integer rows=mapper.updateDefault(aid,"叶心",new Date());
        System.err.println(rows);
    }

    @Test
    public void testSetDefault(){
        try {
            Integer aid=16;
            Integer uid=9;
            String username="惢心";
            service.setDefault(aid,uid,username);
        } catch (AddressNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AddressDeniedException e) {
            System.err.println(e.getMessage());
        } catch (UpdateException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testDeleteByAid(){
        Integer aid=26;
        Integer rows=mapper.deleteByAid(aid);
        System.err.println(rows);
    }

    @Test
    public void testFindLastModifiedAddress(){
        Integer uid=9;
        Address address=mapper.findLastModifiedAddress(uid);
        System.err.println(address);
    }


    @Test
    public void testDelete(){
        try {
            Integer aid=30;
            Integer uid=9;
            String username="珍珠";
            service.delete(aid,uid,username);
        } catch (AddressNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AddressDeniedException e) {
            System.err.println(e.getMessage());
        } catch (DeleteException e) {
            System.err.println(e.getMessage());
        }
    }

}
