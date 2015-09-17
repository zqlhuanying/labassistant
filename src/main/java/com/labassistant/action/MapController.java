package com.labassistant.action;

import java.util.List;

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
	public List<MapEntity> getAround(HttpServletRequest request, MapEntity theUser){
		setErrorMsg(request, "获取周围数据错误");
		String hql = "from MapEntity where userid = ?";
		MapEntity mapEntity = mapService.findOneByHql(hql, theUser.getUserID());
		if(mapEntity != null){
			mapEntity.setLongitude(theUser.getLongitude());
			mapEntity.setLatitude(theUser.getLatitude());
			mapService.update(mapEntity);
			return mapService.getAround(mapEntity);
		} else {
			mapService.save(theUser);
			return mapService.getAround(theUser);
		}
	}
}
