package cn.tedu.music.mapper;

import cn.tedu.music.entity.District;

import java.util.List;

public interface DistrictMapper {

    /**
     * 根据parent寻找城市信息
     * @param parent
     * @return
     */
    List<District> findByParent(String parent);

    /**
     * 根据代号查询详情
     * @param code 省市区的代号
     * @return 匹配的省市区的详情  没有匹配的数据则返回null
     */
    District findByCode(String code);

}
