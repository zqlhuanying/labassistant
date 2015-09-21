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
}
