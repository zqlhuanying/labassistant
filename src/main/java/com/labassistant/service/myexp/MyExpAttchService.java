package com.labassistant.service.myexp;

import com.labassistant.beans.MyExpAttchEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.List;

/**
 * Created by zql on 2016/1/8.
 */
public interface MyExpAttchService extends IBaseAbstractService<MyExpAttchEntity> {

    public List<MyExpAttchEntity> getMyExpAttches(String myExpID);
}
