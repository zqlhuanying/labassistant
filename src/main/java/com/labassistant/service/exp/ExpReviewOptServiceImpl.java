package com.labassistant.service.exp;

import com.labassistant.beans.*;
import com.labassistant.constants.LabConstant;
import com.labassistant.constants.ReturnJson;
import com.labassistant.service.SyncService;
import com.labassistant.service.myexp.MyExpMainService;
import com.labassistant.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.dao.service.BaseAbstractService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author zql
 * @date 2015/11/02
 */
@Service
public class ExpReviewOptServiceImpl extends
		BaseAbstractService<ExpReviewOptEntity> implements ExpReviewOptService {

    @Autowired
    private ExpReagentService expReagentService;
    @Autowired
    private ExpConsumableService expConsumableService;
    @Autowired
    private ExpEquipmentService expEquipmentService;
    @Autowired
    private ExpProcessService expProcessService;
    @Autowired
    private MyExpMainService myExpMainService;
    @Autowired
    private ExpInstructionsMainService expInstructionsMainService;
    @Autowired
    private SyncService syncService;

    /**
     * 获取实验评论项
     * @return
     */
    @Override
    public List<Object> expReviewOpt(String expInstructionJson, String expInstructionID, String userID, int allowDownload){
        // process expInstruction
        // 若说明书不存在，先上传
        if(!expInstructionsMainService.isExist(expInstructionID)){
            syncService.pushExpInstruction(expInstructionJson, expInstructionID, userID, allowDownload);
        }

        List<Object> objects = new ArrayList<Object>();
        //MyExpEntity myExp = myExpMainService.getByExpID(myExpID);
        List<ExpReviewOptEntity> expReviewOpts = findList();
        for(ExpReviewOptEntity expReviewOpt : expReviewOpts){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("expReviewOptID", expReviewOpt.getExpReviewOptID());
            map.put("expReviewOptName", expReviewOpt.getExpReviewOptName());
            map.put("expReviewDetailOfOpts", getExpReviewOptAttch(expReviewOpt.getExpReviewOptName(), expInstructionID));
            CommonUtil.unionMap(ReturnJson.REVIEWOPTIONAL, map);
            objects.add(map);
        }
        return objects;
    }

    private List<Object> getExpReviewOptAttch(String expReviewOptName, String expInstructionID){
        List<Object> objects = new ArrayList<Object>();
        if(LabConstant.EXPREAGENTS.equals(expReviewOptName)){
            List<ExpReagentEntity> expReagents = expReagentService.getExpReagentLists(expInstructionID);
            if(expReagents != null){
                for(ExpReagentEntity expReagent : expReagents){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("itemID", expReagent.getExpReagentID());
                    map.put("itemName", expReagent.getReagentName());
                    map.put("supplierID", expReagent.getSupplierID());
                    objects.add(map);
                }
            }
            return objects;
        }
        if(LabConstant.EXPCONSUMABLES.equals(expReviewOptName)){
            List<ExpConsumableEntity> expConsumables = expConsumableService.getExpConsumableLists(expInstructionID);
            if(expConsumables != null){
                for(ExpConsumableEntity expConsumable : expConsumables){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("itemID", expConsumable.getExpConsumableID());
                    map.put("itemName", expConsumable.getConsumableName());
                    map.put("supplierID", expConsumable.getSupplierID());
                    objects.add(map);
                }
            }
            return objects;
        }
        if(LabConstant.EXPEQUIPMENTS.equals(expReviewOptName)){
            List<ExpEquipmentEntity> expEquipments = expEquipmentService.getExpEquipmentLists(expInstructionID);
            if(expEquipments != null){
                for(ExpEquipmentEntity expEquipment : expEquipments){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("itemID", expEquipment.getExpEquipmentID());
                    map.put("itemName", expEquipment.getEquipmentName());
                    map.put("supplierID", expEquipment.getSupplierID());
                    objects.add(map);
                }
            }
            return objects;
        }
        if(LabConstant.EXPSTEPS.equals(expReviewOptName)){
            List<ExpStepEntity> expSteps = expProcessService.getProcessLists(expInstructionID);
            if(expSteps != null){
                for(ExpStepEntity expStep : expSteps){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("itemID", expStep.getExpStepID());
                    map.put("itemName", expStep.getExpStepDesc());
                    objects.add(map);
                }
            }
            return objects;
        }
        return null;
    }
}
