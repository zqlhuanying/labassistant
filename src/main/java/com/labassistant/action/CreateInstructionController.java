package com.labassistant.action;

import com.labassistant.beans.*;
import com.labassistant.common.BaseController;
import com.labassistant.service.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zql on 2015/12/8.
 */
@Controller
@RequestMapping(value = "create")
public class CreateInstructionController extends BaseController {

    @Autowired
    private ReagentLevelOneService reagentLevelOneService;
    @Autowired
    private ReagentLevelTwoService reagentLevelTwoService;
    @Autowired
    private ReagentService reagentService;
    @Autowired
    private ConsumableService consumableService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private ReagentMapService reagentMapService;
    @Autowired
    private ConsumableMapService consumableMapService;
    @Autowired
    private EquipmentMapService equipmentMapService;
    @Autowired
    private SupplierService supplierService;


    @RequestMapping(value = "levelOne")
    @ResponseBody
    public Map<String, Object> levelOne(HttpServletRequest request){
        setErrorMsg(request, "获取试剂一级分类失败");
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(retSuccess());
        map.put("data", reagentLevelOneService.findList());
        return map;
    }

    @RequestMapping(value = "levelTwo")
    @ResponseBody
    public Map<String, Object> levelTwo(HttpServletRequest request, String levelOneID){
        setErrorMsg(request, "获取试剂二级分类失败");
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(retSuccess());
        map.put("data", reagentLevelTwoService.getLevelTwo(levelOneID));
        return map;
    }

    @RequestMapping(value = "getReagents")
    @ResponseBody
    public Map<String, Object> getReagents(HttpServletRequest request, String levelOneID, String levelTwoID){
        setErrorMsg(request, "获取试剂失败");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> objects = new ArrayList<Object>();
        List<ReagentEntity> reagents = reagentService.getReagents(levelOneID, levelTwoID);
        if(reagents != null){
            for(ReagentEntity reagent : reagents){
                Map<String, String> innerMap = new HashMap<String, String>();
                innerMap.put("reagentID", reagent.getReagentID());
                innerMap.put("reagentName", reagent.getReagentName());
                objects.add(innerMap);
            }
        }
        map.putAll(retSuccess());
        map.put("data", objects);
        return map;
    }

    @RequestMapping(value = "getConsumables")
    @ResponseBody
    public Map<String, Object> getConsumables(HttpServletRequest request){
        setErrorMsg(request, "获取耗材失败");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> objects = new ArrayList<Object>();
        List<ConsumableEntity> consumables = consumableService.findList();
        if(consumables != null){
            for(ConsumableEntity consumable : consumables){
                Map<String, String> innerMap = new HashMap<String, String>();
                innerMap.put("consumableID", consumable.getConsumableID());
                innerMap.put("consumableName", consumable.getConsumableName());
                objects.add(innerMap);
            }
        }
        map.putAll(retSuccess());
        map.put("data", objects);
        return map;
    }

    @RequestMapping(value = "getEquipments")
    @ResponseBody
    public Map<String, Object> getEquipments(HttpServletRequest request){
        setErrorMsg(request, "获取设备失败");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> objects = new ArrayList<Object>();
        List<EquipmentEntity> equipments = equipmentService.findList();
        if(equipments != null){
            for(EquipmentEntity equipment : equipments){
                Map<String, String> innerMap = new HashMap<String, String>();
                innerMap.put("equipmentID", equipment.getEquipmentID());
                innerMap.put("equipmentName", equipment.getEquipmentName());
                objects.add(innerMap);
            }
        }
        map.putAll(retSuccess());
        map.put("data", objects);
        return map;
    }

    @RequestMapping(value = "getSuppliers")
    @ResponseBody
    // mark: 1: 试剂接口；2：耗材接口；3：设备接口
    public Map<String, Object> getSuppliers(HttpServletRequest request, String id, int mark){
        setErrorMsg(request, "获取供应商失败");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> objects = new ArrayList<Object>();

        if(mark == 1){
            objects = getReagentSupplier(id);
        }
        if(mark == 2){
            objects = getConsumableSupplier(id);
        }
        if(mark == 3){
            objects = getEquipmentSupplier(id);
        }

        map.putAll(retSuccess());
        map.put("data", objects);
        return map;
    }

