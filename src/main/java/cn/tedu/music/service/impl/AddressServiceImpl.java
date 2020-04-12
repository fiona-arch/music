package cn.tedu.music.service.impl;

import cn.tedu.music.entity.Address;
import cn.tedu.music.entity.District;
import cn.tedu.music.mapper.AddressMapper;
import cn.tedu.music.service.IAddressService;
import cn.tedu.music.service.IDistrictService;
import cn.tedu.music.service.ex.AddressCountLimitException;
import cn.tedu.music.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    IDistrictService districtService;

    @Override
    public void addNew(Address address, Integer uid, String username)
            throws InsertException, AddressCountLimitException {
        Integer count = countByUid(uid);
        if (count >= ADDRESS_MAX_COUNT) {
            throw new AddressCountLimitException("您的地址数量已经达到上限" + ADDRESS_MAX_COUNT + "条,禁止继续新增");
        }

        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        //TODO 省市区信息

        District province=districtService.getByCode(address.getProvinceCode());
        District city=districtService.getByCode(address.getCityCode());
        District area=districtService.getByCode(address.getAreaCode());
        if(province==null){
            address.setProvinceName(null);
        }else {
            address.setProvinceName(province.getName());
        }

        if(city==null){
            address.setCityName(null);
        }else{
            address.setCityName(city.getName());
        }


        if(city==null){
            address.setAreaName(null);
        }else{
            address.setAreaName(area.getName());
        }

        address.setUid(uid);
        address.setCreateUser(username);
        address.setCreateTime(new Date());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
       insert(address);
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        return findByUid(uid);
    }

    private Integer countByUid(Integer uid){
        return addressMapper.countByUid(uid);
    }


    private void insert(Address address){
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("新增失败!发生了未知错误!请联系系统管理员");
        }
    }

    private List<Address> findByUid(Integer uid){
        return addressMapper.findByUid(uid);
    }
}
