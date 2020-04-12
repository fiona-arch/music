package cn.tedu.music.AddresMapper;

import cn.tedu.music.entity.Address;
import cn.tedu.music.mapper.AddressMapper;
import cn.tedu.music.service.IAddressService;
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

}
