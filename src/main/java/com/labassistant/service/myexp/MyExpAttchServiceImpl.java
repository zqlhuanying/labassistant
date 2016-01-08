package com.labassistant.service.myexp;

import com.labassistant.beans.MyExpAttchEntity;
import com.labassistant.dao.service.BaseAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zql on 2016/1/8.
 */
@Service
public class MyExpAttchServiceImpl extends BaseAbstractService<MyExpAttchEntity> implements MyExpAttchService{

    @Override
    public List<MyExpAttchEntity> getMyExpAttches(String myExpID){
        String hql = "from MyExpAttchEntity where myExpID = ?";
        return findListByHql(hql, myExpID);
    }
}
