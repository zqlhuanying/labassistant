package com.labassistant.service;

import java.util.ArrayList;
import java.util.List;

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

	private static final double EARTH_RADIUS = 6378137;		// 地球半径
	private static final double LIMIT = 2000;		// 周围附近多少公里,默认获取附近2公里的用户
	
	/**
	 * 获取某位置处的周围数据
	 * 
	 * @param longitude 经度
	 * @param latitude  纬度
	 * @return 附近用户
	 */
	@Override
	public List<MapEntity> getAround(MapEntity theUser, double limit) {
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

	@Override
	public List<MapEntity> getAround(MapEntity theUser) {
		return getAround(theUser, LIMIT);
	}
	
	@Override
	public void setReagens(String mapID, String reagents) {
		MapEntity map = get(mapID);
		map.setReagentName(reagents);
		update(map);
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
