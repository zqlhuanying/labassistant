package com.labassistant.service.exp;

import com.labassistant.beans.ExpReviewOptEntity;
import com.labassistant.dao.service.IBaseAbstractService;

import java.util.List;

/**
 * 
 * @author zql
 * @date 2015/11/02
 */
public interface ExpReviewOptService extends IBaseAbstractService<ExpReviewOptEntity> {

    public List<Object> getExpReviewOpt(String expInstructionJson, String expInstructionID, String userID, int allowDownload);
}
