package cn.tedu.music.DistrictMapper;

import cn.tedu.music.entity.District;
import cn.tedu.music.mapper.DistrictMapper;
import cn.tedu.music.service.IDistrictService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestCase {
    @Autowired
    DistrictMapper mapper;
    @Autowired
    IDistrictService service;

    @Test
    public void testSelectByParent(){
        String parent="110100";
        List<District> list=mapper.findByParent(parent);
        System.err.println(list);
    }

    @Test
    public void testFindByCode(){
        String code="130000";
        District dis=mapper.findByCode(code);
        System.err.println(dis);
    }


    @Test
    public void testGetByCode(){
        String code="110111";
        District dis=service.getByCode(code);
        System.err.println(dis);
    }
}
