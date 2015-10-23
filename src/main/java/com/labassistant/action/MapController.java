package com.labassistant.action;

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

/**
 * 地图相关
 * @author zql
 * @date 2015/09/15
 */
@Controller
@RequestMapping(value = "/map")
public class MapController extends BaseController {

	@Autowired
	private MapService mapService;
	
	@RequestMapping(value = "/around")
	@ResponseBody
	public Map<String, Object> getAround(HttpServletRequest request, MapEntity theUser){
		setErrorMsg(request, "获取周围数据错误");
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from MapEntity where userID = ?";
		MapEntity mapEntity = mapService.findOneByHql(hql, theUser.getUserID());
		if(mapEntity != null){
			mapEntity.setLongitude(theUser.getLongitude());
			mapEntity.setLatitude(theUser.getLatitude());
			mapService.update(mapEntity);
			map.put("data", mapService.getAround(mapEntity));
		} else {
			mapService.save(theUser);
			map.put("data", mapService.getAround(mapEntity));
		}
		map.putAll(retSuccess());
		return map;
	}
	
	@RequestMapping(value = "/setReagents")
	@ResponseBody
	public Map<String, Object> setReagens(HttpServletRequest request, String mapID, String reagents){
		setErrorMsg(request, "设置试剂失败");
		Map<String, Object> map = new HashMap<String, Object>();
		mapService.setReagens(mapID, reagents);
		map.putAll(retSuccess());
		map.put("data", "");
		return map;
	}
}
