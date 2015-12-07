package com.labassistant.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.labassistant.beans.MapEntity;
import com.labassistant.common.BaseController;
import com.labassistant.service.MapService;
import com.labassistant.service.SysUserService;

/**
 * 地图相关
 * @author zql
 * @date 2015/09/15
 */
@Controller
@RequestMapping(value = "/map")
public class MapController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MapService mapService;
	
	@RequestMapping(value = "/around")
	@ResponseBody
	public Map<String, Object> getAround(HttpServletRequest request, MapEntity theUser){
		setErrorMsg(request, "获取周围数据错误");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new HashMap<String, Object>();
		String hql = "from MapEntity where userID = ?";
		MapEntity mapEntity = mapService.findOneByHql(hql, theUser.getUserID());
		if(mapEntity != null){
			mapEntity.setLongitude(theUser.getLongitude());
			mapEntity.setLatitude(theUser.getLatitude());
			mapService.update(mapEntity);
			innerMap.put("arounds", mapService.getAround(mapEntity));
		} else {
			Serializable pk = mapService.save(theUser);
			mapEntity = mapService.get(pk);
			innerMap.put("arounds", mapService.getAround(mapEntity));
		}
		innerMap.put("mapID", mapEntity.getMapID());
		innerMap.put("userID", mapEntity.getUserID());
		innerMap.put("eMail", sysUserService.get(mapEntity.getUserID()).geteMail());
		innerMap.put("longitude", mapEntity.getLongitude());
		innerMap.put("latitude", mapEntity.getLatitude());
		innerMap.put("reagentName", mapEntity.getReagentName());
		innerMap.put("distance", mapEntity.getDistance());
		map.putAll(retSuccess());
		map.put("data", innerMap);
		return map;
	}
	
	@RequestMapping(value = "/setReagents")
	@ResponseBody
	public Map<String, Object> setReagens(HttpServletRequest request, String userID, String reagent){
		setErrorMsg(request, "设置试剂失败");
		Map<String, Object> map = new HashMap<String, Object>();
		mapService.setReagent(userID, reagent);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
