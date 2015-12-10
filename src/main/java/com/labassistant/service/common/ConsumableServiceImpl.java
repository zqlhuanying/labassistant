package com.labassistant.service.common;

import org.springframework.stereotype.Service;

import com.labassistant.beans.ConsumableEntity;
import com.labassistant.dao.service.BaseAbstractService;

import java.util.List;

/**
 * 耗材服务
 * @author zql
 * @date 2015/10/09
 */
@Service
public class ConsumableServiceImpl extends
		BaseAbstractService<ConsumableEntity> implements ConsumableService {

    @Override
    public List<ConsumableEntity> search(String name){
        String hql = "from ConsumableEntity where consumableName like ?";
        return findListByHql(hql, "%" + name + "%");
    }

}