    @RequestMapping(value = "search")
    @ResponseBody
    // mark: 1: 试剂接口；2：耗材接口；3：设备接口
    public Map<String, Object> search(HttpServletRequest request, String name, int mark){
        setErrorMsg(request, "搜索失败");
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> objects = new ArrayList<Object>();
        if(mark == 1){
            objects = searchReagent(name);
        }
        if(mark == 2){
            objects = searchConsumable(name);
        }
        if(mark == 3){
            objects = searchEquipment(name);
        }

        map.putAll(retSuccess());
        map.put("data", objects);
        return map;
    }

    private List<Object> searchReagent(String name){
        List<Object> objects = new ArrayList<Object>();
        List<ReagentEntity> reagents = reagentService.search(name);
        if(reagents != null){
            for(ReagentEntity reagent : reagents){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("reagentID", reagent.getReagentID());
                map.put("levelOneSortID", reagent.getLevelOneSortID());
                map.put("levelOneSortName", reagentLevelOneService.get(reagent.getLevelOneSortID()).getLevelOneSortName());
                map.put("levelTwoSortID", reagent.getLevelTwoSortID());
                map.put("levelTwoSortName", reagentLevelTwoService.get(reagent.getLevelTwoSortID()).getLevelTwoSortName());
                map.put("reagentName", reagent.getReagentName());
                map.put("suppliers", getReagentSupplier(reagent.getReagentID()));
                objects.add(map);
            }
        }
        return objects;
    }

    private List<Object> searchConsumable(String name){
        List<Object> objects = new ArrayList<Object>();
        List<ConsumableEntity> consumables = consumableService.search(name);
        if(consumables != null){
            for (ConsumableEntity consumable : consumables){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("consumableID", consumable.getConsumableID());
                map.put("consumableName", consumable.getConsumableName());
                map.put("suppliers", getConsumableSupplier(consumable.getConsumableID()));
                objects.add(map);
            }
        }
        return objects;
    }

    private List<Object> searchEquipment(String name){
        List<Object> objects = new ArrayList<Object>();
        List<EquipmentEntity> equipments = equipmentService.search(name);
        if(equipments != null){
            for (EquipmentEntity equipment : equipments){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("equipmentID", equipment.getEquipmentID());
                map.put("equipmentName", equipment.getEquipmentName());
                map.put("suppliers", getEquipmentSupplier(equipment.getEquipmentID()));
                objects.add(map);
            }
        }
        return objects;
    }

    private List<Object> getReagentSupplier(String reagentID){
        List<Object> res = new ArrayList<Object>();
        List<ReagentMapEntity> reagentMaps = reagentMapService.getListByReagentID(reagentID);
        if(reagentMaps != null){
            for(ReagentMapEntity reagentMap : reagentMaps){
                Map<String, String> m = new HashMap<String, String>();
                m.put("supplierID", reagentMap.getSupplierID());
                m.put("supplierName", supplierService.get(reagentMap.getSupplierID()).getSupplierName());
                res.add(m);
            }
        }
        return res;
    }

    private List<Object> getConsumableSupplier(String consumableID){
        List<Object> res = new ArrayList<Object>();
        List<ConsumableMapEntity> consumableMaps = consumableMapService.getListByConsumableID(consumableID);
        if(consumableMaps != null){
            for(ConsumableMapEntity consumableMap : consumableMaps){
                Map<String, String> m = new HashMap<String, String>();
                m.put("supplierID", consumableMap.getSupplierID());
                m.put("supplierName", supplierService.get(consumableMap.getSupplierID()).getSupplierName());
                res.add(m);
            }
        }
        return res;
    }

    private List<Object> getEquipmentSupplier(String equipmentID){
        List<Object> res = new ArrayList<Object>();
        List<EquipmentMapEntity> equipmentMaps = equipmentMapService.getListByEquipmentID(equipmentID);
        if(equipmentMaps != null){
            for(EquipmentMapEntity equipmentMap : equipmentMaps){
                Map<String, String> m = new HashMap<String, String>();
                m.put("supplierID", equipmentMap.getSupplierID());
                m.put("supplierName", supplierService.get(equipmentMap.getSupplierID()).getSupplierName());
                res.add(m);
            }
        }
        return res;
    }
}
