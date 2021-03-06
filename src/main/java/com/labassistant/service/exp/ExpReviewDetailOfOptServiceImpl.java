package com.labassistant.service.exp;

import com.labassistant.beans.ExpReviewDetailOfOptEntity;
import com.labassistant.dao.service.BaseAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zql on 2015/12/8.
 */
@Service
public class ExpReviewDetailOfOptServiceImpl extends BaseAbstractService<ExpReviewDetailOfOptEntity> implements ExpReviewDetailOfOptService{

    @Override
    public List<ExpReviewDetailOfOptEntity> getExpReviewDetailOfOpts(String expReviewID, String expReviewOptID){
        String hql = "from ExpReviewDetailOfOptEntity where expReviewID = ? and expReviewOptID = ?";
        return findListByHql(hql, expReviewID, expReviewOptID);
    }

    @Override
    public ExpReviewDetailOfOptEntity getExpReviewDetailOfOpt(String expReviewID, String expReviewOptID, String itemID){
        String hql = "from ExpReviewDetailOfOptEntity where expReviewID = ? and expReviewOptID = ? and itemID = ?";
        return findOneByHql(hql, expReviewID, expReviewOptID, itemID);
    }

    @Override
    public boolean isReviewed(String expReviewID, String expReviewOptID, String itemID){
        String hql = "from ExpReviewDetailOfOptEntity where expReviewID = ? and expReviewOptID = ? and itemID = ?";
        return getCount(hql, true, expReviewID, expReviewOptID, itemID) > 0;
    }
}
