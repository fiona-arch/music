package cn.tedu.music.service.impl;

import cn.tedu.music.entity.Address;
import cn.tedu.music.entity.District;
import cn.tedu.music.mapper.AddressMapper;
import cn.tedu.music.service.IAddressService;
import cn.tedu.music.service.IDistrictService;
import cn.tedu.music.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //省市区信息
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
    @Transactional
    public void setDefault(Integer aid, Integer uid, String username) throws AddressNotFoundException, AccessDeniedException, UpdateException {
        //根据aid查找地址信息
        Address address=findByAid(aid);
        //用户地址不存在抛出异常
        if(address==null){
            throw new AddressNotFoundException("你尝试操作的数据不存在");
        }
        //根据地址查出用户id,查出的id于当前登录用户的id不一致 抛出异常
        if(!uid.equals(address.getUid())){
            throw new AccessDeniedException("拒绝修改他人数据");
        }
        //将用户所有地址设为非默认
        updateNonDefault(uid);
        Date now=new Date();
        //将用户选择的地址设为默认
        addressMapper.updateDefault(aid,username,now);
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) throws AddressNotFoundException, AccessDeniedException, DeleteException {
        //验证地址是否存在
        Address address=findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("你尝试操作的数据不存在");
        }
        //验证数据归属
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("拒绝修改他人数据");
        }
        //删除地址
        deleteByAid(aid);
        //查询地址数量 为零直接返回
        Integer count=countByUid(uid);
        if(count==0){
            return;
        }
        //查询删除地址是否默认  非默认直接返回
        if(address.getIsDefault()!=1){
            return;
        }
        //查询最后修改的地址id
        Address result=findLastModifiedAddress(uid);
        //将最后修改的地址id设为默认
        updateDefault(result.getAid(),username,new Date());
    }

    @Override
    public Address getByAid(Integer aid) {
        return findByAid(aid);
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

    private void updateNonDefault(Integer uid){
        Integer rows=addressMapper.updateNonDefault(uid);
        if(rows==0){
            throw new UpdateException("修改失败!发生了未知错误");
        }
    }

    private Address findByAid(Integer aid){
        return addressMapper.findByAid(aid);
    }

   private void updateDefault(Integer aid,String modifiedUser,Date modifiedTime){
        Integer rows=addressMapper.updateDefault(aid,modifiedUser,modifiedTime);
        if(rows!=1){
            throw new UpdateException("设为默认失败!发生了未知错误");
        }
    }
    private void deleteByAid(Integer aid){
        Integer rows=addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除失败!发生了未知错误");
        }
    }
    public Address findLastModifiedAddress(Integer uid){
        return addressMapper.findLastModifiedAddress(uid);
    }
}
