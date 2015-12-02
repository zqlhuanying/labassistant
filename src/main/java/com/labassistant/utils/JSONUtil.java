package com.labassistant.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.BeanSerializerBuilder;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import com.labassistant.beans.ExpInstructionEntity;
import com.labassistant.exception.MyRuntimeException;
import org.codehaus.jackson.map.ser.impl.UnwrappingBeanPropertyWriter;

/**
 * json操作工具类
 * @author zql
 * @date 2015/09/08
 */
public final class JSONUtil {

	/**
	 * 自定义 SerializerProvider
	 * @author zql
	 * @date 2015/12/01
	 */
	/*static final class MySerializerProvider extends StdSerializerProvider{
		JsonSerializer<Object> myJsonSerializer = new JsonSerializer<Object>(){
			@Override
			public void serialize(Object arg0, JsonGenerator arg1,
				SerializerProvider arg2) throws IOException,
				JsonProcessingException {
				//System.out.println(arg0.getClass());
				arg1.writeString("");
                System.out.println(arg0.getClass());
			}
		};
		
		private MySerializerProvider(){
            // NullValueSerializer 只对 Value 为 Null 的情况进行处理
            // 但是因获取不到 Value 的类型，导致不能只对 String 进行处理
            // 所以舍弃
			super.setNullValueSerializer(myJsonSerializer);
		}

        private MySerializerProvider(SerializationConfig config,
                                        MySerializerProvider src, SerializerFactory f){
            super(config, src, f);
        }

        @Override
        protected StdSerializerProvider createInstance(SerializationConfig config, SerializerFactory jsf) {
            return new MySerializerProvider(config, this, jsf);
        }
    }*/

    /**
     * 自定义 JsonSerializer
     * 但是自定义的 json 解析器 只对 Value 不为 Null 的情况处理
     */
    /*private static final class MyJsonSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
            System.out.println(value);
            jsonGenerator.writeString("");
        }
    }

	private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
        SimpleModule module = new SimpleModule("simpleModule", Version.unknownVersion());
        module.addSerializer(String.class, new MyJsonSerializer());
        objectMapper.registerModule(module);
    }*/

    /**
     * 自定义 SerializerFactory
     * BeanPropertyWriter: 对传进来的 Bean 而言，每个字段都对应着一个 BeanPropertyWriter
     * 即就是对每个字段进行处理，处理函数是 serializeAsField()
     * 这是优先于 JsonSerializer 对字段进行处理
     * 所以重写 serializeAsField() 可以实现对不同的字段或字段类型进行处理
     */
    static class EntityCustomSerializationFactory extends CustomSerializerFactory{
        @Override
        protected void processViews(SerializationConfig config, BeanSerializerBuilder builder) {
            // get origin writers
            List<BeanPropertyWriter> originWriters = builder.getProperties();

            // create actual writers
            List<BeanPropertyWriter> writers = new ArrayList<BeanPropertyWriter>();

            for(final BeanPropertyWriter writer : originWriters){
                writers.add(new UnwrappingBeanPropertyWriter(writer){
                    @Override
                    public void serializeAsField(Object bean, JsonGenerator jsonGenerator, SerializerProvider prov) throws Exception {
                        Object value = get(bean);
                        Class<?> valueClass = writer.getPropertyType();
                        String name = writer.getName();
                        if(String.class.isAssignableFrom(valueClass) && value == null){
                            jsonGenerator.writeFieldName(name);
                            jsonGenerator.writeString("");
                        }
                        super.serializeAsField(bean, jsonGenerator, prov);
                    }

                    @Override
                    public BeanPropertyWriter withSerializer(JsonSerializer<Object> ser) {
                        return this;
                    }
                });
            }
            builder.setProperties(writers);
            super.processViews(config, builder);
        }
    }
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
	static {
        objectMapper.setSerializerFactory(new EntityCustomSerializationFactory());
    }

    // 不允许实例化
    private JSONUtil(){}
    
	/**
	 * 将Map转换成json
	 * @param map 需要转换的对象
	 * @return String
	 */
	public static String map2Json(Map map){
		requireNotNull(map, "map is null");
		try{
			return objectMapper.writeValueAsString(map);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将JSON转换成Map
	 * @param json 需要转换的对象
	 * @return Map
	 */
	public static Map json2Map(String json){
		requireNotNull(json, "json is null");
		try{
			return objectMapper.readValue(json, Map.class);
		} catch (Exception ex){
			ex.printStackTrace();
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将Bean转换成json
	 * @param bean 需要转换的对象
	 * @return String
	 */
	public static <T> String bean2Json(T bean){
		requireNotNull(bean, "bean is null");
		try{
			return objectMapper.writeValueAsString(bean);
		} catch (Exception ex){
			ex.printStackTrace();
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将JSON转换成JavaBean
	 * @param json 需要转换的对象
	 * @return JavaBean对象
	 */
	public static <T> T json2Bean(String json, Class<T> beanClass){
		requireNotNull(json, "json is null");
		try{
			return objectMapper.readValue(json, beanClass);
		} catch (Exception ex){
			ex.printStackTrace();
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将Bean转换成Map
	 * @param bean 需要转换的对象
	 * @return Map
	 */
	public static <T> Map bean2Map(T bean){
		try{
			return json2Map(bean2Json(bean));
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
	 * 将Map转换成JavaBean
	 * @param map 需要转换的对象
	 * @return JavaBean对象
	 */
	public static <T> T map2Bean(Map map, Class<T> beanClass){
		try{
			System.out.println(map2Json(map));
			return json2Bean(map2Json(map), beanClass);
		} catch (Exception ex){
			throw new MyRuntimeException("json转换异常");
		}
	}
	
	/**
     * 判断对象是否为空，如果为空，直接抛出异常
     * @param object 需要检查的对象
     * @param errorMessage 异常信息
     * @return 非空的对象
     */
    private static Object requireNotNull(Object object, String errorMessage) {
        if(null == object) {
            throw new NullPointerException(errorMessage);
        }
        return object;
    }
    
    //test
    public static void main(String[] args){
    	String json = "{\"experimentDesc\":\"我也不知道\",\"supplierName\":\"海尔\",\"expCategoryID\":\"1\",\"createDate\":\"2015-09-16\",\"reviewCount\":0,\"provideUser\":\"\",\"experimentTheory\":\"还是不知道\",\"expSubCategoryID\":\"11\",\"expInstructionID\":\"4028c681494b994701494b99bab61111\",\"downloadCount\":20,\"allowDownload\":1,\"experimentName\":\"ELISA检测血清TNF-b浓度\",\"expVersion\":1,\"filterStr\":\"\",\"productNum\":\"hhd\",\"supplierID\":\"1001\"}";
    	//Map m = json2Map(json);
    	System.out.println(json2Bean(json, ExpInstructionEntity.class));
        ExpInstructionEntity expInstruction = new ExpInstructionEntity();
        expInstruction.setExpInstructionID("11111");
    	System.out.println(bean2Json(expInstruction));
    	System.out.println(bean2Map(new ExpInstructionEntity()));
    	//String json = "{\"imgStream\":\"1\"}";
    	//System.out.println(json);
    	//System.out.println(json2Map(json));
    }
}
