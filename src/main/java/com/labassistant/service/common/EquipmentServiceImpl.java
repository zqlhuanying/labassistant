package com.labassistant.service.common;

import org.springframework.stereotype.Service;

import com.labassistant.beans.EquipmentEntity;
import com.labassistant.dao.service.BaseAbstractService;

import java.util.List;

/**
 * 设备服务
 * @author zql
 * @date 2015/10/09
 */
@Service
public class EquipmentServiceImpl extends BaseAbstractService<EquipmentEntity>
		implements EquipmentService {

    @Override
    public List<EquipmentEntity> search(String name){
        String hql = "from EquipmentEntity where equipmentName like ?";
        return findListByHql(hql, "%" + name + "%");
    }
}
