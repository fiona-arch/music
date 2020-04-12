package cn.tedu.music.service;

import cn.tedu.music.entity.District;

import java.util.List;

public interface IDistrictService {

    /**
     * 根据parent查询城市资料
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);

    /**
     * 根据代号查询详情
     * @param code 省市区的代号
     * @return 匹配的省市区的详情  没有匹配的数据则返回null
     */
    District getByCode(String code);
}
