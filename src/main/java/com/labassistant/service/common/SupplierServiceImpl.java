package com.labassistant.service.common;

import org.springframework.stereotype.Service;

import com.labassistant.beans.SupplierEntity;
import com.labassistant.dao.service.BaseAbstractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应商服务类
 * @author zql
 * @date 2015/09/15
 */
@Service
public class SupplierServiceImpl extends BaseAbstractService<SupplierEntity> implements
		SupplierService {

    @Override
    public List<SupplierEntity> getSupplierList(Date date){
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("from SupplierEntity ");
        if(date != null){
            sb.append("where updateTime > ?");
            params.add(date);
        }
        return findListByHql(sb.toString(), params);
    }

}
