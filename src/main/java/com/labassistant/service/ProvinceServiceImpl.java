package com.labassistant.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labassistant.beans.CityEntity;
import com.labassistant.beans.ProvinceEntity;
import com.labassistant.constants.ReturnJson;
import com.labassistant.dao.service.BaseAbstractService;
import com.labassistant.utils.CommonUtil;

/**
 * 省份
 * @author zql
 * @date 2015/09/29
 */
@Service
public class ProvinceServiceImpl extends BaseAbstractService<ProvinceEntity>
		implements ProvinceService {

	@Autowired
	private CityService cityService;
	
	/**
	 * 获取省份和城市对应列表
	 */
	@Override
	public List<Object> provinceAndCity(){
		List<Object> object = new ArrayList<Object>();
		List<ProvinceEntity> provinces = findList();
		if(provinces != null){
			for(ProvinceEntity province : provinces){
				Map<String, Object> map = new HashMap<String, Object>();
				List<CityEntity> cities = cityService.getCities(province.getProvinceID());
				map.put("provinceName", province.getProvinceName());
				map.put("provinceID", province.getProvinceID());
				map.put("cities", cities);
				CommonUtil.unionMap(ReturnJson.PROVINCEANDCITY, map);
				object.add(map);
			}
		}
		return object;
	}
}
