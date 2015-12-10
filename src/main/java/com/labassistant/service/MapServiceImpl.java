package com.labassistant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.MapEntity;
import com.labassistant.dao.service.BaseAbstractService;

/**
 * 地图相关服务
 * 
 * @author zql
 * @date 2015/09/14
 */
@Service
public class MapServiceImpl extends BaseAbstractService<MapEntity> implements
		MapService {

    @Autowired
    private SysUserService sysUserService;
	private static final double EARTH_RADIUS = 6378137;		// 地球半径
	private static final double LIMIT = 2000;		// 周围附近多少公里,默认获取附近2公里的用户

    @Override
    public List<Object> getAround(MapEntity theUser){
        List<Object> objects = new ArrayList<Object>();
        List<MapEntity> arounds = _getAround(theUser);
        for(MapEntity around : arounds){
            Map<String, String> map = new HashMap<String, String>();
            map.put("userID", around.getUserID());
            map.put("longitude", around.getLongitude());
            map.put("latitude", around.getLatitude());
            map.put("distance", String.valueOf(around.getDistance()));
            map.put("reagentName", around.getReagentName());
            map.put("eMail", sysUserService.get(around.getUserID()).geteMail());
            objects.add(map);
        }
        return objects;
    }

	/**
	 * 获取某位置处的周围数据
	 * 
	 * @param longitude 经度
	 * @param latitude  纬度
	 * @return 附近用户
	 */
	private List<MapEntity> _getAround(MapEntity theUser, double limit) {
		List<MapEntity> lists = findList();
		List<MapEntity> result = new ArrayList<MapEntity>();
		for (MapEntity list : lists) {
			double distance = computeDistance(theUser, list);
			list.setDistance(distance);
			// 大于0的目的是去除用户本身所在的位置
			if (distance > 0 && distance <= limit) {
				result.add(list);
			}
		}
		return result;
	}

	private List<MapEntity> _getAround(MapEntity theUser) {
		return _getAround(theUser, LIMIT);
	}
	
	@Override
	public void setReagent(String userID, String reagent) {
		String hql = "from MapEntity where userID = ?";
		MapEntity mapEntity = findOneByHql(hql, userID);
		String reagents = reagent;
		if(mapEntity != null){
			if(StringUtils.isNotBlank(mapEntity.getReagentName())){
				reagents += "," + mapEntity.getReagentName();
			}
			mapEntity.setReagentName(reagents);
			update(mapEntity);
		} else {
			MapEntity map = new MapEntity();
			map.setUserID(userID);
			map.setReagentName(reagents);
			save(map);
		}
	}
	
	/**
	 * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
	 * 
	 * @param thisMapEntity
	 * @param thatMapEntity
	 * @return
	 */
	private double computeDistance(MapEntity thisMapEntity, MapEntity thatMapEntity){
		// 获取比较的两个用户所在的经纬度
		double thisLongitude = Double.parseDouble(thisMapEntity.getLongitude());
		double thisLatitude = Double.parseDouble(thisMapEntity.getLatitude());
		double thatLongitude = Double.parseDouble(thatMapEntity.getLongitude());
		double thatLatitude = Double.parseDouble(thatMapEntity.getLatitude());
		
		thisLatitude = rad(thisLatitude);
		thatLatitude = rad(thatLatitude);
		double latitudeDiff = thisLatitude - thatLatitude;
		double longitudeDiff = rad(thisLongitude) - rad(thatLongitude);
		// 计算距离
		double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latitudeDiff/2),2) + Math.cos(thisLatitude)*Math.cos(thatLatitude)*Math.pow(Math.sin(longitudeDiff/2),2)));
		return Math.round(distance * EARTH_RADIUS * 10000) / 10000;
	}

	/**
	 * 将度转换成弧度
	 * 
	 * @param d
	 * @return
	 */
	private double rad(double d) {
		return d * Math.PI / 180.0;
	}
}
