package com.labassistant.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.labassistant.beans.CityEntity;
import com.labassistant.utils.CommonUtil;

/**
 * 返回给客户端的 json 格式，默认数据，保证返回的数据的一致性
 * 变量名对应着接口名，方便查找
 * @author zql
 * @date 2015/11/30
 */
public final class ReturnJson {

	public static final Map<String,Object> EXPREAGENTDETAILJSON = new HashMap<String, Object>(){
		private static final long serialVersionUID = 4288076939495473053L;
		{
			put("reagentName", "");
			put("chemicalName", "");
			put("supplier", "");
			put("levelOne", "");
			put("levelTwo", "");
			put("originPlace", "");
			put("productNo", "");
			put("agents", "");
			put("specification", "");
			put("casNo", "");
			put("memo", "");
		}
	};
	
	public static final Map<String,Object> REVIEWDETAILJSON = new HashMap<String, Object>(){
		private static final long serialVersionUID = -1757106916879489445L;
		{
			put("reviewInfo", "");
			put("reviewOpts", new ArrayList<Object>(){
				private static final long serialVersionUID = 5126275949572854913L;
				{
					Map<String, String> innerMap = new HashMap<String, String>();
					innerMap.put("expReviewOptName", "");
					innerMap.put("reviewOptScore", "");
					add(innerMap);
				}
			});
		}
	};

    public static final Map<String,Object> REVIEWOPTIONAL = new HashMap<String, Object>(){
        {
            put("expReviewOptID", "");
            put("expReviewOptName", "");
            put("expReviewDetailOfOpts", new ArrayList<Object>(){
                {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("itemID", "");
                    map.put("itemName", "");
                    map.put("supplierID", "");
                    add(map);
                }
                private static final long serialVersionUID = 3692245925651488180L;
            });
        }
        private static final long serialVersionUID = -6642689924356664199L;
    };

	public static final Map<String, Object> PROVINCEANDCITY = new HashMap<String, Object>(){
		private static final long serialVersionUID = 2163563696582262664L;
		{
			put("provinceName", "");
			put("provinceID", "");
			put("cities", new ArrayList<CityEntity>(){
				private static final long serialVersionUID = -4330496382746592174L;
				{
					add(new CityEntity());
				}
			});
		}
	};
	
	public static final Map<String, Object> REVIEWS = new HashMap<String, Object>(){
		private static final long serialVersionUID = -6642689924356664199L;
		{
			Map<String, String> innerMap = new HashMap<String, String>();
			innerMap.put("topicID", "");
			innerMap.put("topicDetail", "");
			innerMap.put("topicName", "");
            innerMap.put("icon", "");
            innerMap.put("creator", "");
            innerMap.put("createTime", "");

			put("topic", innerMap);
			put("reviews", new ArrayList<Object>(){
				private static final long serialVersionUID = -1272936552374723545L;
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("reviewID", "");
					map.put("parentReviewID", "");
					map.put("parentReviewer", "");
					map.put("reviewer", "");
					map.put("reviewDetail", "");
					map.put("reviewDateTime", "");
					map.put("icon", "");
					add(map);
				}
			});
		}
	};

    public static final Map<String, Object> CHECKVERSION = new HashMap<String, Object>(){
        private static final long serialVersionUID = 8984001426084824988L;
        {
            put("version", "0");
            put("url", "");
            put("content", "");
        }
    };

	// test
	public static void main(String[] args){
		// test: Value is Map
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> innerMap = new HashMap<String, Object>();
		innerMap.put("expReviewOptName", "实验试剂");
		innerMap.put("reviewOptScore", "3");
		innerMap.put("other", "忽略");
		map.put("reviewInfo", "请输入评论");
		map.put("reviewOpts", innerMap);
		System.out.println("Before union: " + map);
		//System.out.println("After union: " + CommonUtil.unionMap(REVIEWDETAILJSON, map));
		
		// test: Value is List<Map>
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<Object> objects = new ArrayList<Object>();
		Map<String, Object> innerMap1 = new HashMap<String, Object>();
		innerMap1.put("expReviewOptName", "实验试剂");
		innerMap1.put("reviewOptScore", "3");
		innerMap1.put("other", "忽略");
		Map<String, Object> innerMap2 = new HashMap<String, Object>();
		innerMap2.put("expReviewOptName", "实验试剂");
		innerMap2.put("reviewOptScore", "3");
		innerMap2.put("other", "忽略");
		Map<String, Object> innerMap3 = new HashMap<String, Object>();
		innerMap3.put("expReviewOptName", "实验试剂");
		innerMap3.put("reviewOptScore", "3");
		innerMap3.put("other", "忽略");
		objects.add(innerMap1);
		objects.add(innerMap2);
		objects.add(innerMap3);
		map1.put("reviewInfo", "请输入评论");
		map1.put("reviewOpts", objects);
		System.out.println("Before union: " + map1);
		CommonUtil.unionMap(REVIEWDETAILJSON, map1);
		System.out.println("After union: " + map1);
	}
}
