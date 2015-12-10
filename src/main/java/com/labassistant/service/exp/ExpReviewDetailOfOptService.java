package com.labassistant.service.exp;

import com.labassistant.beans.ExpReviewDetailOfOptEntity;
import com.labassistant.dao.service.IBaseAbstractService;

/**
 * Created by zql on 2015/12/8.
 */
public interface ExpReviewDetailOfOptService extends IBaseAbstractService<ExpReviewDetailOfOptEntity> {

    public ExpReviewDetailOfOptEntity getExpReviewDetailOfOpt(String expReviewID, String expReviewOptID, String itemID);

    public boolean isReviewed(String expReviewID, String expReviewOptID, String itemID);
}
