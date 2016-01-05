package com.labassistant.service.common;

import com.labassistant.beans.SupplierEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author zql
 * @date 2015/09/15
 */
public interface SupplierService extends IBaseAbstractService<SupplierEntity> {

    public List<SupplierEntity> getSupplierList(Date date);
}
