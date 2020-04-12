package cn.tedu.music.service.impl;

import cn.tedu.music.entity.District;
import cn.tedu.music.mapper.DistrictMapper;
import cn.tedu.music.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    DistrictMapper mapper;
    @Override
    public List<District> getByParent(String parent) {
        return findByParent(parent);
    }

    @Override
    public District getByCode(String code) {
        return findByCode(code);
    }

    private List<District> findByParent(String parent){
        return mapper.findByParent(parent);
    }

    private District findByCode(String code){return mapper.findByCode(code);}
}
