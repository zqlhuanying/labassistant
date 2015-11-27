package com.labassistant.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


import com.labassistant.beans.CityEntity;
import com.labassistant.beans.ProvinceEntity;
import com.labassistant.service.CityService;
import com.labassistant.service.ProvinceService;
import com.labassistant.utils.JSONUtil;

public class ImportCity {
	
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	
	@SuppressWarnings("unchecked")
	public void importCity(String path){
		String jsonString = readJsonFile(path);
		Map<String, Object> map = JSONUtil.json2Map(jsonString);
		List<ProvinceEntity> provinces = getProvinces(map);
		List<CityEntity> cities = getCities(map);
		provinceService.deleteAll(ProvinceEntity.class);
		cityService.deleteAll(CityEntity.class);
		provinceService.saveList(provinces);
		cityService.saveList(cities);
	}
	
	private String readJsonFile(String path){
		BufferedReader reader = null;
		StringBuffer rs = new StringBuffer();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "GB2312"));
			String jsonString = null;
			while ((jsonString = reader.readLine()) != null) {
				rs.append(jsonString.trim());
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件没找到");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("读取文件异常");
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println("关闭文件流异常");
				e.printStackTrace();
			}
		}
		return rs.toString();
	}
	
	@SuppressWarnings("unchecked")
	private List<ProvinceEntity> getProvinces(Map<String, Object> map){
		List<ProvinceEntity> provinces = new ArrayList<ProvinceEntity>();
		List<Map<String, Object>> provincesList = (List<Map<String, Object>>)map.get("provinces");
		for(Map<String, Object> provinceMap : provincesList){
			ProvinceEntity province = new ProvinceEntity();
			province.setProvinceID(String.valueOf(provinceMap.get("id")));
			province.setProvinceName((String)provinceMap.get("name"));
			provinces.add(province);
		}
		return provinces;
	}
	
	@SuppressWarnings("unchecked")
	private List<CityEntity> getCities(Map<String, Object> map){
		List<CityEntity> cities = new ArrayList<CityEntity>();
		List<Map<String, Object>> provincesList = (List<Map<String, Object>>)map.get("provinces");
		for(Map<String, Object> provinceMap : provincesList){
			List<Map<String, String>> cityList = (List<Map<String, String>>)provinceMap.get("cities");
			for(Map<String, String> cityMap : cityList){
				CityEntity city = new CityEntity();
				city.setProvinceID(String.valueOf(provinceMap.get("id")));
				city.setCityID(String.valueOf(cityMap.get("id")));
				city.setCityName(cityMap.get("name"));
				cities.add(city);
			}
		}
		return cities;
	}
	
	public static void main(String[] args){
		
	}
}
