package com.labassistant.service.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.labassistant.beans.ReagentEntity;
import com.labassistant.dao.service.BaseAbstractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 试剂服务类
 * @author zql
 * @date 2015/09/15
 */
@Service
public class ReagentServiceImpl extends BaseAbstractService<ReagentEntity> implements
		ReagentService {

    @Override
    public List<ReagentEntity> getReagents(String levelOneID, String levelTwoID){
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(" from ReagentEntity where ");
        if(StringUtils.isNotBlank(levelOneID)){
            sb.append(" and levelOneSortID = ?");
            params.add(levelOneID);
        }
        if(StringUtils.isNotBlank(levelTwoID)){
            sb.append(" and levelTwoSortID = ?");
            params.add(levelTwoID);
        }
        String queryHql = sb.toString();
        // 若params中没有参数，则表示全表查询
        if(params.size() == 0){
            queryHql = queryHql.replace("where", "");
        } else {
            queryHql = queryHql.replaceFirst("and", "");
        }
        return findListByHql(queryHql, params);
    }

    @Override
    public List<ReagentEntity> search(String name){
        String hql = "from ReagentEntity where reagentName like ?";
        return findListByHql(hql, "%" + name + "%");
    }

    @Override
    public List<ReagentEntity> getReagentList(Date date){
        List<Object> params = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("from ReagentEntity ");
        if(date != null){
            sb.append("where updateTime > ?");
            params.add(date);
        }
        return findListByHql(sb.toString(), params);
    }
}
