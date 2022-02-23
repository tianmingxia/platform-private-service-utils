package cn.iald.platform.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @description: JSON序列化调整
 * @author: wangyc
 * @create: 2020-12-30 17:00
 **/
@JsonComponent
public class JsonSerializerManage {

	/**
	 * 序列换成json时,将所有的long变成string
	 * 因为js中得数字类型不能包含所有的java long值
	 *
	 * @param builder
	 * @return
	 */
	@Bean
	public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		SimpleModule module = new SimpleModule();
		module.addSerializer(Long.class, ToStringSerializer.instance);
		module.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(module);
		return objectMapper;
	}

}

