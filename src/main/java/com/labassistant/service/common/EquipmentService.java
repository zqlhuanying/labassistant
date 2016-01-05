package com.labassistant.service.common;

import com.labassistant.beans.EquipmentEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author zql
 * @date 2015/10/09
 */
public interface EquipmentService extends IBaseAbstractService<EquipmentEntity> {

    public List<EquipmentEntity> search(String name);

    public List<EquipmentEntity> getEquipmentList(Date date);
}
